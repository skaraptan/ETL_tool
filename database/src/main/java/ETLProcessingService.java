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

    public String getProductCode() {
        return productCode;
    }

    private String productCode;

    private ArrayList<Review> reviews = new ArrayList<Review>();
    private Product product = new Product();

    private Elements untransformedReviews;
    private Elements untransformedProduct;
    private HtmlUploadService htmlUploadService;
    private ElementsGettingService elementsGettingService;
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public Product getProduct() {
        return product;
    }

    public Elements getUntransformedReviews() {
        return untransformedReviews;
    }

    public Elements getUntransformedProduct() {
        return untransformedProduct;
    }

    public ETLProcessingService(String url) throws IOException, ResponseException {
        this.url = url;
        this.productCode = url.replaceFirst("http://www.ceneo.pl/", "");
        htmlUploadService = new HtmlUploadService();
        elementsGettingService = new ElementsGettingService(htmlUploadService.getFileFromWeb(url));
    }

    public Elements extractProduct(){
        Elements elements = elementsGettingService.getAllElements();
        return  this.untransformedProduct = elements;
    }
    public Elements extractReviews(){
        Elements elements = elementsGettingService.getReviews();
        return this.untransformedReviews = elements;
    }
    public Product transformProduct(Elements elements){
        ProductParsingService productParsingService = new ProductParsingServiceImpl(elements.first());
        return this.product = productParsingService.getProductAsAnObject();
    }

    public Product transformProduct(){
        ProductParsingService productParsingService = new ProductParsingServiceImpl(untransformedProduct.first());
        return this.product = productParsingService.getProductAsAnObject();
    }

    public ArrayList<Review> transformReviews(Elements elements){
        ArrayList<Review> reviews = new ArrayList<Review>();
        ReviewParsingService reviewParsingService = new ReviewParsingServiceImpl();
        reviewParsingService.setProduct(transformProduct(extractProduct()));
        for(Element element : elements) {
            reviewParsingService.setElement(element);
            reviews.add(reviewParsingService.getReviewAsAnObject());
        }
        return this.reviews = reviews;
    }
    public ArrayList<Review> transformReviews(){
        ReviewParsingService reviewParsingService = new ReviewParsingServiceImpl();
        reviewParsingService.setProduct(product);
        for(Element element : untransformedReviews) {
            reviewParsingService.setElement(element);
            reviews.add(reviewParsingService.getReviewAsAnObject());
        }
        return reviews;
    }

    public void doETL(){
        ProductStoringService productStoringService = new ProductStoringServiceImpl();
        productStoringService.addProduct(transformProduct(extractProduct()));
        ReviewStoringService reviewStoringService = new ReviewStoringServiceImpl();
        for(Review review : transformReviews(extractReviews())){
            reviewStoringService.addReview(review);
        }
    }
    public void loadAll(){
        ProductStoringService productStoringService = new ProductStoringServiceImpl();
        productStoringService.addProduct(product);
        ReviewStoringService reviewStoringService = new ReviewStoringServiceImpl();
        for(Review review : reviews){
            reviewStoringService.addReview(review);
        }
        htmlUploadService.deleteDoc();
    }
}