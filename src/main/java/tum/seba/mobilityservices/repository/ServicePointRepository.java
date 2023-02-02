package tum.seba.mobilityservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tum.seba.mobilityservices.entity.ServicePoint;

@Repository
public interface ServicePointRepository extends JpaRepository<ServicePoint, Integer> {

}
