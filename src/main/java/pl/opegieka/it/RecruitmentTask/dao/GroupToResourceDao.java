package pl.opegieka.it.RecruitmentTask.dao;

public interface GroupToResourceDao<T,C> {
    int findIfExist(Long groupId, Long resourceId);

    void delete(long groupId, long resourceId);
}
