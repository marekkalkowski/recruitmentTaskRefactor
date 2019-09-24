package pl.opegieka.it.RecruitmentTask.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@Entity
@Table(name = "PERMISION_GRUOPS")
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long groupId;

    @Column(name = "group_name", unique = true)
    @NotNull
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERMISSION_GROUPS_TO_CARDS",
            joinColumns = @JoinColumn(name = "PERMISSION_GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "CARD_ID"))
    @JsonIgnore
    private List<Card> cardList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERMISSION_GROUPS_TO_RESOURCES",
            joinColumns = @JoinColumn(name = "PERMISSION_GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"))
    @JsonIgnore
    private List<Resource> resourceList;

    public PermissionGroup() {
    }

    public PermissionGroup(@NotNull String groupName) {
        this.groupName = groupName;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public void addCard(Card card) {
        cardList.add(card);
        card.getPermissionGroupList().add(this);
    }

    public void addResource(Resource resource) {
        resourceList.add(resource);
        resource.getPermissionGroupList().add(this);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PermissionGroup{");
        sb.append("groupId=").append(groupId);
        sb.append(", groupName='").append(groupName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
