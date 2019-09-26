package pl.opegieka.it.RecruitmentTask.dao;

import pl.opegieka.it.RecruitmentTask.Model.PermissionGroup;

import java.util.List;

public interface PermissionGroupDao<T> {


    long save(T t);

    PermissionGroup update(T t);

    void delete(long T);

    PermissionGroup findById(long T);

    List<PermissionGroup> findAll();

    int checkIfExist(String T);

    PermissionGroup findByName(String T);
}
