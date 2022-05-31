package service.api;

import dto.Student;

import java.util.List;

public interface IStudentService {
    void create(Student student);

    List<Student> getAll();

    Student update(Integer id, Student student);

    void delete(Integer id);

    Student getById(Integer id);
}
