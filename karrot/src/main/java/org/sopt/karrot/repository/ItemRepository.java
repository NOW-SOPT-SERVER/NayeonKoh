package org.sopt.karrot.repository;

import java.util.List;
import lombok.NonNull;
import org.sopt.karrot.domain.Item;
import org.sopt.karrot.exception.CommonException;
import org.sopt.karrot.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i WHERE i.location.id = :locationId")
    List<Item> findByLocationId(@NonNull final Long locationId);

    default Item findByIdOrThrow(@NonNull final Long itemId) {
        return findById(itemId).orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_ITEM));
    }
}
