package sg.darren.microservices.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import sg.darren.microservices.accounts.config.AccountsServiceConfig;
import sg.darren.microservices.accounts.model.dto.Card;
import sg.darren.microservices.accounts.model.dto.CustomerDetails;
import sg.darren.microservices.accounts.model.dto.Loan;
import sg.darren.microservices.accounts.model.dto.Properties;
import sg.darren.microservices.accounts.model.entity.Account;
import sg.darren.microservices.accounts.model.entity.Customer;
import sg.darren.microservices.accounts.repository.AccountRepository;
import sg.darren.microservices.accounts.service.client.CardsFeignClient;
import sg.darren.microservices.accounts.service.client.LoansFeignClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    private final AccountRepository accountRepository;
    private final AccountsServiceConfig accountsServiceConfig;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @PostMapping
    @Timed(value = "getAccountList.time", description = "Time taken to return Account Details")
    public Account getAccountList(@RequestBody Customer customer) {
        return accountRepository.findByCustomerId(customer.getCustomerId());
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                accountsServiceConfig.getMsg(),
                accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(),
                accountsServiceConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }

    @PostMapping("/customer-details")
    @CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "getCustomerDetailsFallBack")
//    @Retry(name = "retryForCustomerDetails", fallbackMethod = "getCustomerDetailsFallBack")
    public CustomerDetails getCustomerDetails(@RequestHeader("retailbank-correlation-id") String correlationId,
                                              @RequestBody Customer customer) {
        logger.info(String.format("Accounts.getCustomerDetails() invoked with retailbank-correlation-id: %s", correlationId));
        logger.info("Enter getCustomerDetails()");

        Account account = accountRepository.findByCustomerId(customer.getCustomerId());
        List<Loan> loans = loansFeignClient.getLoans(correlationId, customer);
        List<Card> cards = cardsFeignClient.getCards(correlationId, customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(account);
        customerDetails.setLoans(loans);
        customerDetails.setCards(cards);

        logger.info("Exit getCustomerDetails()");
        return customerDetails;
    }

    private CustomerDetails getCustomerDetailsFallBack(@RequestHeader("retailbank-correlation-id") String correlationId,
                                                       Customer customer,
                                                       Throwable throwable) {
        logger.info(String.format("Accounts.getCustomerDetailsFallBack() invoked with retailbank-correlation-id: %s", correlationId));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId());

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(account);
        customerDetails.setLoans(null);
        customerDetails.setCards(null);

        return customerDetails;
    }

    @GetMapping("/sayHello")
    @RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
    public String sayHello() {
        return "Hello, Welcome to RetailBank";
    }

    private String sayHelloFallback(Throwable t) {
        return "Hello, Welcome to RetailBank - Fallback";
    }

}
