package com.example.Moviebookapp.Repository;

import com.example.Moviebookapp.Models.TheaterSeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatsRepository extends JpaRepository<TheaterSeatsEntity,Integer> {
}
