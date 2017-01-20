import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yoga2pro on 02.01.2017.
 */
public class HtmlUploadService {
    private File htmlPage;
    public HtmlUploadService(){
    }

    public File getFileFromWeb(String url) throws ResponseException, IOException {
        UserAgent ua = new UserAgent();
        ua.visit(url);
        ua.doc.saveCompleteWebPage(htmlPage = new File("C:\\Users\\Yoga2pro\\Desktop\\index.htm"));
        return htmlPage;
    }

    public void deleteDoc(){
           System.out.print("TEMP file:   " + htmlPage.getAbsolutePath());
           if(htmlPage.delete()){
               System.out.print("      deleted");
           }
           else{
               System.out.print("      still exists. Something went wrong");
           }
    }
}
