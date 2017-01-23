import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Yoga2pro on 23.01.2017.
 */
public class CSVExportServiceImpl implements CSVExportService{
    private ArrayList<Review> reviews;
    private ArrayList<String[]> reviewsAsATable = new ArrayList<String[]>();
    private char separator;
    private CSVWriter csvWriter;
    private FileWriter fileWriter;

    public CSVExportServiceImpl(){
    }
    public void exportToCSV(ArrayList<Review> reviews) throws IOException {
        csvWriter = new CSVWriter(fileWriter = new FileWriter("reviews.csv"), ',');
        for(Review review : reviews){
            String[] reviewAsATable = new String[]{review.getProduct().getProductId(), review.getAuthor(), review.getConclusion(), review.getPros(), review.getCons(),
            review.getStarsRate(), review.getRecommended(), "" + review.getUsefulnessRate(), "" + review.getUselessRate()};
            System.out.println(reviewAsATable);
            reviewsAsATable.add(reviewAsATable);
        }
        csvWriter.writeAll(reviewsAsATable);
        csvWriter.close();
    }

    public void exportToCSV(Review review){}

    public CSVExportService setSeparator(char separator) {
        return this;
    }
}