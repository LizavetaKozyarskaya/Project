package com.shop.web.repo;

import com.shop.web.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Long> {

//    Building findBuildingByRooms(String rooms);
//    List<Building> findByWalls(String walls);

}
