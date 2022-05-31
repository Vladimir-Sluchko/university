package main;

import dao.StudentDao;
import dao.StudentInTheGroupDao;
import dao.api.IStudentDao;
import dao.api.IStudentInTheGroupDao;
import dto.Student;

import java.util.ArrayList;
import java.util.List;

public class MainStudentInGroup {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();

        IStudentDao studentDao = StudentDao.getInstance();
        studentList.add(studentDao.getById(7));
        studentList.add(studentDao.getById(6));

        StudentInTheGroupDao studentInTheGroupDao = StudentInTheGroupDao.getInstance();
        studentInTheGroupDao.create(studentList,6);
    }
}
