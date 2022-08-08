package sg.darren.microservices.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.darren.microservices.accounts.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
