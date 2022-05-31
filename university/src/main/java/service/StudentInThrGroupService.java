package service;

import dao.StudentInTheGroupDao;
import dao.api.IStudentInTheGroupDao;
import dto.Group;
import dto.Student;
import service.api.IStudInTheGroupServive;

import java.util.List;

public class StudentInThrGroupService implements IStudInTheGroupServive {
    private static StudentInThrGroupService instance = new StudentInThrGroupService();

    private final IStudentInTheGroupDao studentByGroup = StudentInTheGroupDao.getInstance();

    @Override
    public void create(List<Student> studentList, Integer idGroup) {
        studentByGroup.create(studentList,idGroup);

    }

    @Override
    public Group getAll(Integer idGroup) {
        return studentByGroup.getAll(idGroup);
    }

    @Override
    public void update(Integer idStudent, Integer idGroup) {
        studentByGroup.update(idStudent,idGroup);
    }

    @Override
    public void delete(Integer idGroup) {
        studentByGroup.delete(idGroup);

    }

    public static StudentInThrGroupService getInstance(){
        return instance;
    }
}
