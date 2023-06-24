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


@Service
@Configurable
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertBike(Bike bikeData) {
        entityManager.createNativeQuery("INSERT INTO bikes(id, game_name, game_score, player_username, team_size) VALUES (?,?,?,?,?)")
                .setParameter(1, bikeData.getId())
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