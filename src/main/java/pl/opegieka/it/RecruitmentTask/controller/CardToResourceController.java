package pl.opegieka.it.RecruitmentTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.opegieka.it.RecruitmentTask.Model.*;
import pl.opegieka.it.RecruitmentTask.dao.*;
import pl.opegieka.it.RecruitmentTask.exception.AllreadyExistException;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;
import pl.opegieka.it.RecruitmentTask.service.RegexService;

@RestController
@RequestMapping(value = "/api/cardtoresource")
public class CardToResourceController {

    @Autowired
    private Card card;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private Resource resource;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private CardToResourceDTO cardToResourceDTO;

    @Autowired
    private CardToResourceDao cardToResourceDao;

    @Autowired
    private RegexService regexService;

    @PostMapping(value = "{cardId}/{resourceId}",
            produces = {"application/json"})
    public CardToResourceDTO addCardToResource(@PathVariable("cardId") String cardId,
                                               @PathVariable("resourceId") String resourceId) {

        checkNumberFormat(cardId, "Card id must be integer!");
        checkNumberFormat(resourceId, "Resource id must be integer!");

        Long checkedCardId = Long.valueOf(cardId);
        Long checkedResourceId = Long.valueOf(resourceId);

        card = cardDao.findById(checkedCardId);
        if (card == null) {
            throw new NotFoundException("Card not found");
        }

        resource = resourceDao.findById(Long.valueOf(checkedResourceId));
        if (resource == null) {
            throw new NotFoundException("Resource not found");
        }

        int test =cardToResourceDao.findIfExist(checkedCardId, checkedResourceId);
        if (cardToResourceDao.findIfExist(checkedCardId, checkedResourceId) == 1) {
            throw new AllreadyExistException(String.format("Card %s is already in resource: %s", card.getCardNumber(), resource.getResourceName()));
        }

        card.addResource(resource);
        cardDao.update(card);

        CardToResourceDTO cardToResourceDTO = new CardToResourceDTO(checkedCardId, checkedResourceId);
        return cardToResourceDTO;
    }

    @DeleteMapping(value = "/{cardId}/{resourceId}",
            produces = {"application/json"})
    public String deleteCardFromResource(@PathVariable("cardId") String cardId,
                                      @PathVariable("resourceId") String resourceId) {

        checkNumberFormat(cardId, "Card id must be integer!");
        checkNumberFormat(resourceId, "Resource id must be integer!");

        Long checkedCardId = Long.valueOf(cardId);
        Long checkedResourceId = Long.valueOf(resourceId);

        cardToResourceDao.delete(checkedCardId, checkedResourceId);
        return String.format("Card with id %s  was deleted from %s id resource", checkedCardId, checkedResourceId);
    }

    private void checkNumberFormat(String id, String s) {
        if (!regexService.checkRegex(id)) {
            throw new NumberFormatException(s);
        }
    }
}
