package dao;

import dao.api.IStudentDao;
import dto.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IStudentDao {
    private static final StudentDao instance = new StudentDao();
    private static final String INSERT_QUERY = "INSERT INTO university.student (name, age, score, olympic_gamer)\n" +
            "    VALUES (?, ?, ?, ?);";
    private static final String SELECT_QUERY = "SELECT\n" +
            "    id,\n" +
            "    name,\n" +
            "    age,\n" +
            "    score,\n" +
            "    olympic_gamer\n" +
            "FROM\n" +
            "    university.student;\n" +
            "\n";
    private static final String SELECT_QUERY_BY_ID = "SELECT\n" +
            "    id,\n" +
            "    name,\n" +
            "    age,\n" +
            "    score,\n" +
            "    olympic_gamer\n" +
            "FROM\n" +
            "    university.student\n" +
            "WHERE\n" +
            "    id = ?;\n" +
            "\n";
    private static final String UPDATE_QUERY = "UPDATE\n" +
            "    university.student\n" +
            "SET\n" +
            "    name = ?,\n" +
            "    age = ?,\n" +
            "    score = ?,\n" +
            "    olympic_gamer = ?\n" +
            "WHERE\n" +
            "    id = ?;\n" +
            "\n";
    private static final String DELETE_QUERY = "DELETE FROM university.student\n" +
            "WHERE id = ?;\n";
    @Override
    public Student create(Student student) {
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        ) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getScore());
            statement.setBoolean(4, student.isOlympicGamer());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List getAll() {
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    studentList.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }


    @Override
    public Student update(Integer id, Student student) {
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getScore());
            statement.setBoolean(4, student.isOlympicGamer());
            statement.setInt(5,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public void delete(Integer integer) {
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        ) {
            statement.setInt(1, integer);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Student map(ResultSet rs) throws SQLException{
        return Student.Builder.create()
                .setId( rs.getInt("id"))
                .setName(rs.getString("name"))
                .setAge(rs.getInt("age"))
                .setScore(rs.getDouble("score"))
                .setOlympicGamer(rs.getBoolean("olympic_gamer"))
                .build();
    }

    @Override
    public Student getById(Integer id) {
        Student student = new Student();
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_ID);
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    student = Student.Builder.create().setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setAge(resultSet.getInt("age"))
                            .setScore(resultSet.getDouble("score"))
                            .setOlympicGamer(resultSet.getBoolean("olympic_gamer")).build();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    public void close() throws Exception {
        ConnectionPoolDaoBase.close();
    }
    public static StudentDao getInstance(){
        return instance;
    }
}
