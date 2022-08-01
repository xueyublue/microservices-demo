package sg.darren.microservices.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.darren.microservices.cards.model.Card;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByCardNumber(String cardNumber);

    List<Card> findByCustomerId(int customerId);

}
