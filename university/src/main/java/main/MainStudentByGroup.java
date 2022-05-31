package main;

import dao.StudentInTheGroupDao;
import dto.Group;
import dto.Student;

public class MainStudentByGroup {
    public static void main(String[] args) {
        StudentInTheGroupDao studentDao = StudentInTheGroupDao.getInstance();
        //studentDao.update(7,5);
        Group group = studentDao.getAll(4);
        System.out.println(group);
        System.out.println(group.getName());
        for (Student student : group.getStudentList()) {
            System.out.println(student);
        }



    }
}
