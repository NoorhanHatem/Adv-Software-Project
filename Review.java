package project;

public class Review {
	private int reviewID;
	private String guestUsername;
	private int rating;
	private String rev;
	
	Review(int reviewID,String guestUsername,int rating,String rev) {
		this.setReviewID(reviewID);
		this.setGuestUsername(guestUsername);
		this.setRating(rating);
		this.setRev(rev);
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public String getGuestUsername() {
		return guestUsername;
	}

	public void setGuestUsername(String guestUsername) {
		this.guestUsername = guestUsername;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}
}
