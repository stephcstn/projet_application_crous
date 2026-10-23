package fr.univpoitiers.croussardsapi.repository;

import fr.univpoitiers.croussardsapi.model.Restaurant;
import fr.univpoitiers.croussardsapi.model.Review;
import fr.univpoitiers.croussardsapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Iterable<Review> findByRestaurantId(Long id);
    Iterable<Review> findByStudentId(Long id);
}
