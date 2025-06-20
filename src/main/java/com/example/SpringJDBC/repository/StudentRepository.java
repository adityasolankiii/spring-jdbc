package com.example.SpringJDBC.repository;

import com.example.SpringJDBC.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student student) {

        String sql = "INSERT INTO STUDENT(ROLLNO, NAME, MARKS) VALUES(?,?,?)";

        int rows = jdbc.update(sql,student.getRollNo(), student.getName(), student.getMarks());

        System.out.println(rows + "Rows Effects");
    }

    public List<Student> findAll() {

        String sql = "SELECT * FROM STUDENT";

//        RowMapper<Student> mapper = new RowMapper<Student>() {
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//                Student s = new Student();
//                s.setRollNo(rs.getInt("ROLLNO"));
//                s.setName(rs.getString("NAME"));
//                s.setMarks(rs.getInt("MARKS"));
//
//                return s;
//            }
//        };
//
//        return jdbc.query(sql,mapper);

        return jdbc.query(sql,( rs,  rowNum) -> {

            Student s = new Student();
            s.setRollNo(rs.getInt("ROLLNO"));
            s.setName(rs.getString("NAME"));
            s.setMarks(rs.getInt("MARKS"));

            return s;
        });
    }
}
