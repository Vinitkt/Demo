package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

}
