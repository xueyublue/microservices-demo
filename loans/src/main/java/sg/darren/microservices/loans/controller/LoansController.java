package sg.darren.microservices.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LoansController {

    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);

    private final LoanRepository loanRepository;
    private final LoansServiceConfig loansServiceConfig;

    @PostMapping
    public List<Loan> getLoanList(@RequestHeader("retailbank-correlation-id") String correlationId,
                                  @RequestBody Customer customer) {
        logger.info(String.format("CardsController.getLoanList() invoked with retailbank-correlation-id: %s", correlationId));
        logger.info("getLoanList() called.");
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
