import com.jaunt.ResponseException;

import java.io.IOException;

/**
 * Created by Yoga2pro on 06.01.2017.
 */
public class HtmlDirectlyToObjectTest {
    public static void main(String[] args) throws IOException, ResponseException {
        HtmlUploadService service = new HtmlUploadService();
        ElementsGettingService rgs = new ElementsGettingService(service.getFileFromWeb("http://www.ceneo.pl/38796089"));
        ReviewParsingService ps = new ReviewParsingServiceImpl(rgs.getReviews().get(0), new Product());

    }
}
