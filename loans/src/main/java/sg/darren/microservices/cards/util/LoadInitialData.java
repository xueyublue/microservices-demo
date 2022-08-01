package sg.darren.microservices.cards.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sg.darren.microservices.cards.model.Loan;
import sg.darren.microservices.cards.repository.LoanRepository;

import java.util.Date;

@Configuration
public class LoadInitialData {

    @Bean
    CommandLineRunner initDatabase(LoanRepository loanRepository) {
        return args -> {
            final Loan l1 = Loan.builder()
                    .customerId(1)
                    .startDt(new Date())
                    .loanType("HOME")
                    .totalLoan(1000)
                    .amountPaid(200)
                    .outstandingAmount(800)
                    .createDt(new Date())
                    .build();
            loanRepository.save(l1);

            final Loan l2 = Loan.builder()
                    .customerId(1)
                    .startDt(new Date())
                    .loanType("VEHICLE")
                    .totalLoan(2000)
                    .amountPaid(200)
                    .outstandingAmount(1800)
                    .createDt(new Date())
                    .build();
            loanRepository.save(l2);

            final Loan l3 = Loan.builder()
                    .customerId(1)
                    .startDt(new Date())
                    .loanType("PERSONAL")
                    .totalLoan(3000)
                    .amountPaid(200)
                    .outstandingAmount(2800)
                    .createDt(new Date())
                    .build();
            loanRepository.save(l3);
        };
    }
}
