package sg.darren.retailbank.cards.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sg.darren.retailbank.cards.model.Card;
import sg.darren.retailbank.cards.repository.CardRepository;

import java.util.Date;

@Configuration
public class LoadInitialData {

    @Bean
    CommandLineRunner initDatabase(CardRepository cardRepository) {
        return args -> {
            String cardNumber = "4565XXXX4656";
            if (cardRepository.findByCardNumber(cardNumber) == null) {
                final Card c = Card.builder()
                        .customerId(1)
                        .cardNumber(cardNumber)
                        .cardType("CREDIT")
                        .totalLimit(10000)
                        .amountUsed(4500)
                        .availableAmount(5500)
                        .createDt(new Date())
                        .build();
                cardRepository.save(c);
            }

            cardNumber = "3455XXXX8673";
            if (cardRepository.findByCardNumber(cardNumber) == null) {
                final Card c = Card.builder()
                        .customerId(1)
                        .cardNumber(cardNumber)
                        .cardType("CREDIT")
                        .totalLimit(10000)
                        .amountUsed(6500)
                        .availableAmount(3500)
                        .createDt(new Date())
                        .build();
                cardRepository.save(c);
            }

            cardNumber = "2359XXXX9346";
            if (cardRepository.findByCardNumber(cardNumber) == null) {
                final Card c = Card.builder()
                        .customerId(1)
                        .cardNumber(cardNumber)
                        .cardType("CREDIT")
                        .totalLimit(10000)
                        .amountUsed(6500)
                        .availableAmount(3500)
                        .createDt(new Date())
                        .build();
                cardRepository.save(c);
            }
        };
    }
}
