package com.coding.repositories;

import com.coding.entities.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface ShopRepository extends JpaRepository<Shop, Long> {

    @RestResource(path = "all")
    Page<Shop> findByShopIdNotInAndShopIdNotInOrderByPositionAsc(@Param("ids") List<Long> ids, @Param("undesired") List<Long> undesired, Pageable pageable);

    @RestResource(path = "liked")
    Page<Shop> findByShopIdNotInOrderByPosition(@Param("ids") List<Long> ids, Pageable pageable);

    @RestResource(path = "disliked")
    Page<Shop> findByShopIdNotInOrderByPositionAsc(@Param("undesired") List<Long> undesired, Pageable pageable);

    @RestResource(path = "preferred")
    Page<Shop> findByShopIdInOrderByPositionAsc(@Param("ids") List<Long> list, Pageable pageable);

}
