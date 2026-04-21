package org.e2e.labe2e01.ride.application;


import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.ride.domain.RideService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class RideController {
    private final RideService rideService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ride createRide(@RequestBody Ride ride) {
        return rideService.create(ride);
    }

    @PatchMapping("/{rideId}/assign/{driverId}")
    public Ride assignDriver(@PathVariable Long rideId, @PathVariable Long driverId) {
        return rideService.assignDriver(rideId, driverId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRide(@PathVariable Long id) {
        rideService.delete(id);
    }

    @GetMapping("/{passengerId}")
    public Page<Ride> getPassengerRides(
            @PathVariable Long passengerId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return rideService.getPassengerRides(passengerId, page, size);
    }

    @PatchMapping("/{id}")
    public Ride cancelRide(@PathVariable Long id) {
        return rideService.cancel(id);
    }
}
