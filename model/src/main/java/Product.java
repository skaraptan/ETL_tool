import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Yoga2pro on 16.11.2016.
 */

@Entity
@Table(name="PRODUCT")
public class Product extends Item{


    @Id@Column(name="product_id")
    private String productId;
    @Column(name="mark")
    private String mark;
    @Column(name="model")
    private String model;
    @Column(name="additional_info")
    private String additionalInfo;
    @Column(name = "type")
    private String type;

    public Product(String productId, String mark, String model, String additionalInfo, String type) {
        this.productId = productId;
        this.mark = mark;
        this.model = model;
        this.additionalInfo = additionalInfo;
        this.type = type;
    }
    public Product(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }


}
