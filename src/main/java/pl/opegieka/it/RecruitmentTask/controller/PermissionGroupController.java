package pl.opegieka.it.RecruitmentTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.opegieka.it.RecruitmentTask.Model.PermissionGroup;
import pl.opegieka.it.RecruitmentTask.dao.PermissionGroupDao;
import pl.opegieka.it.RecruitmentTask.exception.AllreadyExistException;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;
import pl.opegieka.it.RecruitmentTask.service.RegexService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/permissiongroups")
public class PermissionGroupController {

    @Autowired
    private PermissionGroupDao permissionGroupDao;

    @Autowired
    private RegexService regexService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PermissionGroup> getAllGroups() {
        return permissionGroupDao.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PermissionGroup saveGroup(@RequestBody PermissionGroup permissionGroup) {

        String groupName = permissionGroup.getGroupName();
        if (groupName.length() < 4) {
            throw new NumberFormatException("Group name must be at least 4 characters long.");
        }

        if (permissionGroupDao.checkIfExist(groupName) == 1) {
            throw new AllreadyExistException(String.format("Group name  %s already exist.", groupName));
        }

        long id = permissionGroupDao.save(permissionGroup);

        return permissionGroupDao.findById(id);
    }

    @GetMapping(value = "/{groupId}")
    public PermissionGroup getGroupById(@PathVariable("groupId") String groupId) {

        if (!regexService.checkRegex(groupId)) {
            throw new NumberFormatException("Group id must be integer!");
        }

        long id = Long.valueOf(groupId);

        if (permissionGroupDao.findById(id) == null) {
            throw new NotFoundException(String.format("Group with id %s not found", groupId));
        }

        PermissionGroup permissionGroup = permissionGroupDao.findById(id);
        return permissionGroup;

    }

    @DeleteMapping(value = "/{groupId}")
    public String deleteGroup(@PathVariable("groupId") int groupId) {
        if (permissionGroupDao.findById(groupId) == null) {
            throw new NotFoundException(String.format("Group with id %s not found", groupId));
        }

        permissionGroupDao.delete(groupId);
        return String.format("Grupa with id: %s was deleted", groupId);
    }

    @GetMapping(value = "/name/{groupName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PermissionGroup findByName(@PathVariable("groupName") String groupName) {
        PermissionGroup group = permissionGroupDao.findByName(groupName);
        return group;
    }
}
