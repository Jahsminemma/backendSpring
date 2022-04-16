package com.example.backendSpring.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.log4j2.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

     StudentService(StudentRepository studentRepository) {
         this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> existingStudent = studentRepository.findStudentByEmail(student.getEmail());
        if(existingStudent.isPresent()){
            throw new IllegalStateException("user exists");
        }
        studentRepository.save(student);
    }

    public void removeStudentById(Long studentId){
         boolean exists = studentRepository.existsById(studentId);
         if(!exists){
             throw new IllegalStateException(("user does not exist"));
         }
         studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudentById(Long studentId, String name, String email) {
         Student student = studentRepository.findById(studentId)
                 .orElseThrow(() -> new IllegalStateException("user does not exist"));

         if(name != null && name.length() > 3
         && !Objects.equals(student.getName(), name)){
             student.setName(name);
         }
         if(email != null && email.length() > 0 &&
            !Objects.equals(student.getEmail(), email)){
             Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
             if(optionalStudent.isPresent()){
                 throw new IllegalStateException("email already taken");
             }
             student.setEmail(email);
        }
    }
}
