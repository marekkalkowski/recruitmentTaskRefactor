package pl.opegieka.it.RecruitmentTask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.opegieka.it.RecruitmentTask.Model.Card;
import pl.opegieka.it.RecruitmentTask.dao.CardDao;
import pl.opegieka.it.RecruitmentTask.exception.AllreadyExistException;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;
import pl.opegieka.it.RecruitmentTask.service.RegexService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/cards")
public class CardController {

    private static final Logger LOG =
            LoggerFactory.getLogger(CardController.class);


    @Autowired
    private CardDao cardDao;

    @Autowired
    RegexService regexService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getAllCards() {
        LOG.info("Zapytanie GET do /api/cards; metoda getAllCards ");
        return cardDao.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Card addCard(@RequestBody Card card) {
        LOG.info("Zapytanie POST do /api/cards; metoda addCard ");

        checkCardNumberLenght(card);

        int cardNumber = card.getCardNumber();
        if (cardDao.checkIfExist(cardNumber) == 1) {
            throw new AllreadyExistException(String.format("Card with nuber %s already exist", cardNumber));
        }

        cardDao.save(card);

        LOG.info("Zapisano nową kartę" + card);
        return card;

    }


    @GetMapping(value = "/{cardId}")
    public Card getCardById(@PathVariable("cardId") String cardId) {

        checkFormatOfCardId(cardId);

        long checkedCardId = Long.valueOf(cardId);

        Card card = cardDao.findById(checkedCardId);
        if (card == null) {
            throw new NotFoundException("Card id not found");
        }
        return card;
    }


    @DeleteMapping(value = "/{cardId}")
    public String deleteCard(@PathVariable("cardId") String cardId) {

        checkFormatOfCardId(cardId);

        long checkedCardId = Long.valueOf(cardId);

        if (cardDao.findById(checkedCardId) == null) {
            throw new NotFoundException("Card id not found");
        }
        cardDao.delete(checkedCardId);
        return "Card with id: " + cardId + " was deleted";
    }

    private void checkFormatOfCardId(String cardId) {
        if (!regexService.checkRegex(cardId)) {
            throw new NumberFormatException("Card id must be integer!");
        }
    }

    private void checkCardNumberLenght(Card card) {
        if (Integer.valueOf(card.getCardNumber()).toString().length() > 8) {
            throw new NumberFormatException("Card number to long. Has to be max 8 digits.");
        }
    }
}
