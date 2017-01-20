import com.jaunt.ResponseException;

import java.io.IOException;

/**
 * Created by Yoga2pro on 14.01.2017.
 */
public class ProductParsingTest {
    public static void main(String[] args) throws IOException, ResponseException {
        HtmlUploadService service = new HtmlUploadService();
        ElementsGettingService rgs = new ElementsGettingService(service.getFileFromWeb("http://www.ceneo.pl/44316403"));
        ProductParsingService pps = new ProductParsingServiceImpl(rgs.getAllElements().first());
        System.out.println(pps.getMark() + " :\n" + pps.getModel() + ":\n " + pps.getAdditionalInfo() + "\n" + pps.getType());
    }
}
