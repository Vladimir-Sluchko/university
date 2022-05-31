package service;

import dao.StudentDao;
import dao.api.IStudentDao;
import dto.Student;
import service.api.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {
    private final static StudentService instance = new StudentService();
    private IStudentDao studentDao = StudentDao.getInstance();
    @Override
    public void create(Student student) {
        studentDao.create(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public Student update(Integer id, Student student) {
        return studentDao.update(id,student);
    }

    @Override
    public void delete(Integer id) {
        studentDao.delete(id);

    }

    @Override
    public Student getById(Integer id) {
        return studentDao.getById(id);
    }

    public static StudentService getInstance(){
        return instance;
    }
}
