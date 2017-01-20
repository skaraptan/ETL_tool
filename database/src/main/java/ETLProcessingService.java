import com.jaunt.ResponseException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Yoga2pro on 16.01.2017.
 */
public class ETLProcessingService {
    private String url;
    private String productCode;

    public ETLProcessingService(String url){
        this.url = url;
        this.productCode = url.replaceFirst("http://www.ceneo.pl/", "");
    }

    public Elements extractProduct(){
        Elements elements = null;
        try {
            HtmlUploadService htmlUploadService = new HtmlUploadService();
            ElementsGettingService elementsGettingService = new ElementsGettingService(htmlUploadService.getFileFromWeb(url));
            elements = elementsGettingService.getAllElements();
        } catch (ResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return elements;
        }
    }
    public Elements extractReviews(){
        Elements elements = null;
        try {
            HtmlUploadService htmlUploadService = new HtmlUploadService();
            ElementsGettingService elementsGettingService = new ElementsGettingService(htmlUploadService.getFileFromWeb(url));
            elements = elementsGettingService.getReviews();
        } catch (ResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return elements;
        }
    }
    public Product transformProduct(Elements elements){
        ProductParsingService productParsingService = new ProductParsingServiceImpl(elements.get(0));
        return productParsingService.getProductAsAnObject();
    }

    public ArrayList<Review> transformReviews(Elements elements){
        ArrayList<Review> reviews = new ArrayList<Review>();
        ReviewParsingService reviewParsingService = new ReviewParsingServiceImpl();
        reviewParsingService.setProduct(transformProduct(extractProduct()));
        for(Element element : elements) {
            reviewParsingService.setElement(element);
            reviews.add(reviewParsingService.getReviewAsAnObject());
        }
        return reviews;
    }

    public void loadAll(){
        ProductStoringService productStoringService = new ProductStoringServiceImpl();
        productStoringService.addProduct(transformProduct(extractProduct()));
        ReviewStoringService reviewStoringService = new ReviewStoringServiceImpl();
        for(Review review : transformReviews(extractReviews())){
            reviewStoringService.addReview(review);
        }
    }

    public void doETL(){
        loadAll();
    }

}