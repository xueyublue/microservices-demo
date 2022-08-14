package sg.darren.microservices.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CardsController.class);

    private final CardRepository cardRepository;
    private final CardsServiceConfig cardsServiceConfig;

    @PostMapping
    public List<Card> getCardList(@RequestHeader("retailbank-correlation-id") String correlationId,
                                  @RequestBody Customer customer) {
        logger.info(String.format("CardsController.getCardList() invoked with retailbank-correlation-id: %s", correlationId));

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
