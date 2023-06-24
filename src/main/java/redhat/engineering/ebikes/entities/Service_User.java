package redhat.engineering.ebikes.entities;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
@Table(name="service_user")
public class Service_User {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "date_created")
    private Date date_created;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private String user_role;

    public Long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }
}