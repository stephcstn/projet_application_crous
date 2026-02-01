package fr.univpoitiers.croussardsapi;


import fr.univpoitiers.croussardsapi.model.Restaurant;
import fr.univpoitiers.croussardsapi.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RestaurantRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void whenFindById_thenReturnRestaurant() {
        // nouveau restaurant à sauvegarder
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Le Crous'tille");
        restaurant.setAddress("123 Rue de la Faim, Poitiers");
        restaurant.setTypeResto("Universitaire");
        entityManager.persistAndFlush(restaurant);

        // retrouver le restaurant par son ID
        Optional<Restaurant> found = restaurantRepository.findById(restaurant.getIdResto());

        //Vérifie que le restaurant a été trouvé et que ses données sont correctes
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(restaurant.getName());
        assertThat(found.get().getAddress()).isEqualTo(restaurant.getAddress());
    }
}
