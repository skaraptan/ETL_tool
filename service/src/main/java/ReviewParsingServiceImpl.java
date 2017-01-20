import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

import static java.lang.Integer.parseInt;

/**
 * Created by Yoga2pro on 02.01.2017.
 */
public class ReviewParsingServiceImpl implements ReviewParsingService {
    private Element element;
    private Product product;

    public ReviewParsingServiceImpl(){
    }

    public ReviewParsingServiceImpl(Element element, Product product){
        this.element = element;
        this.product = product;
    }
    public ReviewParsingServiceImpl(Element element){
        this.element = element;
    }

    public void setElement(Element element){
        this.element = element;
    }
    public void setProduct(Product product){
        this.product = product;
    }
    public String getPros() {
        String prosText = new String();
        Elements pros = element.getElementsByClass("pros-cell").first().getElementsByTag("li");
        for(Element pro : pros){
            prosText= prosText + (pro.text()) + ", ";
        }
        return prosText;
    }

    public String getCons() {
        String consText = new String();
        Elements cons = element.getElementsByClass("cons-cell").first().getElementsByTag("li");
        for(Element con : cons){
            consText=consText + (con.text()) + ", ";
        }
        return consText;
    }

    public String getConclusion() {
        return element.getElementsByClass("product-review-body").first().text();
    }

    public String getStarsRate() {
        return element.getElementsByClass("review-score-count").first().text();
    }

    public String getAuthor() {
       return element.getElementsByClass("product-reviewer").first().text();
    }

    public String isRecommended() {
        String recommendation;
        try {
             recommendation = element.getElementsByClass("product-review-summary").first().text();
        }
        catch (NullPointerException e){
            return "";
        }
        return recommendation;
    }

    public UsefulnessRate getUsefulnessRates() {
        int usefull = parseInt(element.getElementsByClass("vote-yes js_product-review-vote js_vote-yes").first().text());
        int useless = parseInt(element.getElementsByClass("vote-no js_product-review-vote js_vote-no").first().text());
        return new UsefulnessRate(usefull, useless);
    }

    public Review getReviewAsAnObject(){
        return new Review(product, getPros(), getCons(), getConclusion(), getStarsRate(), getAuthor(),
                isRecommended(), getUsefulnessRates().getUsefulnessRate(), getUsefulnessRates().getUselessRate());
    }

}
