package sg.darren.microservices.accounts.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sg.darren.microservices.accounts.model.entity.Account;
import sg.darren.microservices.accounts.model.entity.Customer;
import sg.darren.microservices.accounts.repository.CustomerRepository;
import sg.darren.microservices.accounts.repository.AccountRepository;

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
