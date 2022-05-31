package dao;

import dao.api.IGroupDao;
import dao.api.IStudentDao;
import dto.Group;
import dto.GroupWhithOutStudent;
import dto.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements IGroupDao {
    private static final GroupDao instance = new GroupDao();

    private static final String INSERT_QUERY = "INSERT INTO university.t_groups (name_group)\n" +
            "    VALUES (?);";
    private static final String SELECT_QUERY = "SELECT\n" +
            "    id,\n" +
            "    name_group\n" +
            "FROM\n" +
            "    university.t_groups;";
    private static final String SELECT_QUERY_BY_ID = "SELECT\n" +
            "    id,\n" +
            "    name_group\n" +
            "FROM\n" +
            "    university.t_groups\n" +
            "WHERE\n" +
            "    id = ?;\n" +
            "\n";
    private static final String UPDATE_QUERY = "UPDATE\n" +
            "    university.t_groups\n" +
            "SET\n" +
            "    name_group = ?\n" +
            "WHERE\n" +
            "    id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM university.t_groups\n" +
            "WHERE id = ?;";
    @Override
    public GroupWhithOutStudent create(GroupWhithOutStudent group) {
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
        ) {
            statement.setString(1, group.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group;
    }

    @Override
    public List<GroupWhithOutStudent> getAll() {
        List<GroupWhithOutStudent> groupList = new ArrayList<>();
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    groupList.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groupList;
    }

    @Override
    public GroupWhithOutStudent update(Integer id, GroupWhithOutStudent group) {
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            statement.setString(1, group.getName());
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group;
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GroupWhithOutStudent getById(Integer id) {
        GroupWhithOutStudent group = new GroupWhithOutStudent();
        try (Connection connection = ConnectionPoolDaoBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_BY_ID);
        ) {
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    group.setId(resultSet.getInt("id"));
                    group.setName(resultSet.getString("name_group"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group;
    }

    private GroupWhithOutStudent map(ResultSet rs) throws SQLException{
        GroupWhithOutStudent groupWhithOutStudent = new GroupWhithOutStudent();
        groupWhithOutStudent.setId(rs.getInt("id"));
        groupWhithOutStudent.setName(rs.getString("name_group"));
        return groupWhithOutStudent;
    }

    public void close() throws Exception {
        ConnectionPoolDaoBase.close();
    }

    public static GroupDao getInstance(){
        return instance;
    }
}
