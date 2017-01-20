/**
 * Created by Yoga2pro on 10.01.2017.
 */
public interface ReviewStoringService {
    void addReview(Review review);
    boolean checkIfExists(Review review);

}
