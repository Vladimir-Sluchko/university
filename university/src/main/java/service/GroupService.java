package service;

import dao.GroupDao;
import dao.api.IGroupDao;
import dto.Group;
import dto.GroupWhithOutStudent;
import service.api.IGroupService;

import java.util.List;

public class GroupService implements IGroupService {
    private static final GroupService instance = new GroupService();
    IGroupDao groupDao = GroupDao.getInstance();
    @Override
    public GroupWhithOutStudent create(GroupWhithOutStudent group) {
        return groupDao.create(group);
    }

    @Override
    public List<GroupWhithOutStudent> getAll() {
        return groupDao.getAll();
    }

    @Override
    public GroupWhithOutStudent update(Integer integer, GroupWhithOutStudent group) {
        return groupDao.update(integer,group);
    }

    @Override
    public void delete(Integer id) {
        groupDao.delete(id);

    }

    @Override
    public GroupWhithOutStudent getById(Integer id) {
       return groupDao.getById(id);
    }

    public static GroupService getInstance(){
        return instance;
    }
}
