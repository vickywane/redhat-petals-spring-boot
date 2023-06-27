package redhat.engineering.ebikes.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import redhat.engineering.ebikes.entities.Service_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import redhat.engineering.ebikes.repositories.UserRepository;

import jakarta.persistence.*;
import jakarta.transaction.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Configurable
public class UserService {
    public UserService() {}

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UUID generateUUID;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Service_User> retrieveUsers(String filterBy) {
        return userRepository.findAll();
    }

    public Optional<Service_User> retrieveAUser(Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteAUser(Long userId) {
         userRepository.deleteById(userId);
    }

    @Transactional
    public void createUser(Service_User userData) {
        entityManager.createNativeQuery("INSERT INTO service_user(fullname, email, user_role, password) VALUES (?,?,?,?)")
                .setParameter(1, userData.getFullname())
                .setParameter(2, userData.getEmail())
                .setParameter(3, userData.getUser_role())
                .setParameter(4, passwordEncoder.encode(userData.getPassword()) )
                .executeUpdate();
    }
}