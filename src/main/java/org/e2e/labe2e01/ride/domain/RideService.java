package org.e2e.labe2e01.ride.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.driver.infrastructure.DriverRepository;
import org.e2e.labe2e01.ride.infrastructure.RideRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RideService {
    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;

    public Ride create(Ride ride) {
        return rideRepository.save(ride);
    }

    public Ride assignDriver(Long rideId, Long driverId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow();
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        ride.setDriver(driver);
        ride.setStatus(Status.ACCEPTED);
        return rideRepository.save(ride);
    }

    public void delete(Long id) {
        rideRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Ride> getPassengerRides(Long passengerId, int page, int size) {
        return rideRepository.findAllByPassengerIdAndStatus(
                passengerId,
                Status.COMPLETED,
                PageRequest.of(page, size)
        );
    }

    public Ride cancel(Long id) {
        Ride ride = rideRepository.findById(id).orElseThrow();
        ride.setStatus(Status.CANCELED);
        return rideRepository.save(ride);
    }
}
