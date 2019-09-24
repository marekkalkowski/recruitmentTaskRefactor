package pl.opegieka.it.RecruitmentTask.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@Entity
@Table(name = "CARDS")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long cardId;

    @NotNull
    @Column(name = "card_number", unique = true)
    private int cardNumber;


    @ManyToMany(mappedBy = "cardList",
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PermissionGroup> permissionGroupList;

    @ManyToMany(mappedBy = "cardList",
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Resource> resourceList;


    public Card() {
    }

    public Card(@NotNull int cardNumber) {
        this.cardNumber = cardNumber;

    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }


    public List<PermissionGroup> getPermissionGroupList() {
        return permissionGroupList;
    }

    public void setPermissionGroupList(List<PermissionGroup> permissionGroupList) {
        this.permissionGroupList = permissionGroupList;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public void addPermissonGroup(PermissionGroup group) {
        permissionGroupList.add(group);
        group.getCardList().add(this);
    }

    public void addResource(Resource resource) {
        resourceList.add(resource);
        resource.getCardList().add(this);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Card{");
        sb.append("cardId=").append(cardId);
        sb.append(", cardNumber=").append(cardNumber);
        sb.append(", permissionGroupList=").append(permissionGroupList);
        sb.append(", resourceList=").append(resourceList);
        sb.append('}');
        return sb.toString();
    }
}
