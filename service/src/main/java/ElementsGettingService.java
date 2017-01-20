import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yoga2pro on 04.01.2017.
 */
public class ElementsGettingService {
    private Document doc;

    public ElementsGettingService(File file) throws IOException {
        this.doc = Jsoup.parse(file, "UTF-8");
    }

    public Elements getReviews(){
        return doc.getElementsByClass("product-review js_product-review");
    }

    public Elements getAllElements(){
        return doc.getAllElements();
    }

}
