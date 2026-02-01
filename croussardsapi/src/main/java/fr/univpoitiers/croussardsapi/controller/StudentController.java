package fr.univpoitiers.croussardsapi.controller;

import fr.univpoitiers.croussardsapi.model.Review;
import fr.univpoitiers.croussardsapi.model.Student;
import fr.univpoitiers.croussardsapi.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        Student s = studentService.getStudent(id);
        return studentService.myResponse(s);
    }

    @GetMapping
    public ResponseEntity<?> getStudents() {
        return studentService.myResponse(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        Student updateStudent = studentService.getStudent(id);
        studentService.updateStudent(updateStudent, student);
        studentService.saveStudent(updateStudent);
        return studentService.myResponse(updateStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<?> getReviewsByStudent(@PathVariable Long id) {
        Iterable<Review> reviews = studentService.getReviews(id);
        return studentService.myResponse(reviews);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Student> authenticateStudent(@RequestBody Student loginData) {

        Student authenticatedStudent = studentService.authenticate(
                loginData.getUsername(),
                loginData.getPwdHash()
        );

        if (authenticatedStudent != null) {
            return new ResponseEntity<>(authenticatedStudent, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
        }
    }
}
