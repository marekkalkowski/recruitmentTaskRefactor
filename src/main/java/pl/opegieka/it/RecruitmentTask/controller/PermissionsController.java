package pl.opegieka.it.RecruitmentTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.opegieka.it.RecruitmentTask.Model.Card;
import pl.opegieka.it.RecruitmentTask.Model.PermissionGroup;
import pl.opegieka.it.RecruitmentTask.Model.Resource;
import pl.opegieka.it.RecruitmentTask.dao.*;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;
import pl.opegieka.it.RecruitmentTask.service.RegexService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/permissions")
public class PermissionsController {

    @Autowired
    private CardToResourceDao cardToResourceDao;

    @Autowired
    private GroupToResourceDao groupToResourceDao;

    @Autowired
    private RegexService regexService;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private CardToPermisionGroupDao cardToPermisionGroupDao;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private Card card;

    @Autowired
    private Resource resource;


    @GetMapping
    public String checkPermission(@RequestParam(value = "cardNumber") String cardNumber,
                                  @RequestParam(value = "resourceName") String resourceName) {

        checkNumberFormat(cardNumber, "Card id must be integer!");

        int checkedCardNumber = Integer.valueOf(cardNumber);
        card = cardDao.findByCardNumber(checkedCardNumber);

        List<PermissionGroup> groupList = card.getPermissionGroupList();

        if (resourceDao.checkIfExist(resourceName) == 0) {
            throw new NotFoundException("Resource not found");
        }

        return card.toString();
    }

    private void checkNumberFormat(String id, String s) {
        if (!regexService.checkRegex(id)) {
            throw new NumberFormatException(s);
        }
    }
}
