package com.example.gruppe10.repositories;

import com.example.gruppe10.models.Seat;
import com.example.gruppe10.models.UniqueTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface SeatRepository extends JpaRepository<Seat, Integer> {




    @Query("SELECT seat FROM Seat seat  WHERE id_unique_time_slot = ?1")
    Set<Seat> findBookedSeatsFromIdUniqueTimeSlot(int idUniqueTimeSlot);






}
