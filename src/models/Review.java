package models;

public class Review {

    private Tourist tourist;
    private int rating;
    private String comment;

    public Review(Tourist tourist, int rating, String comment) {
        this.tourist = tourist;
        this.rating = rating;
        this.comment = comment;
    }

    public Tourist getTourist() {
        return tourist;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return tourist.getName() + " | " + rating + " | " + comment;
    }
}