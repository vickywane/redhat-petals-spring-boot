package redhat.engineering.ebikes.services;

import redhat.engineering.ebikes.entities.Bike;
import redhat.engineering.ebikes.entities.Service_User;
import redhat.engineering.ebikes.repositories.BikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Configurable
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Bike> retrieveBikes() {

        return bikeRepository.findAll();
    }

    @Transactional
    public Optional<Bike> retrieveABike(Long bikeId) {

        return bikeRepository.findById(bikeId);
    }

    @Transactional
    public void postBikeAd(Bike bikeData) {
        entityManager.createNativeQuery("INSERT INTO bikes(id, name, model, price, warranty_status) VALUES (?,?,?,?,?)")
//                .setParameter(1, bikeData.getId())
                .setParameter(1, 10)
                .setParameter(2, bikeData.getName())
                .setParameter(3, bikeData.getModel())
//                .setParameter(4, bikeData.getPrice())
                .setParameter(4, 20)
                .setParameter(5, bikeData.getWarranty_status())
                .executeUpdate();
    }

    @Transactional
    public void createUser(Service_User userData) {
        entityManager.createNativeQuery("INSERT INTO service_user(id, fullname, email, user_role, password) VALUES (?,?,?,?,?)")
                .setParameter(1, 10)
//                .setParameter(2, userData.getFullname())
//                .setParameter(3, userData.getEmail())
//                .setParameter(4, userData.getuser_role())
                .setParameter(2, "Victory Nwani")
                .setParameter(3, "Vickywane@gmail.com")
                .setParameter(4, "CUSTOMER")
                .setParameter(5, "IAMNWANI")
//                .setParameter(5, userData.getPassword())
                .executeUpdate();
    }

   public BikeService() {}
}