package service.api;

import dao.StudentInTheGroupDao;
import dao.api.IStudentInTheGroupDao;
import dto.Group;
import dto.Student;

import java.util.List;

public interface IStudInTheGroupServive extends IStudentInTheGroupDao {


    @Override
    void create(List<Student> studentList, Integer idGroup);

    @Override
    Group getAll(Integer idGroup);

    @Override
    void update(Integer idStudent, Integer idGroup);

    @Override
    void delete(Integer idGroup);
}
