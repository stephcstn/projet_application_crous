package fr.univpoitiers.croussardsapi.repository;

import fr.univpoitiers.croussardsapi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
}
