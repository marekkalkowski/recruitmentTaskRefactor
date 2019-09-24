package pl.opegieka.it.RecruitmentTask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.opegieka.it.RecruitmentTask.Model.Resource;
import pl.opegieka.it.RecruitmentTask.dao.ResourceDao;
import pl.opegieka.it.RecruitmentTask.exception.AllreadyExistException;
import pl.opegieka.it.RecruitmentTask.exception.NotFoundException;
import pl.opegieka.it.RecruitmentTask.service.RegexService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/resources")
public class ResourceController {


    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RegexService regexService;

    @Value("${resource.min.length}")
    private String resourceMinLength;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Resource> getAllGResources() {
        return resourceDao.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource addResource(@RequestBody Resource resource) {

        String resourceName = resource.getResourceName();

        if (resourceName.length() < Integer.valueOf(resourceMinLength)) {
            throw new NumberFormatException("Resource name must be at least 4 characters long.");
        }


        if (resourceDao.checkIfExist(resourceName) == 1) {
            throw new AllreadyExistException(String.format("Resource name  \"%s\" already exist.", resourceName));
        }

        long id = resourceDao.save(resource);

        return resourceDao.findById(id);
    }

    @GetMapping(value = "/{resourceId}")
    public Resource getResourceById(@PathVariable("resourceId") String resourceId) {

        if (!regexService.checkRegex(resourceId)) {
            throw new NumberFormatException("Resource id must be integer!");
        }

        long id = Long.valueOf(resourceId);

        if (resourceDao.findById(id) == null) {
            throw new NotFoundException(String.format("Resource with id %s not found",resourceId));
        }

        Resource resource = resourceDao.findById(id);
        return resource;

    }

    @DeleteMapping(value = "/{resourceId}")
    public String deleteResource(@PathVariable("resourceId") int resourceId) {
        if (resourceDao.findById(resourceId) == null) {
            throw new NotFoundException(String.format("Resource with id %s not found",resourceId));
        }

        resourceDao.delete(resourceId);
        return String.format("Resource with id: %s was deleted", resourceId);
    }

    @GetMapping(value = "/name/{resourceName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource findByName(@PathVariable("resourceName") String resourceName) {
        Resource resource = resourceDao.findByName(resourceName);
        return resource;
    }
}
