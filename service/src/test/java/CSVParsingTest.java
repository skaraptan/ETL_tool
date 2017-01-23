import com.jaunt.ResponseException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Yoga2pro on 23.01.2017.
 */
public class CSVParsingTest {
    public static void main(String[] args) throws IOException, ResponseException {
        ArrayList<String[]> reviewsAsATable = new ArrayList<String[]>();
        HtmlUploadService htmlUploadService = new HtmlUploadService();
        ElementsGettingService elementsGettingService = new ElementsGettingService(htmlUploadService.getFileFromWeb("http://www.ceneo.pl/38796089"));
        ProductParsingService productParsingService = new ProductParsingServiceImpl(elementsGettingService.getAllElements().first());
        ReviewsParsingService reviewsParsingService = new ReviewsParsingService();
        CSVExportService csvExportService = new CSVExportServiceImpl();
        csvExportService.exportToCSV(reviewsParsingService.listReviews(elementsGettingService.getReviews(), productParsingService.getProductAsAnObject()));
    }
}
