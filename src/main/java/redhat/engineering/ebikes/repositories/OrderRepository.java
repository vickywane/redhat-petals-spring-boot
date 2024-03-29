package redhat.engineering.ebikes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redhat.engineering.ebikes.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> { }

