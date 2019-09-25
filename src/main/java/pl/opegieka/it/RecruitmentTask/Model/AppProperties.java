package pl.opegieka.it.RecruitmentTask.Model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Value("${build.version}")
    private String version;
    @Value("${build.timestamp}")
    private String time;

    public AppProperties() {
    }

    public AppProperties(String version, String time) {
        this.version = version;
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
