package pl.opegieka.it.RecruitmentTask.Model;

import org.springframework.stereotype.Component;

@Component
public class GroupToResourceDTO {

    private long groupId;
    private long resourceId;

    public GroupToResourceDTO() {
    }

    public GroupToResourceDTO(long groupId, long resourceId) {
        this.groupId = groupId;
        this.resourceId = resourceId;
    }

    public long getgroupId() {
        return groupId;
    }

    public void setgroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }
}
