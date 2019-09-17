package pl.opegieka.it.RecruitmentTask.Repository;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Card {

    private int cardId;
    private int cardNumber;
    private LocalDateTime expireDate;
}
