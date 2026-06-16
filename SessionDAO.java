/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/driversmart_db";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = ""; 
    
    private static final String INSERT_STUDENTS_SQL = "INSERT INTO students (name, email, position) VALUES (?, ?, ?);";
    private static final String SELECT_STUDENTS_BY_ID = "select id, name, position from students where id =?";
    private static final String SELECT_ALL_STUDENTS = "select * from students";
    private static final String DELETE_STUDENTS_SQL = "delete from students where id =?";
    private static final String UPDATE_STUDENTS_SQL = "update students set name =?, email =?, position=? where id =?;";

    public SessionDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
        }
        return connection;
    }

    public void insertStudents(Student students) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
            preparedStatement.setString(1, students.getsession_id());
            preparedStatement.setString(2, students.getname());
            preparedStatement.setString(3, students.getPosition());
            preparedStatement.executeUpdate();
        } catch (SQLException e) { printSQLException(e); }
    }

    public Student selectstudents(int id) {
        Student students = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String branch = rs.getString("location");
                String lesson = rs.getString("lesson");
                students = new student(id, name, location, lesson_type);
            }
        } catch (SQLException e) { printSQLException(e); }
        return students;
    }

    public List<Student> selectAllStudent() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String branch = rs.getString("position");
                students.add(new students(id, name, branch, session));
            }
        } catch (SQLException e) { printSQLException(e); }
        return students;
    }

    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        String DELETE_STUDENT_SQL = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student, String UPDATE_STUDENT_SQL) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getLocation());
            statement.setString(4, student.getLesson());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) { e.printStackTrace(System.err); }
        }
    }
}
