package com.ncgroup.org.controllers;

import com.ncgroup.org.models.Student;
import com.ncgroup.org.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    //path - урла, по которой будет делаться запрос
    //method - HTTP метод
    @RequestMapping(path = "/api/v1/frontend-api/students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.getStudents();

        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    //При помощи аннотации @RequestBody Spring автоматически из JSON переводит в JAVA объект Student
    @RequestMapping(path = "/api/v1/frontend-api/students", method = RequestMethod.POST)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);

        return new ResponseEntity<Student>(HttpStatus.CREATED);
    }
}
