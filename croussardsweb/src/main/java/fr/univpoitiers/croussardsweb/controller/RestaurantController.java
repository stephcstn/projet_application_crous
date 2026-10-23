package fr.univpoitiers.croussardsweb.controller;

import fr.univpoitiers.croussardsweb.model.Restaurant;
import fr.univpoitiers.croussardsweb.model.Review;
import fr.univpoitiers.croussardsweb.model.Student;
import fr.univpoitiers.croussardsweb.service.RestaurantService;
import fr.univpoitiers.croussardsweb.service.ReviewService;
import fr.univpoitiers.croussardsweb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private ReviewService reviewService;

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
        Iterable<Review> reviews = reviewService.getReviewsByRestaurantId(id);
       if (restaurant == null) {
           return "/restaurants";
       }

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("reviews", reviews);

        return "restaurant_reviews";
    }

    @GetMapping("/restaurants/{id}/reviews/new")
    public String newReviewForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute("currentStudentId");
        if (studentId == null) {
            return "redirect:/login";
        }

        Restaurant restaurant = restaurantService.getRestaurant(id);
        Student student = studentService.getStudent(studentId);

        Review review = new Review();

        model.addAttribute("review", review);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("student", student);

        return "review_form";
    }

    @PostMapping("/reviews/save")
    public String saveReview(@ModelAttribute("review") Review review,
                             @RequestParam("restaurantId") Long restaurantId,
                             HttpSession session) {
        Long studentId = (Long) session.getAttribute("currentStudentId");
        if (studentId == null) {
            return "redirect:/login";
        }

        // Réaffecter les entités ou IDs avant l'envoi vers l'API
        reviewService.createReview(review, restaurantId, studentId);

        // Redirige vers la page du restaurant
        return "redirect:/restaurants/" + restaurantId;
    }
}
