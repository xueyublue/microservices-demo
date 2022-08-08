package sg.darren.microservices.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sg.darren.microservices.cards.config.CardsServiceConfig;
import sg.darren.microservices.cards.model.Card;
import sg.darren.microservices.cards.model.Customer;
import sg.darren.microservices.cards.model.Properties;
import sg.darren.microservices.cards.repository.CardRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardsController {

    private final CardRepository cardRepository;
    private final CardsServiceConfig cardsServiceConfig;

    @PostMapping
    public List<Card> getCardList(@RequestBody Customer customer) {
        return cardRepository.findByCustomerId(customer.getCustomerId());
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                cardsServiceConfig.getMsg(),
                cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(),
                cardsServiceConfig.getActiveBranches());
        return ow.writeValueAsString(properties);
    }

}
