package org.e2e.labe2e01.passenger.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.coordinate.infrastructure.CoordinateRepository;
import org.e2e.labe2e01.passenger.infrastructure.PassengerRepository;
import org.e2e.labe2e01.userLocations.domain.UserLocation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final CoordinateRepository coordinateRepository;

    @Transactional(readOnly = true)
    public Passenger getById(Long id) {
        return passengerRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }

    public Passenger addPlace(Long id, Coordinate coordinatePayload, String description) {
        Passenger passenger = getById(id);
        Coordinate coordinate = coordinateRepository.save(
                new Coordinate(coordinatePayload.getLatitude(), coordinatePayload.getLongitude())
        );
        passenger.addPlace(coordinate, description);
        return passengerRepository.save(passenger);
    }

    @Transactional(readOnly = true)
    public List<UserLocation> getPlaces(Long id) {
        return getById(id).getPlaces();
    }

    public void deletePlace(Long passengerId, Long coordinateId) {
        Passenger passenger = getById(passengerId);
        Coordinate coordinate = coordinateRepository.findById(coordinateId).orElseThrow();
        passenger.removePlace(coordinate);
        passengerRepository.save(passenger);
    }
}
