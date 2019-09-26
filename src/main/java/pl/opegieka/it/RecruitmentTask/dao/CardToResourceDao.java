package pl.opegieka.it.RecruitmentTask.dao;

public interface CardToResourceDao<T, C> {

    int findIfExist(long T, long C);

    void delete(long T, long C);
}
