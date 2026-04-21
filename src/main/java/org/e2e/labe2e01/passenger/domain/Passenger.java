package org.e2e.labe2e01.passenger.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.e2e.labe2e01.coordinate.domain.Coordinate;
import org.e2e.labe2e01.ride.domain.Ride;
import org.e2e.labe2e01.user.domain.User;
import org.e2e.labe2e01.userLocations.domain.UserLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends User {

    @OneToMany(mappedBy = "passenger")
    private List<Ride> rides = new ArrayList<>();

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLocation> places = new ArrayList<>();
}