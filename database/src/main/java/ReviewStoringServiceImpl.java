import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by Yoga2pro on 10.01.2017.
 */

public class ReviewStoringServiceImpl implements ReviewStoringService {

    public void addReview(Review review) {
        Session session = null;

        //temporary solution
        if (!checkIfExists(review)) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(review);
                session.getTransaction().commit();
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
        }
    }

    //Returns true if exists
    public boolean checkIfExists(Review review) {
        try {
            Query query = HibernateUtil.getSessionFactory().openSession().createQuery("FROM Review R where R.author = :author" +
                    " AND R.product.productId = :product_id");
            query.setString("author", review.getAuthor());
            query.setString("product_id", review.getProduct().getProductId());
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
