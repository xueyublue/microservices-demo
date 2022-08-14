package sg.darren.microservices.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.darren.microservices.accounts.model.dto.Card;
import sg.darren.microservices.accounts.model.entity.Customer;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "cards", consumes = "application/json")
    List<Card> getCards(@RequestHeader("retailbank-correlation-id") String correlationId,
                        @RequestBody Customer customer);

}
