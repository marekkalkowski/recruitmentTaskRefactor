package pl.opegieka.it.RecruitmentTask.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AppProperties {

    @Value("${app.name}")
    private String appName;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimeStamp;

    @RequestMapping(value="/info", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getInfo() {
        final StringBuilder sb = new StringBuilder("AppProperties{");
        sb.append("appName='").append(appName).append('\'');
        sb.append(", buildVersion='").append(buildVersion).append('\'');
        sb.append(", buildTimeStamp='").append(buildTimeStamp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
