package sg.darren.microservices.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sg.darren.microservices.accounts.config.AccountsServiceConfig;
import sg.darren.microservices.accounts.model.Account;
import sg.darren.microservices.accounts.model.Customer;
import sg.darren.microservices.accounts.model.Properties;
import sg.darren.microservices.accounts.repository.AccountRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountRepository accountRepository;
    private final AccountsServiceConfig accountsServiceConfig;

    @PostMapping("/accounts")
    public List<Account> getAccountList(@RequestBody Customer customer) {
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

}
