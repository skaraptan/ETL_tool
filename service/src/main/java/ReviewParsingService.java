import org.jsoup.nodes.Element;

/**
 * Created by Yoga2pro on 23.11.2016.
 */
public interface ReviewParsingService {
    void setProduct(Product product);
    void setElement(Element element);
    String getPros();
    String getCons();
    String getConclusion();
    String getStarsRate();
    String isRecommended();
    UsefulnessRate getUsefulnessRates();
    Review getReviewAsAnObject();
}
