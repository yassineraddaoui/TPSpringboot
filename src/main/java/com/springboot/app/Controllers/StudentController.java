package com.springboot.app.Controllers;

import com.springboot.app.Entities.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @GetMapping("/query")
    public Student studentQueryParam(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName) {
        return new Student(firstName, lastName);
    }

    @GetMapping("/{firstName}/{lastName}")
    public Student studentPathVariable(@PathVariable String firstName, @PathVariable String lastName) {
        return Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    @GetMapping
    public Student getStudent() {
        return Student.builder()
                .firstName("yassine")
                .lastName("raddaoui")
                .build();
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return List.of(
                new Student("Ramesh", "Fadatare"),
                new Student("Tony", "Cena"),
                new Student("Sanjay", "Jadhav"),
                new Student("Ram", "Jadhav"),
                new Student("Umesh", "Fadatare")
        );
    }
}
