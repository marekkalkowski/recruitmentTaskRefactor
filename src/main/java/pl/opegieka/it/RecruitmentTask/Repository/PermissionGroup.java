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

    @Column(name = "group_name")
    @NotNull
    private String groupName;
}
