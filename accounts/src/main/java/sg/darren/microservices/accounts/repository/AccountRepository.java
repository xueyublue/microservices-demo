package sg.darren.microservices.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.darren.microservices.accounts.model.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByCustomerId(Long customerId);

}
