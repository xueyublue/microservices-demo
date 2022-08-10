package sg.darren.microservices.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
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

    private final AccountRepository accountRepository;
    private final AccountsServiceConfig accountsServiceConfig;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @PostMapping
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
    public CustomerDetails getPropertyDetails(@RequestBody Customer customer) {
        Account account = accountRepository.findByCustomerId(customer.getCustomerId());
        List<Loan> loans = loansFeignClient.getLoans(customer);
        List<Card> cards = cardsFeignClient.getCards(customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(account);
        customerDetails.setLoans(loans);
        customerDetails.setCards(cards);

        return customerDetails;
    }

}
