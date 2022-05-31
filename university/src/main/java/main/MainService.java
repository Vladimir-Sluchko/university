package main;

import dto.Student;
import service.StudentService;
import service.api.IStudentService;

public class MainService {
    public static void main(String[] args) {
        IStudentService iStudentService = StudentService.getInstance();
        for (Student student : iStudentService.getAll()) {
            System.out.println(student);
        }

    }
}
