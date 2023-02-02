package tum.seba.mobilityservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tum.seba.mobilityservices.entity.ServicePoint;
import tum.seba.mobilityservices.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
	@Query("SELECT v FROM Vehicle v WHERE v.currentLocation = ?1")
	public List<Vehicle> findVehiclesByServicePoint(ServicePoint servicePoint);

}
