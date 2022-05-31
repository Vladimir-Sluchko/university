package dao.api;

import dto.Group;
import dto.Student;

import java.util.List;

public interface IStudentInTheGroupDao {
    void create(List<Student> studentList, Integer idGroup);

    Group getAll(Integer idGroup);

    void update(Integer idStudent, Integer idGroup);

    void delete(Integer idGroup);
}
