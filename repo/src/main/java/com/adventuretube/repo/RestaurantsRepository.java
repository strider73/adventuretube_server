package com.adventuretube.repo;

import com.adventuretube.model.Restaurants;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface RestaurantsRepository extends JpaRepository <Restaurants,Long> {
    Page<Restaurants> findAll(Pageable pageable);
}
