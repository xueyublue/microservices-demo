package sg.darren.retailbank.cards.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sg.darren.retailbank.cards.model.Account;
import sg.darren.retailbank.cards.model.Customer;
import sg.darren.retailbank.cards.repository.AccountRepository;
import sg.darren.retailbank.cards.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.Date;

@Configuration
public class LoadInitialData {

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, AccountRepository accountRepository) {
        return args -> {
            final Customer c = Customer.builder()
                    .name("Alice")
                    .email("alice@email.com")
                    .mobileNumber("97112233")
                    .createDt(LocalDate.now())
                    .build();
            customerRepository.save(c);

            final Account a = Account.builder()
                    .customerId(1)
                    .accountNumber(new Date().getTime())
                    .accountType("SAVING")
                    .branchAddress("12 Block Street 34, Singapore 123456")
                    .createDt(LocalDate.now())
                    .build();
            accountRepository.save(a);
        };
    }
}
