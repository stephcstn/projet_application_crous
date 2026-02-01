package fr.univpoitiers.croussardsweb.controller;

import fr.univpoitiers.croussardsweb.model.Restaurant;
import fr.univpoitiers.croussardsweb.model.Review;
import fr.univpoitiers.croussardsweb.model.Student;
import fr.univpoitiers.croussardsweb.service.RestaurantService;
import fr.univpoitiers.croussardsweb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/restaurants")
    public String restaurantsPage(Model model) {
        Iterable<Restaurant> restaurants = restaurantService.getRestaurants();

        if (restaurants == null) {
            restaurants = new ArrayList<>();
        }

        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    @GetMapping("/restaurants/{id}")
    public String getRestaurant(Model model, @PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);

        if (restaurant == null) {
            return "/restaurants";
        }

        model.addAttribute("restaurant", restaurant);
        return "restaurant";
    }

   @GetMapping("/restaurants/{id}/reviews")
    public String getRestaurantReviews(Model model, @PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);

       if (restaurant == null) {
           return "/restaurants";
       }

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("reviews", restaurant.getReviews());

        return "restaurant_reviews";
    }

    @GetMapping("/restaurants/{id}/reviews/new")
    public String newReviewForm(Model model, @PathVariable Long id, HttpSession session) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        Long studentId = (Long) session.getAttribute("currentStudentId");

        if (studentId == null) {
            // L'utilisateur n'est pas connecté. Rediriger vers la page de connexion.
            return "redirect:/login";
        }

        Student student = studentService.getStudent(studentId);

        Review review = new Review();

        model.addAttribute("review", review);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("student", student);

        return "review_form";
    }
}
