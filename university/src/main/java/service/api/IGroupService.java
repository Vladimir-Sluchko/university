package service.api;

import dao.api.IGroupDao;
import dto.GroupWhithOutStudent;

import java.util.List;

public interface IGroupService extends IGroupDao {
    @Override
    GroupWhithOutStudent create(GroupWhithOutStudent group);

    @Override
    List<GroupWhithOutStudent> getAll();

    @Override
    GroupWhithOutStudent update(Integer integer, GroupWhithOutStudent group);

    @Override
    void delete(Integer id);

    @Override
    GroupWhithOutStudent getById(Integer id);
}
