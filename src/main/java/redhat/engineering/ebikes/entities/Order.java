package redhat.engineering.ebikes.entities;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="bike_order")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    private Date date_created;
}