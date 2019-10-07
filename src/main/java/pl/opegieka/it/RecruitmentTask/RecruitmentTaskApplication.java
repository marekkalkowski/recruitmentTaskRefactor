package pl.opegieka.it.RecruitmentTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RecruitmentTaskApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RecruitmentTaskApplication.class, args);


        System.out.println(RecruitmentTaskApplication.class.getPackage().getImplementationVersion());
    }
}
