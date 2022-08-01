package sg.darren.microservices.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.darren.microservices.cards.model.Loan;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);

}
