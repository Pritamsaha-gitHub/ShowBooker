package com.example.Moviebookapp.Repository;

import com.example.Moviebookapp.Models.MovieEntity;
import com.example.Moviebookapp.Models.TheaterEntity;
import com.example.Moviebookapp.ResponseDTOs.MovieResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

    @Query(value = "select * from movie where language =:language",nativeQuery = true)
    List<MovieEntity> findBylanguage(String language);

    @Query(value = "select * from movie where genre =:genre",nativeQuery = true)
    List<MovieEntity> findBygenre(String genre);
    @Query(value = "select * from movie where movie_name =:movie_name",nativeQuery = true)
    MovieEntity findByName(String movie_name);
}
