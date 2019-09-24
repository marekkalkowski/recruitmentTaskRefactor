package pl.opegieka.it.RecruitmentTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.opegieka.it.RecruitmentTask.dao.CardDao;

@SpringBootApplication
public class RecruitmentTaskApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RecruitmentTaskApplication.class, args);

    }

}
