package com.example.backendSpring.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @Autowired
    public StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudent();
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void removeStudent(@PathVariable("studentId") Long id){
        studentService.removeStudentById(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudentById(@PathVariable ("studentId") Long id,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String email){
        studentService.updateStudentById(id, name, email);
    }

}
