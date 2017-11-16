package com.ncgroup.org.services;

import com.ncgroup.org.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRowMapper studentRowMapper = new StudentRowMapper();

    //Класс, который нужен для отображения результатов выборки таблицы в Java объект
    private static class StudentRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();

            student.setName(resultSet.getString("name"));
            student.setAge(resultSet.getInt("age"));

            return student;
        }
    }

    public List<Student> getStudents() {
        String sql = "SELECT * FROM student";

        return jdbcTemplate.query(sql, studentRowMapper);
    }

    //Метод jdbcTemplate.update используется для вставки, удаления и обновления
    public void createStudent(Student student) {
        String sql = "INSERT INTO student (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getAge());
    }
}
