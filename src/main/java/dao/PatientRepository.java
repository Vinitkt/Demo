package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer>{

}
