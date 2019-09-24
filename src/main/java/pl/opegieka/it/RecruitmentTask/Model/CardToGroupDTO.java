package pl.opegieka.it.RecruitmentTask.Model;

import org.springframework.stereotype.Component;

@Component
public class CardToGroupDTO {

    private long cardId;
    private long groupId;

    public CardToGroupDTO() {
    }

    public CardToGroupDTO(long cardId, long groupId) {
        this.cardId = cardId;
        this.groupId = groupId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
