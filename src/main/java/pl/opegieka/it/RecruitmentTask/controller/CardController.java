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
    private RegexService regexService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getAllCards() {
        LOG.info("Zapytanie GET do /api/cards; metoda getAllCards ");

        List<Card> cardList = cardDao.findAll();

        LOG.info("Zrócono wszystkie karty w liczbie : {} : ",cardList.size());

        return cardList;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Card addCard(@RequestBody Card card) {
        LOG.info("Zapytanie POST do /api/cards; metoda addCard ");
        LOG.debug("Sprawdzam czy numer karty jest liczbą całkowitą .......");

        checkCardNumberLenght(card);

        LOG.debug("Sprawdzenie czy numer karty na obiekcie Card jest liczbą całkowitą: udane ");
        LOG.debug("Pobranie numer karty z obiektu Card");

        int cardNumber = card.getCardNumber();

        LOG.info("Pobrano numer karty: {}", cardNumber);
        LOG.debug("Sprawdzenie czy karta o podanym numer {} istnieje", cardNumber);

        if (cardDao.checkIfExist(cardNumber) == 1) {
            LOG.warn("Card with nuber {} already exist", cardNumber);
            throw new AllreadyExistException(String.format("Card with nuber %s already exist", cardNumber));
        }

        LOG.debug("Zapisywanie nowej karty .... ");
        cardDao.save(card);

        LOG.info("Zapisano nową kartę" + card);

        return card;
    }


    @GetMapping(value = "/{cardId}")
    public Card getCardById(@PathVariable("cardId") String cardId) {

        LOG.info("Wyszukiwanie karty po id");
        LOG.info("Zapytanie GET do /api/cards/{cardId}; metoda getCardById ");
        LOG.debug("Sprawdzam czy id karty jest liczbą całkowitą .......");

        checkFormatOfCardId(cardId);

        LOG.debug("Sprawdzenie czy id karty na obiekcie Card jest liczbą całkowitą: udane ");

        long checkedCardId = Long.valueOf(cardId);

        LOG.debug("Ppobieranie obiektu Card z bazy danych o id: {}", checkedCardId);

        Card card = cardDao.findById(checkedCardId);

        LOG.info("Pobrano obiekt Card z bazy danych o id: {} : . Karta: {}", checkedCardId, card);
        LOG.debug("Sprawdzenie czy obiekt o numerze karty {} istnieje ", checkedCardId);

        if (card == null) {
            LOG.warn("Karta o numerze {} nie istnieje w bazie" + NotFoundException.class);
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
            LOG.warn("Id karty nie jest liczba całkowitą " + NumberFormatException.class);
            throw new NumberFormatException("Card id must be integer!");
        }
    }

    private void checkCardNumberLenght(Card card) {
        if (Integer.valueOf(card.getCardNumber()).toString().length() > 8) {
            LOG.warn("Numer karty nie jest liczba całkowitą " + NumberFormatException.class);
            throw new NumberFormatException("Card number to long. Has to be max 8 digits.");
        }
    }
}
