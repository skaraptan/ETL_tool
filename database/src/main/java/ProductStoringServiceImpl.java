import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by Yoga2pro on 15.01.2017.
 */
public class ProductStoringServiceImpl implements ProductStoringService {
    public void addProduct(Product product) {
        Session session = null;
        //Temporary solution
        if(!checkIfExists(product)){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
        catch(Exception e){
            System.err.println(e);
        }
        finally {
            if(session!=null && session.isOpen()){
                session.close();
                }
            }
        }
    }

    //Returns true is exists
    public boolean checkIfExists(Product product) {
        try {
            Query query = HibernateUtil.getSessionFactory().openSession().createQuery("FROM Product P where P.productId = :product_id");
            query.setString("product_id", product.getProductId());
            if(query.uniqueResult()==null){
                return false;
            }
            if(query.uniqueResult()!=null){
                return true;
            }
        }
        catch (NonUniqueResultException nure){
            return true;
        }
        return true;
    }
}
