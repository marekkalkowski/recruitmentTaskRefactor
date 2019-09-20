package pl.opegieka.it.RecruitmentTask.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RESOURCES")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int resourceId;

    @Column(name = "resource_name",unique = true)
    @NotNull
    private String resourceName;

    public Resource() {
    }

    public Resource(@NotNull String resourceName) {
        this.resourceName = resourceName;
    }

    public int getResourceId() {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Resource{");
        sb.append("resourceId=").append(resourceId);
        sb.append(", resourceName='").append(resourceName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
