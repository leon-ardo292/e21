package org.e2e.labe2e01.passenger.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.passenger.domain.Passenger;
import org.e2e.labe2e01.passenger.domain.PassengerService;
import org.e2e.labe2e01.userLocations.domain.UserLocation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping("/{id}")
    public Passenger getPassenger(@PathVariable Long id) {
        return passengerService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassenger(@PathVariable Long id) {
        passengerService.delete(id);
    }

    @PatchMapping("/{id}")
    public Passenger addPlace(
            @PathVariable Long id,
            @RequestParam String description,
            @RequestBody Coordinate coordinate
    ) {
        return passengerService.addPlace(id, coordinate, description);
    }

    @GetMapping("/{id}/places")
    public List<UserLocation> getPlaces(@PathVariable Long id) {
        return passengerService.getPlaces(id);
    }

    @DeleteMapping("/{id}/places/{coordinateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlace(@PathVariable Long id, @PathVariable Long coordinateId) {
        passengerService.deletePlace(id, coordinateId);
    }
}
