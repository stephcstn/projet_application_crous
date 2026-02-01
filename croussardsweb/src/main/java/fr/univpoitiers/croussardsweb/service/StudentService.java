package fr.univpoitiers.croussardsweb.service;

import fr.univpoitiers.croussardsweb.model.Student;
import fr.univpoitiers.croussardsweb.repository.StudentProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentProxy studentProxy;

    public Student getStudent(Long id) {
        return studentProxy.getStudent(id);
    }

    public Iterable<Student> getStudents() {
        return studentProxy.getStudents();
    }

    public Student saveStudent(Student student) {
        Student savedStudent;

        student.setLastName(student.getLastName().toUpperCase());
        student.setFirstName(student.getFirstName().toUpperCase());

        if (student.getNumStudent() == null) {
            savedStudent = studentProxy.createStudent(student);
        } else {
            savedStudent = studentProxy.updateStudent(student);
        }

        return savedStudent;
    }

    public Student loginStudent(String username, String password) {
        return studentProxy.authenticate(username, password);
    }


}



