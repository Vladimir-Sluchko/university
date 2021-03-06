package dao.api;

import dto.Student;

import java.util.List;

public interface IStudentDao extends ICRUDao<Student,Integer>{
    @Override
    Student create(Student student);

    @Override
    List<Student> getAll();

    @Override
    Student update(Integer id, Student student);

    @Override
    void delete(Integer id);
    Student getById(Integer id);
}
