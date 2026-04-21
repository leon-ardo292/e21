package org.e2e.labe2e01.driver.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.coordinate.infrastructure.CoordinateRepository;
import org.e2e.labe2e01.driver.infrastructure.DriverRepository;
import org.e2e.labe2e01.vehicle.domain.Vehicle;
import org.e2e.labe2e01.vehicle.infrastructure.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverService {
    private final DriverRepository driverRepository;
    private final CoordinateRepository coordinateRepository;
    private final VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public Driver getById(Long id) {
        return driverRepository.findById(id).orElseThrow();
    }

    public Driver create(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver update(Long id, Driver payload) {
        Driver driver = getById(id);

        driver.setFirstName(payload.getFirstName());
        driver.setLastName(payload.getLastName());
        driver.setEmail(payload.getEmail());
        driver.setPassword(payload.getPassword());
        driver.setPhoneNumber(payload.getPhoneNumber());
        driver.setRole(payload.getRole());
        driver.setCategory(payload.getCategory());
        driver.setCreatedAt(payload.getCreatedAt());
        driver.setUpdatedAt(payload.getUpdatedAt());
        driver.setTrips(payload.getTrips());
        driver.setAvgRating(payload.getAvgRating());
        driver.setCoordinate(payload.getCoordinate());
        driver.setVehicle(payload.getVehicle());

        return driverRepository.save(driver);
    }

    public void delete(Long id) {
        driverRepository.deleteById(id);
    }

    public Driver updateLocation(Long id, Double latitude, Double longitude) {
        Driver driver = getById(id);
        Coordinate coordinate = coordinateRepository.save(new Coordinate(latitude, longitude));
        driver.setCoordinate(coordinate);
        return driverRepository.save(driver);
    }

    public Driver updateVehicle(Long id, Vehicle payload) {
        Driver driver = getById(id);
        Vehicle vehicle = vehicleRepository.save(payload);
        driver.setVehicle(vehicle);
        return driverRepository.save(driver);
    }
}
