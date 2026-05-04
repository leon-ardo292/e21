package org.e2e.labe2e01.driver.application;


import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.driver.domain.DriverService;
import org.e2e.labe2e01.vehicle.domain.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/{id}")
    public Driver getDriver(@PathVariable Long id) {
        return driverService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.create(driver);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDriver(@PathVariable Long id) {
        driverService.delete(id);
    }

    @PutMapping("/{id}")
    public Driver updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        return driverService.update(id, driver);
    }

    @PatchMapping("/{id}/location")
    public Driver updateDriverLocation(
            @PathVariable Long id,
            @RequestParam Double latitude,
            @RequestParam Double longitude
    ) {
        return driverService.updateLocation(id, latitude, longitude);
    }

    @PatchMapping("/{id}/car")
    public Driver updateDriverCar(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return driverService.updateVehicle(id, vehicle);
    }
}
