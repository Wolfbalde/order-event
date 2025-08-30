package org.wolfiee.ordereve.orderevent.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wolfiee.ordereve.orderevent.Domain.Order;

@Repository
public interface orderrepo extends JpaRepository<Order,String> {
}
