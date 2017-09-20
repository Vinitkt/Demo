package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private   int    persionId;
	private   String  personName;
	
	public int getPersionId() {
		return persionId;
	}
	public void setPersionId(int persionId) {
		this.persionId = persionId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public Person(String personName) {
		super();
		this.personName = personName;
	}
	public Person() {
		super();
	}
}
