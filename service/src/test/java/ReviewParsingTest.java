import java.io.File;
import java.io.IOException;

/**
 * Created by Yoga2pro on 08.12.2016.
 */
public class ReviewParsingTest {
    public static void main (String[] args) {
        try {
            ElementsGettingService reviewGettingService = new ElementsGettingService(new File("C:\\Users\\Yoga2pro\\workspace\\ETL_tool\\service\\src\\main\\resources\\testPage.html"));
            String output;
            String result = new String("punio24:   Całe życie na Windows aż do 10 Pro - i człowiek nie wie co traci. Broniłem się przed Maciem aż do momentu jak przyszło mi pracować na Linuxie w pracy. To jest to czego człowiekowi potrzeba - siadasz , odpalasz i przyjemnie pracujesz bez zawieszeń, problemów z aktualizacjami i restartami. Poprostu energię wykorzystujesz do twórczej pracy a nie ciągłego dbania by Winda działała. MAc jest mega intuicyjny i za to go lubię. Plus to oprogramowanie! Polecam. Do Windowsa już nie wrócę.\n" +
                    " 5/5   Polecam\n" +
                    "Wady:  naprawdę nie mam czego się przyczepić, \n" +
                    "Zalety:  10 godzin na jednym ładowaniu, design, dodatkowe oprogramowanie w pakiecie, jakość wykonania, lekki i w miarę szybki, synergia sprzętu z systemem, \n" +
                    "+ : 22\n" +
                    "- : 4");
            ReviewParsingServiceImpl parsingService = new ReviewParsingServiceImpl(reviewGettingService.getReviews().get(1));
            output = new String(parsingService.getAuthor() + ":   " + parsingService.getConclusion() +  "\n " + parsingService.getStarsRate()
                    + "   " + parsingService.isRecommended() + "\nWady:  " + parsingService.getCons() + "\nZalety:  " + parsingService.getPros()
            + "\n+ : " + parsingService.getUsefulnessRates().getUsefulnessRate() +"\n- : "+ parsingService.getUsefulnessRates().getUselessRate());
            if(output.equals(result)){
                System.out.println("Test passed");
            }
            else{
                System.err.println("Expected results and output are different. Test failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
