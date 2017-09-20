package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patient 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private    int        patientId;
	private   String   patientName;
	private   String   location;
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "doctor_patient", joinColumns = @JoinColumn(name = "patient_id",
    referencedColumnName = "patientId"),
    inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId"))
	private List<Doctor> doctor;
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Doctor> getDoctor() {
		return doctor;
	}
	public void setDoctor(List<Doctor> doctor) {
		this.doctor = doctor;
	}
	public Patient() {
		
	}
	public Patient(String patientName, String location) {
		this.patientName = patientName;
		this.location = location;
	}
	public Patient(String patientName, String location, List<Doctor> doctor) {
		super();
		this.patientName = patientName;
		this.location = location;
		this.doctor = doctor;
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", location=" + location
				+ "]";
	}
	
}
