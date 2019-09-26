package pl.opegieka.it.RecruitmentTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.opegieka.it.RecruitmentTask.Model.GroupToResourceDTO;
import pl.opegieka.it.RecruitmentTask.Model.PermissionGroup;
import pl.opegieka.it.RecruitmentTask.Model.Resource;
import pl.opegieka.it.RecruitmentTask.dao.GroupToResourceDao;
import pl.opegieka.it.RecruitmentTask.dao.PermissionGroupDao;
import pl.opegieka.it.RecruitmentTask.dao.ResourceDao;
import pl.opegieka.it.RecruitmentTask.exception.AllreadyExistException;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;
import pl.opegieka.it.RecruitmentTask.service.RegexService;

@RestController
@RequestMapping(value = "/api/grouptoresource")
public class GroupToResourcesController {

    @Autowired
    private Resource resource;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private PermissionGroup permissionGroup;

    @Autowired
    private PermissionGroupDao permissionGroupDao;

    @Autowired
    private GroupToResourceDao groupToResourceDao;

    @Autowired
    private RegexService regexService;

    @PostMapping(value = "/{groupId}/{resourceId}",
            produces = {"application/json"})
    public GroupToResourceDTO addGroupToResource(@PathVariable("groupId") String groupId,
                                                 @PathVariable("resourceId") String resourceId) {

        checkNumberFormat(groupId, "Group id must be integer!");
        checkNumberFormat(resourceId, "Resource id must be integer!");

        Long checkedGroupId = Long.valueOf(groupId);
        Long checkedResourceId = Long.valueOf(resourceId);

        permissionGroup = permissionGroupDao.findById(checkedGroupId);
        if (permissionGroup == null) {
            throw new NotFoundException("Group not found");
        }

        resource = resourceDao.findById(Long.valueOf(checkedResourceId));
        if (resource == null) {
            throw new NotFoundException("Resource not found");
        }

        int test = groupToResourceDao.findIfExist(checkedGroupId, checkedResourceId);
        if (groupToResourceDao.findIfExist(checkedGroupId, checkedResourceId) == 1) {
            throw new AllreadyExistException(String.format("Group %s is already in resource: %s", permissionGroup.getGroupName(), resource.getResourceName()));
        }

        permissionGroup.addResource(resource);
        permissionGroupDao.update(permissionGroup);

        GroupToResourceDTO groupToResourceDTO = new GroupToResourceDTO(checkedGroupId, checkedResourceId);
        return groupToResourceDTO;
    }

    @DeleteMapping(value = "/{groupId}/{resourceId}",
            produces = {"application/json"})
    public String deleteGroupFromResource(@PathVariable("groupId") String groupId,
                                          @PathVariable("resourceId") String resourceId) {

        checkNumberFormat(groupId, "Group id must be integer!");
        checkNumberFormat(resourceId, "Resource id must be integer!");

        Long checkedGroupId = Long.valueOf(groupId);
        Long checkedResourceId = Long.valueOf(resourceId);

        groupToResourceDao.delete(checkedGroupId, checkedResourceId);
        return String.format("Group with id %s  was deleted from %s id resource", checkedGroupId, checkedResourceId);
    }

    private void checkNumberFormat(String id, String s) {
        if (!regexService.checkRegex(id)) {
            throw new NumberFormatException(s);
        }
    }
}
