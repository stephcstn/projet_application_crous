package fr.univpoitiers.croussardsweb.repository;

import fr.univpoitiers.croussardsweb.configuration.CustomProperties;
import fr.univpoitiers.croussardsweb.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class StudentProxy {

    @Autowired
    private CustomProperties customProperties;

    public Iterable<Student> getStudents() {
        String baseApiUrl = customProperties.getApiUrl();
        String getStudentsUrl = baseApiUrl + "/students";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Student>> response = restTemplate.exchange(
                getStudentsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        log.debug("Get Students call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Student getStudent(Long id) {
        String baseApiUrl = customProperties.getApiUrl();
        String getStudentUrl = baseApiUrl + "/students/" + id.intValue();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Student> response = restTemplate.exchange(
                getStudentUrl,
                HttpMethod.GET,
                null,
                Student.class
        );

        log.debug("Get Student call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deleteStudent(final int id) {
        String baseApiUrl = customProperties.getApiUrl();
        String deleteStudentUrl = baseApiUrl + "/students/" + id;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(deleteStudentUrl);

        log.debug("Delete Student call");
    }

    public Student createStudent(Student student) {
        String baseApiUrl = customProperties.getApiUrl();
        String createStudentUrl = baseApiUrl + "/students";

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Student> request = new HttpEntity<>(student);

        ResponseEntity<Student> response = restTemplate.exchange(
                createStudentUrl,
                HttpMethod.POST,
                request,
                Student.class
        );
        log.debug("Create Student call " + response.getStatusCode().toString());
        return response.getBody();
    }

    public Student updateStudent(Student student) {
        String baseApiUrl = customProperties.getApiUrl();
        String updateStudentUrl = baseApiUrl + "/students/" + student.getNumStudent();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(updateStudentUrl, student);

        log.debug("Update Student call");

        return student;
    }

    public Student authenticate(String mail, String pwdHash) {

        String authenticateUrl = customProperties.getApiUrl() + "/students/authenticate";

        Student loginData = new Student();
        loginData.setMail(mail);
        loginData.setPwdHash(pwdHash);

        HttpEntity<Student> request = new HttpEntity<>(loginData);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Student> response = restTemplate.exchange(
                authenticateUrl,
                HttpMethod.POST,
                request,
                Student.class
        );
        log.debug("Authenticate call " + response.getStatusCode().toString());
        return response.getBody();
    }

}
