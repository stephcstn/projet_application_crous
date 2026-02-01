package fr.univpoitiers.croussardsweb.controller;

import fr.univpoitiers.croussardsweb.model.Student;
import fr.univpoitiers.croussardsweb.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    public String studentsPage(Model model) {
        Iterable<Student> students = studentService.getStudents();

        if (students == null) {
            students = new ArrayList<>();
        }

        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/students/{id}")
    public String getStudent(Model model, @PathVariable Long id) {
        Student student = studentService.getStudent(id);

        if (student == null) {
            return "/students";
        }

        model.addAttribute("student", student);
        return "student";
    }

    @GetMapping("/students/{id}/reviews")
    public String getStudentReviews(Model model, @PathVariable Long id) {
        Student student = studentService.getStudent(id);

        if (student == null) {
            return "/students";
        }

        model.addAttribute("student", student);
        model.addAttribute("reviews", student.getReviews());

        return "student_reviews";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String pwdHash,
                               HttpSession session,
                               Model model) {

        Student student = studentService.loginStudent(username, pwdHash);

        if (student != null) {
            session.setAttribute("currentStudentId", student.getNumStudent());
            session.setAttribute("currentUsername", student.getUsername());

            return "redirect:/";
        } else {
            model.addAttribute("loginError", "Email ou mot de passe incorrect.");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/login";
    }
}
