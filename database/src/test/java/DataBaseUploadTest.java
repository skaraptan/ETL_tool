import com.jaunt.ResponseException;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by Yoga2pro on 11.01.2017.
 */
public class DataBaseUploadTest {
        public static void main(String[] args) throws IOException, ResponseException {
            HtmlUploadService service = new HtmlUploadService();
            ElementsGettingService rgs = new ElementsGettingService(service.getFileFromWeb("http://www.ceneo.pl/44316403"));
            ProductParsingService pps = new ProductParsingServiceImpl(rgs.getAllElements().get(0));
            ProductStoringService pss = new ProductStoringServiceImpl();
            Product product;
            pss.addProduct(product = pps.getProductAsAnObject());
            ReviewStoringService rss = new ReviewStoringServiceImpl();
            for(Element e : rgs.getReviews()) {
                ReviewParsingService ps = new ReviewParsingServiceImpl(e, product);
                rss.addReview(ps.getReviewAsAnObject());
            }
        }
    }
