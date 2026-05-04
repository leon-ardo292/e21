package org.e2e.labe2e01.ride.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.driver.domain.Driver;
import org.e2e.labe2e01.passenger.domain.Passenger;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Status status;

    @Column(name = "arrival_date")
    private ZonedDateTime arrivalDate;

    @Column(name = "departure_date")
    private ZonedDateTime departureDate;

    @ManyToOne
    @JoinColumn(name = "destination_coordinates_id", nullable = false)
    private Coordinate destinationCoordinates;

    @ManyToOne
    @JoinColumn(name = "origin_coordinates_id", nullable = false)
    private Coordinate originCoordinates;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @Column(name = "destination_name", nullable = false)
    private String destinationName;

    @Column(name = "origin_name", nullable = false)
    private String originName;
}
