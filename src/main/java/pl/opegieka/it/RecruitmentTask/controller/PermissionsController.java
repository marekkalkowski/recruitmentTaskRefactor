package pl.opegieka.it.RecruitmentTask.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.opegieka.it.RecruitmentTask.Model.*;
import pl.opegieka.it.RecruitmentTask.dao.*;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;
import pl.opegieka.it.RecruitmentTask.service.RegexService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/permissions")
public class PermissionsController {

    public static final Logger LOG = LogManager.getLogger(PermissionsController.class);

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

    @Autowired
    private PermissionDTO permissionDTO;

    @GetMapping
    public PermissionDTO checkPermission(@RequestParam(value = "cardNumber") String cardNumber,
                                         @RequestParam(value = "resourceName") String resourceName) {

        checkNumberFormat(cardNumber, "Card id must be integer!");

        int checkedCardNumber = Integer.valueOf(cardNumber);
        card = cardDao.findByCardNumber(checkedCardNumber);

        LOG.debug("test");
        List<PermissionGroup> cardGroupList = card.getPermissionGroupList();
        List<Resource> cardResourceList = card.getResourceList();

        if (resourceDao.checkIfExist(resourceName) == 0) {
            throw new NotFoundException("Resource not found");
        }

        resource = resourceDao.findByName(resourceName);

        boolean checkCardGroupInResource = false;

        for (PermissionGroup group : cardGroupList
        ) {
            if (resource.getPermissionGroupList().contains(group)) {
                checkCardGroupInResource = true;
                break;

            } else {
                checkCardGroupInResource = false;
            }

        }

        if (cardResourceList.contains(resource) || checkCardGroupInResource) {
            permissionDTO = new PermissionDTO(checkedCardNumber, resourceName, PermissionStatus.GRANTED);
        } else {
            permissionDTO = new PermissionDTO(checkedCardNumber, resourceName, PermissionStatus.DENIED);
        }

        return permissionDTO;
    }

    private void checkNumberFormat(String id, String s) {
        if (!regexService.checkRegex(id)) {
            throw new NumberFormatException(s);
        }
    }
}
