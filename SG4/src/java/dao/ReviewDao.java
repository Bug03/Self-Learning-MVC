package dao;

import java.util.List;

import model.Review;

public interface ReviewDao {
	void insert(Review review);

	void edit(Review review);

	void delete(int id);

	Review get(int id);
	
	Review get(String name);

	List<Review> getAll();
	
	List<Review> getReviewById(int id);
        
        int getTotalReview();
        
        List<Review> paging(int index);
}
