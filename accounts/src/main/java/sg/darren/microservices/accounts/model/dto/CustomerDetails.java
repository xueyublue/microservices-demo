package sg.darren.microservices.accounts.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sg.darren.microservices.accounts.model.entity.Account;

import java.util.List;

@Getter
@Setter
@ToString
public class CustomerDetails {

    private Account accounts;
    private List<Loan> loans;
    private List<Card> cards;

}
