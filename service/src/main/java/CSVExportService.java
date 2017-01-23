import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Yoga2pro on 23.01.2017.
 */
public interface CSVExportService {
    void exportToCSV(ArrayList<Review> reviews) throws IOException;
    void exportToCSV(Review review) throws IOException;
    CSVExportService setSeparator(char separator);

}
