package dao;


import dao.api.IStudentInTheGroupDao;
import dto.Group;
import dto.GroupWhithOutStudent;
import dto.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentInTheGroupDao implements IStudentInTheGroupDao {
    private static final StudentInTheGroupDao instance = new StudentInTheGroupDao();

    private static final String INSERT_QUERY = "INSERT INTO university.students_in_the_group (group_id, student_id)\n" +
            "    VALUES (?, ?);";
    private static final String SELECT_QUERY = "SELECT \n" +
            "\tstudent.id, \n" +
            "\tstudent.name, \n" +
            "\tstudent.age, \n" +
            "\tstudent.score, \n" +
            "\tstudent.olympic_gamer,\n" +
            "\tt_groups.name_group\n" +
            "\t\n" +
            "FROM university.student\n" +
            "JOIN university.students_in_the_group ON student.id=students_in_the_group.student_id\n" +
            "JOIN university.t_groups ON t_groups.id=students_in_the_group.group_id\n" +
            "WHERE t_groups.id = ?;";
    private static final String UPDATE_QUERY = "UPDATE university.students_in_the_group\n" +
            "\tSET group_id = ?\n" +
            "\tWHERE student_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM university.students_in_the_group\n" +
            "\tWHERE group_id = ?;";



    public void create(List<Student> studentList, Integer idGroup) {
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        ) {
            for (Student student : studentList) {
                statement.setInt(1, idGroup);
                statement.setInt(2,student.getId());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public Group getAll(Integer idGroup) {
        Group group = new Group();
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
        ) {
            statement.setInt(1,idGroup);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    group.setName(resultSet.getString("name_group"));
                    studentList.add(map(resultSet));
                    group.setStudentList(studentList);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group;
    }


    public void update(Integer idStudent, Integer idGroup) {
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            statement.setInt(1, idGroup);
            statement.setInt(2,idStudent);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Integer idGroup) {
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        ) {
            statement.setInt(1, idGroup);
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

    public void close() throws Exception {
        ConnectionPoolDaoBase.close();
    }
    public static StudentInTheGroupDao getInstance() {
        return instance;
    }
}
