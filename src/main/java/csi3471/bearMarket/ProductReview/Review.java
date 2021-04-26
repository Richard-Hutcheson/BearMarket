package csi3471.bearMarket.ProductReview;

import csi3471.bearMarket.Logging.ProgLogger;

import java.util.Objects;

/**Review is an object that holds review information for each Product
 * @author Richard Hutcheson
 */
public class Review {

    private String username, description, rating;

    /**
     * @param username name of user who made review
     * @param rating rating the user gave
     * @param desc the review for the product
     */
    public Review(String username, String rating, String desc){
        ProgLogger.LOGGER.info("Review constructor called and assigning parsed data into attributes");
        this.username = username;
        this.rating = rating;
        this.description = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return username.equals(review.username) && description.equals(review.description) && rating.equals(review.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, description, rating);
    }

    @Override
    public String toString() {
        return "Review{" +
                "username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
}
