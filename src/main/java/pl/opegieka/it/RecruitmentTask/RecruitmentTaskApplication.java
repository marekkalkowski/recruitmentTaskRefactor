package pl.opegieka.it.RecruitmentTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.opegieka.it.RecruitmentTask.Repository.Card;
import pl.opegieka.it.RecruitmentTask.dao.CardDao;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

@SpringBootApplication
public class RecruitmentTaskApplication {

    @Autowired
    public CardDao cardDao;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RecruitmentTaskApplication.class, args);



    }

}
