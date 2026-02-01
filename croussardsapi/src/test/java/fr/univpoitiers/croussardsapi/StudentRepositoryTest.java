package fr.univpoitiers.croussardsapi;

import fr.univpoitiers.croussardsapi.model.Student;
import fr.univpoitiers.croussardsapi.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void whenSaved_thenCanBeFoundById() {
        // nouvel étudiant à sauvegarder
        Student student = new Student();
        student.setUsername("jdoe");
        student.setLastName("Doe");
        student.setFirstName("John");
        student.setMail("john.doe@example.com");
        student.setPwdHash("hashed_password"); // mot de passe haché
        entityManager.persistAndFlush(student);

        // retrouver l'étudiant par son ID
        Optional<Student> foundStudent = studentRepository.findById(student.getNumStudent());

        // l'etudiant est trouvé et ses données sont correctes
        assertThat(foundStudent).isPresent();
        assertThat(foundStudent.get().getUsername()).isEqualTo("jdoe");
        assertThat(foundStudent.get().getMail()).isEqualTo(student.getMail());
    }
}