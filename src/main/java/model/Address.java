package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Address 
{
	@Id
	private int hno;
	private String street;
	private String city;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="empId",referencedColumnName = "empId")
	private Employee employee;
	public int getHno() {
		return hno;
	}
	public void setHno(int hno) {
		this.hno = hno;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Address(int hno, String street, String city) {
		super();
		this.hno = hno;
		this.street = street;
		this.city = city;
	}
	public Address()
	{
	
	}
	
}
