package pl.opegieka.it.RecruitmentTask.dao;

public interface CardToPermisionGroupDao<T, C> {

    int findIfExist(Long T, Long C);

    void delete(long T, long C);
}
