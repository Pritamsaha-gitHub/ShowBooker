package com.example.Moviebookapp.Repository;

import com.example.Moviebookapp.Models.ShowSeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeatsEntity,Integer> {
}
