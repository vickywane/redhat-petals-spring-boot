package redhat.engineering.ebikes.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="bikes")
public class Bike {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "date_created")
    private Date date_created;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image")
    private Byte image;

    @Column(name = "warranty_status")
    private String warranty_status;

    public Object getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public String getWarranty_status() {
        return warranty_status;
    }

    public Date getDate_created() {
        return date_created;
    }

    public Byte getImage() {
        return image;
    }

    //  SETTERS
    public void setWarrantyStatus(String status) {
        this.warranty_status = status;
    }

    public void setImage(Byte image) {
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}