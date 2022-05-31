package main;

import dao.GroupDao;
import dao.api.IGroupDao;
import dto.GroupWhithOutStudent;

import java.util.List;

public class MainGroup {
    public static void main(String[] args) {
        IGroupDao groupDao = GroupDao.getInstance();
        GroupWhithOutStudent group = new GroupWhithOutStudent();
        group.setId(2);
        group.setName("Пустая группа");
        //groupDao.create(group);
        System.out.println("___Список групп до добавления___");
        List<GroupWhithOutStudent> groupWhithOutStudents = groupDao.getAll();
        for (GroupWhithOutStudent groupWhithOutStudent : groupWhithOutStudents) {
            System.out.println(groupWhithOutStudent);
        }
        System.out.println("________________________________");
        //groupDao.delete(2);


        System.out.println("___Список групп после добавления___");
        //groupDao.create(group);
        for (GroupWhithOutStudent groupWhithOutStudent : groupDao.getAll()) {
            System.out.println(groupWhithOutStudent);
        }
        System.out.println("выбраная группа");
        System.out.println(groupDao.getById(4));

        /*GroupWhithOutStudent group2 = new GroupWhithOutStudent();
        group2.setId(2);
        group2.setName("MKKKKKK");
        groupDao.update(3,group2);
        System.out.println("________________");*/

        /*List<GroupWhithOutStudent> groupWhithOutStudents1 = groupDao.get();
        for (GroupWhithOutStudent groupWhithOutStudent : groupWhithOutStudents1) {
            System.out.println(groupWhithOutStudent);
        }*/


    }



}
