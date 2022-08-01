package sg.darren.microservices.cards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sg.darren.microservices.cards.model.Card;
import sg.darren.microservices.cards.model.Customer;
import sg.darren.microservices.cards.repository.CardRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardRepository cardRepository;

    @PostMapping("/cards")
    public List<Card> getCardList(@RequestBody Customer customer) {
        return cardRepository.findByCustomerId(customer.getCustomerId());
    }

}
