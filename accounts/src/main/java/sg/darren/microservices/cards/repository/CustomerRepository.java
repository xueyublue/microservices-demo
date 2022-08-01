package sg.darren.microservices.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.darren.microservices.cards.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
