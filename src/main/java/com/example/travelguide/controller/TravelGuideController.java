package com.example.travelguide.controller;

import com.example.travelguide.model.TravelGuide;
import com.example.travelguide.service.FlightService;
import com.example.travelguide.service.TravelGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/travel-guides")
public class TravelGuideController {

    @Autowired
    private TravelGuideService travelGuideService;
    
    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<TravelGuide> getAllTravelGuides() {
        return travelGuideService.getAllTravelGuides();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelGuide> getTravelGuideById(@PathVariable Long id) {
        Optional<TravelGuide> travelGuide = travelGuideService.getTravelGuideById(id);
        return travelGuide.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TravelGuide createTravelGuide(@RequestBody TravelGuide travelGuide) {
        return travelGuideService.saveTravelGuide(travelGuide);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelGuide> updateTravelGuide(@PathVariable Long id, @RequestBody TravelGuide travelGuideDetails) {
        Optional<TravelGuide> optionalTravelGuide = travelGuideService.getTravelGuideById(id);
        if (optionalTravelGuide.isPresent()) {
            TravelGuide travelGuide = optionalTravelGuide.get();
            travelGuide.setDestination(travelGuideDetails.getDestination());
            travelGuide.setDescription(travelGuideDetails.getDescription());
            return ResponseEntity.ok(travelGuideService.saveTravelGuide(travelGuide));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravelGuide(@PathVariable Long id) {
        Optional<TravelGuide> travelGuide = travelGuideService.getTravelGuideById(id);
        if (travelGuide.isPresent()) {
            travelGuideService.deleteTravelGuide(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Other methods...

    @GetMapping("/flight-time/{flightNumber}")
    public ResponseEntity<String> getFlightTime(@PathVariable String flightNumber) {
        String flightTime = flightService.getFlightTime(flightNumber);
        return ResponseEntity.ok(flightTime);
    }
}
