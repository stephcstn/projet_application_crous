package fr.univpoitiers.croussardsapi.repository;

import fr.univpoitiers.croussardsapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
