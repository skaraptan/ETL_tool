/**
 * Created by Yoga2pro on 15.01.2017.
 */
public interface ProductStoringService {
    void addProduct(Product product);
    boolean checkIfExists(Product product);
}
