package main;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
public interface TripsRepository extends JpaRepository<Trip, Integer> {
}
