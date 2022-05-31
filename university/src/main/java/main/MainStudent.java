package main;

import dao.StudentDao;
import dao.StudentInTheGroupDao;
import dao.api.IStudentDao;
import dto.Student;

import java.sql.SQLOutput;
import java.util.List;
import java.util.SortedMap;

public class MainStudent {
    public static void main(String[] args) {
        IStudentDao studentDao = StudentDao.getInstance();


        Student studentNew = Student.Builder.create()
                .setName("Александр Случко")
                .setAge(6)
                .setScore(9)
                .setOlympicGamer(false)
                .build();

        //studentDao.update(7,studentNew);
        //studentDao.create(studentNew);

        List<Student> studentList = studentDao.getAll();
        for (Student student : studentList) {
            System.out.println(student);
        }
        System.out.println("______");
        System.out.println(studentDao.getById(6));;

       /* StudentInTheGroupDao s = StudentInTheGroupDao.getInstance();
        s.create(studentList,2);*/




    }

}
