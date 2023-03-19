package com.example.Moviebookapp.Repository;

import com.example.Moviebookapp.Models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity,Integer> {

    @Query(value = "select * from theater where location =:location",nativeQuery = true)
    List<TheaterEntity> findBylocation(String location);

    @Query(value = "select * from theater where name =:name",nativeQuery = true)
    TheaterEntity findByName(String name);
}
