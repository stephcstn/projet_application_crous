package fr.univpoitiers.croussardsapi;

import fr.univpoitiers.croussardsapi.model.Restaurant;
import fr.univpoitiers.croussardsapi.model.Review;
import fr.univpoitiers.croussardsapi.model.Student;
import fr.univpoitiers.croussardsapi.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void whenSaved_thenCanBeFoundByIdWithRelationships() {
        // nouvel étudiant et nouveau restaurant à sauvegarder
        Student student = new Student();
        student.setUsername("testuser");
        student.setLastName("Joe");
        student.setFirstName("John");
        student.setMail("john.doe@example.com");
        student.setPwdHash("hashed_password");
        entityManager.persist(student);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Le Crous'tille");
        entityManager.persist(restaurant);

        // nouvelle review à sauvegarder
        Review review = new Review();
        review.setStudent(student);
        review.setRestaurant(restaurant);
        review.setNbStars(4);
        review.setComments("super bon!");
        review.setDatePubli(LocalDate.from(LocalDateTime.now()));
        entityManager.persistAndFlush(review);

        // retrouver la review par son ID et vérifier les relations
        Optional<Review> foundReview = reviewRepository.findById(review.getIdReview());

        // la review est trouvée et ses données sont correctes
        assertThat(foundReview).isPresent();
        assertThat(foundReview.get().getComments()).isEqualTo("super bon!");
        assertThat(foundReview.get().getStudent().getNumStudent()).isEqualTo(student.getNumStudent());
        assertThat(foundReview.get().getRestaurant().getIdResto()).isEqualTo(restaurant.getIdResto());
    }
}