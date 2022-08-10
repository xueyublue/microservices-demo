package sg.darren.microservices.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.darren.microservices.accounts.model.entity.Customer;
import sg.darren.microservices.accounts.model.dto.Loan;

import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "loans", consumes = "application/json")
    List<Loan> getLoans(@RequestBody Customer customer);

}
