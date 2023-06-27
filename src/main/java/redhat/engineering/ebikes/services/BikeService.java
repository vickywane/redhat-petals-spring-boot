package redhat.engineering.ebikes.services;

import redhat.engineering.ebikes.entities.Bike;
import redhat.engineering.ebikes.repositories.BikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import redhat.engineering.ebikes.repositories.UserRepository;

import jakarta.persistence.*;
import jakarta.transaction.*;

import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;
    @Autowired
    UserRepository userRepository;

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

    public void deleteABike(Long bikeId) {
        bikeRepository.deleteById(bikeId);
    }

    @Transactional
    public void postBikeAd(Bike bikeData) {
        entityManager.createNativeQuery("INSERT INTO bikes(name, model, price, warranty_status) VALUES (?,?,?,?)")
                .setParameter(1, bikeData.getName())
                .setParameter(2, bikeData.getModel())
                .setParameter(3, bikeData.getPrice())
                .setParameter(4, bikeData.getWarranty_status())
                .executeUpdate();
    }

    @Transactional
    public void purchaseBike(Optional<Bike> bikeData) {
        bikeData.ifPresent(bike -> entityManager.createNativeQuery("INSERT INTO bike_order(product_id, customer_id, price) VALUES (?,?,?)")
                .setParameter(1, bike.getId())
                .setParameter(2, 26)
                .setParameter(3, bike.getPrice())
                .executeUpdate());
    }
}