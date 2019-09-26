package pl.opegieka.it.RecruitmentTask.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name = "RESOURCES")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long resourceId;

    @Column(name = "resource_name", unique = true)
    @NotNull
    private String resourceName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RESOURCES_TO_CARDS",
            joinColumns = @JoinColumn(name = "RESOURCE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CARD_ID"))
    @JsonIgnore
    private List<Card> cardList;

    @ManyToMany(mappedBy = "resourceList", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PermissionGroup> permissionGroupList;

    public Resource() {
    }

    public Resource(String resourceName) {
        this.resourceName = resourceName;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<PermissionGroup> getPermissionGroupList() {
        return permissionGroupList;
    }

    public void setPermissionGroupList(List<PermissionGroup> permissionGroupList) {
        this.permissionGroupList = permissionGroupList;
    }

    public void addCard(Card card) {
        cardList.add(card);
        card.getResourceList().add(this);
    }

    public void addPermissionGroup(PermissionGroup permissionGroup) {
        permissionGroupList.add(permissionGroup);
        permissionGroup.getResourceList().add(this);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Resource{");
        sb.append("resourceId=").append(resourceId);
        sb.append(", resourceName='").append(resourceName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return resourceId == resource.resourceId &&
                Objects.equals(resourceName, resource.resourceName) &&
                Objects.equals(cardList, resource.cardList) &&
                Objects.equals(permissionGroupList, resource.permissionGroupList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, resourceName, cardList, permissionGroupList);
    }
}
