package com.example.backendSpring.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student alex = new Student(
                    "Okonkwo Emmanuel",
                    "emma@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 25)
            );
            Student yunus = new Student(
                    "Yunus Yakub",
                    "emma@gmail.com",
                    LocalDate.of(1997, Month.DECEMBER, 25)
            );
            repository.saveAll(
                    List.of(alex, yunus)
            );
        };
    }
}
