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
        student.setLastName(student.getLastName().toUpperCase());
        student.setFirstName(student.getFirstName().toUpperCase());

        if (student.getId() == null) {
            return studentProxy.createStudent(student);
        } else {
            return studentProxy.updateStudent(student);
        }
    }

    public Student loginStudent(String username, String password) {
        return studentProxy.login(username, password);
    }


}



