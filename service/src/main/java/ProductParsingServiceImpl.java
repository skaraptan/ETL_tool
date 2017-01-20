import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Yoga2pro on 14.01.2017.
 */
public class ProductParsingServiceImpl implements ProductParsingService {
    private Element element;

    public ProductParsingServiceImpl(Element element){
        this.element = element;
    }
    public String getMark() {
        Elements elements =  element.getElementsByAttributeValueContaining("property", "og:brand");
        String mark = elements.first().attributes().get("content").replaceFirst("http://www.ceneo.pl/", "");
        return  mark;

    }

    public String getModel() {
        String model = element.getElementsByClass("product-name js_product-h1-link js_product-force-scroll js_searchInGoogleTooltip").first().text();
        model.replaceFirst(getMark(), "");
        model.replaceFirst("\\s", "");
        return model;
    }

    public String getAdditionalInfo() {
        return element.getElementsByClass("ProductSublineTags").first().text();
    }

    public String getType(){
        Elements elements = element.getElementsByClass("breadcrumb");
        return elements.get(elements.size() - 1).text();
    }

    public String getProductId(){
        Elements elements =  element.getElementsByAttributeValueContaining("property", "og:url");
        String mark = elements.first().attributes().get("content").replaceFirst("http://www.ceneo.pl/", "");
        return  mark;
    }

    public Product getProductAsAnObject() {
        return new Product(getProductId(), getMark(), getModel(), getAdditionalInfo(), getType());
    }
}
