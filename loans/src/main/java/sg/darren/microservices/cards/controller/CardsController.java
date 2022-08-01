package sg.darren.microservices.cards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sg.darren.microservices.cards.model.Customer;
import sg.darren.microservices.cards.model.Loan;
import sg.darren.microservices.cards.repository.LoanRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final LoanRepository loanRepository;

    @PostMapping("/loans")
    public List<Loan> getCardList(@RequestBody Customer customer) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
    }

}
