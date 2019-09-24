package pl.opegieka.it.RecruitmentTask.Model;

import org.springframework.stereotype.Component;

@Component
public class CardToResourceDTO {

    private long cardId;
    private long resourceId;

    public CardToResourceDTO() {
    }

    public CardToResourceDTO(long cardId, long resourceId) {
        this.cardId = cardId;
        this.resourceId = resourceId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }
}
