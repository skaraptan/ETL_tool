import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Yoga2pro on 23.01.2017.
 */
public class ReviewsParsingService{
    private ReviewParsingService reviewParsingService;
    private ArrayList<Review> reviewsList = new ArrayList<Review>();
    public ArrayList<Review> listReviews(Elements elements, Product product){
        for(Element element : elements) {
            reviewParsingService = new ReviewParsingServiceImpl(element, product);
            reviewsList.add(reviewParsingService.getReviewAsAnObject());
            }
        return reviewsList;
        }
}
