package sg.darren.retailbank.cards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sg.darren.retailbank.cards.model.Account;
import sg.darren.retailbank.cards.model.Customer;
import sg.darren.retailbank.cards.repository.AccountRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountRepository accountRepository;

    @PostMapping("/accounts")
    public List<Account> getAccountList(@RequestBody Customer customer) {
        return accountRepository.findByCustomerId(customer.getCustomerId());
    }

}
