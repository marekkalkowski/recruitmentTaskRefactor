package pl.opegieka.it.RecruitmentTask.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PERMISION_GRUOPS")
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int groupId;

    @Column(name = "group_name",unique = true)
    @NotNull
    private String groupName;

    public PermissionGroup() {
    }

    public PermissionGroup(@NotNull String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PermissionGroup{");
        sb.append("groupId=").append(groupId);
        sb.append(", groupName='").append(groupName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
