package com.example.travelguide.repository;

import com.example.travelguide.model.TravelGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelGuideRepository extends JpaRepository<TravelGuide, Long> {
}
