package pl.opegieka.it.RecruitmentTask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.opegieka.it.RecruitmentTask.Model.AppProperties;

@RestController
@RequestMapping("/api/appversion")
public class AppPropertiesController {

    private static final Logger LOG =
            LoggerFactory.getLogger(CardController.class);

   @Autowired
   AppProperties appProperties;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AppProperties appInfo(){
        LOG.info("Zapytanie do /api/appversion. Zwracany obiekt to: " + appProperties);
        return appProperties;

    }
}
