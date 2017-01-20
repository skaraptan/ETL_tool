import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
public class Review extends Item{


    @Id@GeneratedValue
    @Column(name = "review_id")
    private int reviewId;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "pros")
    private String pros;

    @Column(name = "cons")
    private String cons;

    @Column(name = "conclusion", columnDefinition = "text")
    private String conclusion;

    @Column(name = "stars_rate")
    private String starsRate;

    @Column(name = "author")
    private String author;

    @Column(name = "recommendation")
    private String isRecommended;

    @Column(name = "usefulness_rate")
    private int usefulnessRate;

    @Column(name = "useless_rate")
    private int uselessRate;

    public Review(){}

    public Review(Product product, String pros, String cons, String conclusion,
                  String starsRate, String author, String isRecommended, int usefulnessRate, int uselessRate) {
        this.product = product;
        this.pros = pros;
        this.cons = cons;
        this.conclusion = conclusion;
        this.starsRate = starsRate;
        this.author = author;
        this.isRecommended = isRecommended;
        this.usefulnessRate = usefulnessRate;
        this.uselessRate = uselessRate;
    }
    public Review(String pros, String cons, String conclusion,
                  String starsRate, String author, String isRecommended, int usefulnessRate, int uselessRate) {
        this.pros = pros;
        this.cons = cons;
        this.conclusion = conclusion;
        this.starsRate = starsRate;
        this.author = author;
        this.isRecommended = isRecommended;
        this.usefulnessRate = usefulnessRate;
        this.uselessRate = uselessRate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUsefulnessRate() {
        return usefulnessRate;
    }

    public void setUsefulnessRate(int usefulnessRate) {
        this.usefulnessRate = usefulnessRate;
    }

    public int getUselessRate() {
        return uselessRate;
    }

    public void setUselessRate(int uselessRate) {
        this.uselessRate = uselessRate;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int productId) {
        this.reviewId = productId;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getStarsRate() {
        return starsRate;
    }

    public void setStarsRate(String starsRate) {
        this.starsRate = starsRate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRecommended() {
        return isRecommended;
    }

    public void setRecommended(String recommended) {
        isRecommended = recommended;
    }

}
