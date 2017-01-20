/**
 * Created by Yoga2pro on 16.01.2017.
 */
public class ETLFinalTest {
    public static void main(String[] args){
        String url = new String("http://www.ceneo.pl/39541185");
        ETLProcessingService etlProcessingService = new ETLProcessingService(url);
        etlProcessingService.doETL();
    }
}
