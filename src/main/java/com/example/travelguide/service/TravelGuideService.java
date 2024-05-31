package com.example.travelguide.service;

import com.example.travelguide.model.TravelGuide;
import com.example.travelguide.repository.TravelGuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelGuideService {

    @Autowired
    private TravelGuideRepository travelGuideRepository;

    public List<TravelGuide> getAllTravelGuides() {
        return travelGuideRepository.findAll();
    }

    public Optional<TravelGuide> getTravelGuideById(Long id) {
        return travelGuideRepository.findById(id);
    }

    public TravelGuide saveTravelGuide(TravelGuide travelGuide) {
        return travelGuideRepository.save(travelGuide);
    }

    public void deleteTravelGuide(Long id) {
        travelGuideRepository.deleteById(id);
    }
}
