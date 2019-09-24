package pl.opegieka.it.RecruitmentTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.opegieka.it.RecruitmentTask.Model.*;
import pl.opegieka.it.RecruitmentTask.dao.*;
import pl.opegieka.it.RecruitmentTask.exception.AllreadyExistException;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;
import pl.opegieka.it.RecruitmentTask.service.RegexService;

@RestController
@RequestMapping(value = "/api/cardtogroup")
public class CardToGroupController {

    @Autowired
    private Card card;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private PermissionGroup permissionGroup;

    @Autowired
    private PermissionGroupDao permissionGroupDao;

    @Autowired
    private CardToPermisionGroupDao cardToPermisionGroupDao;

    @Autowired
    private CardToGroupDTO cardToGroupDTO;

    @Autowired
    private CardToResourceDTO cardToResourceDTO;

    @Autowired
    private RegexService regexService;


    @PostMapping(value = "/{cardId}/{groupId}",
            produces = {"application/json"})
    public CardToGroupDTO addCardToGroup(@PathVariable("cardId") String cardId,
                                         @PathVariable("groupId") String groupId) {

        checkNumberFormat(cardId, "Card id must be integer!");
        checkNumberFormat(groupId, "Resource id must be integer!");
        Long checkedCardId = Long.valueOf(cardId);
        Long checkedGroupId = Long.valueOf(groupId);

        card = cardDao.findById(checkedCardId);
        if (card == null) {
            throw new NotFoundException("Card not found");
        }

        permissionGroup = permissionGroupDao.findById(Long.valueOf(checkedGroupId));
        if (permissionGroup == null) {
            throw new NotFoundException("Group not found");
        }

        if (cardToPermisionGroupDao.findIfExist(checkedCardId, checkedGroupId) == 1) {
            throw new AllreadyExistException(String.format("Card %s is already in group: %s", card.getCardNumber(), permissionGroup.getGroupName()));
        }

        card.addPermissonGroup(permissionGroup);
        cardDao.update(card);

        CardToGroupDTO cardToGroupDTO = new CardToGroupDTO(checkedCardId, checkedGroupId);
        return cardToGroupDTO;
    }


    @DeleteMapping(value = "/{cardId}/{groupId}",
            produces = {"application/json"})
    public String deleteCardFromGroup(@PathVariable("cardId") String cardId,
                                      @PathVariable("groupId") String groupId) {

        checkNumberFormat(cardId, "Card id must be integer!");
        checkNumberFormat(groupId, "Group id must be integer!");

        Long checkedCardId = Long.valueOf(cardId);
        Long checkedGroupId = Long.valueOf(groupId);

        cardToPermisionGroupDao.delete(checkedCardId, checkedGroupId);
        return String.format("Card with id %s  was deleted from %s id group", checkedCardId, checkedGroupId);
    }

    private void checkNumberFormat(String id, String s) {
        if (!regexService.checkRegex(id)) {
            throw new NumberFormatException(s);
        }
    }
}
