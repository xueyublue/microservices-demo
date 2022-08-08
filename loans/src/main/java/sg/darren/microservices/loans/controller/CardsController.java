package sg.darren.microservices.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sg.darren.microservices.loans.config.LoansServiceConfig;
import sg.darren.microservices.loans.model.Customer;
import sg.darren.microservices.loans.model.Loan;
import sg.darren.microservices.loans.model.Properties;
import sg.darren.microservices.loans.repository.LoanRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
public class CardsController {

    private final LoanRepository loanRepository;
    private final LoansServiceConfig loansServiceConfig;

    @PostMapping
    public List<Loan> getCardList(@RequestBody Customer customer) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                loansServiceConfig.getMsg(),
                loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getMailDetails(),
                loansServiceConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }

}
