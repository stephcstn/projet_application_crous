document.addEventListener('DOMContentLoaded', () => {
    fetchRestaurants();
    fetchReviews();
    fetchStudents();
});

function fetchRestaurants() {
    // Appel à l'API REST de votre Back-end
    fetch('/api/restaurants') 
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur réseau ou du serveur');
            }
            return response.json(); // Convertit la réponse en objet JavaScript
        })
        .then(restaurants => {
            const listElement = document.getElementById('restaurant-list');
            
            // Boucle sur les données reçues
            restaurants.forEach(resto => {
                const listItem = document.createElement('li');
                listItem.textContent = `${resto.nom} (${resto.typeRestau}) - ${resto.adresse}`;
                listElement.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Erreur lors de la récupération des restaurants:', error);
            document.getElementById('restaurant-list').textContent = "Impossible de charger les données.";
        });
}
function fetchStudents() {
    fetch('/api/students') 
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur réseau ou du serveur');
            }
            return response.json(); 
        })
        .then(students => {
            const listElement = document.getElementById('student-list');
            
            students.forEach(student => {
                const listItem = document.createElement('li');
                listItem.textContent = `${student.name} - ${student.email}`;
                listElement.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Erreur lors de la récupération des étudiants:', error);
            document.getElementById('student-list').textContent = "Impossible de charger les données.";
        });
}
function fetchReviews() {
    fetch('/api/reviews') 
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur réseau ou du serveur');
            }
            return response.json(); 
        })
        .then(reviews => {
            const listElement = document.getElementById('review-list');
            
            reviews.forEach(review => {
                const listItem = document.createElement('li');
                listItem.textContent = `Restaurant ID: ${review.restaurantId} - Note: ${review.rating} - Commentaire: ${review.comment}`;
                listElement.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Erreur lors de la récupération des avis:', error);
            document.getElementById('review-list').textContent = "Impossible de charger les données.";
        });
}
