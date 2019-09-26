package pl.opegieka.it.RecruitmentTask.dao;

import pl.opegieka.it.RecruitmentTask.Model.Card;

import java.util.List;

public interface CardDao<T> {
    long save(T t);

    public Card update(T t);

    void delete(long T);

    Card findById(long T);

    List<T> findAll();

    int checkIfExist(int T);

    Card findByCardNumber(int T);
}
