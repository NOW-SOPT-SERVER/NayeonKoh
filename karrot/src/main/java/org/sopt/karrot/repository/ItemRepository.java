package org.sopt.karrot.repository;

import java.util.List;
import lombok.NonNull;
import org.sopt.karrot.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE i.location.id = :locationId")
    List<Item> findByLocationId(@NonNull final Long locationId);
}
