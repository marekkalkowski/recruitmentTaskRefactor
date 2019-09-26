package pl.opegieka.it.RecruitmentTask.dao;

import pl.opegieka.it.RecruitmentTask.Model.Resource;

import java.util.List;

public interface ResourceDao<T> {
    long save(T t);

    Resource update(T t);

    void delete(long T);

    Resource findById(long T);

    List<Resource> findAll();

    int checkIfExist(String T);

    Resource findByName(String T);
}
