package dao.api;

import dto.Group;
import dto.GroupWhithOutStudent;
import dto.Student;

import java.util.List;

public interface IGroupDao extends ICRUDao<GroupWhithOutStudent,Integer>{
    @Override
    GroupWhithOutStudent create(GroupWhithOutStudent group);

    @Override
    List<GroupWhithOutStudent> getAll();

    @Override
    GroupWhithOutStudent update(Integer integer, GroupWhithOutStudent group);

    @Override
    void delete(Integer id);

    GroupWhithOutStudent getById(Integer id);
}
