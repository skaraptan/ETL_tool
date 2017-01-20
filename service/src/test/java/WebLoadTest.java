import java.io.File;
import java.io.IOException;
import com.jaunt.*;
/**
 * Created by Yoga2pro on 08.12.2016.
 */
public class WebLoadTest {
public static void main(String[] args){
    try{
        File file;
        UserAgent ua = new UserAgent();
        ua.visit("http://www.ceneo.pl/37384121");
        ua.doc.saveCompleteWebPage(file = new File("C:\\Users\\Yoga2pro\\Desktop\\index.htm"));
        if(file.exists()){
            System.out.println("Load successful.");
        }
        if(file.delete()){
            System.out.println("Delete successful.");
        }
        else{
            System.err.println("Load and delete malfunction");
        }
    }
    catch(JauntException j){
        System.err.println(j);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
