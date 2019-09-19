package pl.opegieka.it.RecruitmentTask.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "CARDS")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int cardId;

    @NotNull
    @Column(name = "card_number")
    private int cardNumber;

    @NotNull
    @Column()
    private LocalDateTime expireDate;

    public Card() {
    }

    public Card(@NotNull int cardNumber, @NotNull LocalDateTime expireDate) {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardNumber=" + cardNumber +
                ", expireDate=" + expireDate +
                '}';
    }
}
