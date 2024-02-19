package org.apathinternational.faithpathrestful.repository;

import java.util.List;

import org.apathinternational.faithpathrestful.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    // get students who:
    // 1. needs_airport_pickup = true
    // 2. has_flight_information = true
    // 3. has no airport_pickup_assigment
    @Query("SELECT s FROM Student s LEFT JOIN s.airportPickupAssignment apa WHERE s.needsAirportPickup = true AND s.hasFlightInfo = true AND apa.id IS NULL")
    List<Student> findUnassignedStudentsWithAirportPickupNeeds();

    @Query("SELECT s FROM Student s WHERE s.needsAirportPickup = true AND s.hasFlightInfo = true")
    List<Student> findStudentsWithAirportPickupNeeds();
}