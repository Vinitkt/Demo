package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="childObj2")
public class Child implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private int childId;
	private String childName;
	private Double childweigth;
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	@JsonBackReference*/
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = Parent.class)
	@JoinTable(name = "parent_child",joinColumns = {
			@JoinColumn(name = "Parent_ID", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "child_ID",
					nullable = false, updatable = false)})*/
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Parent_ID")
	private Parent parent; 
	
	
	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public Double getChildweigth() {
		return childweigth;
	}
	public void setChildweigth(Double childweigth) {
		this.childweigth = childweigth;
	}
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public Child() {
		
	}
	public Child(String childName, Double childweigth) {
		super();
		
		this.childName = childName;
		this.childweigth = childweigth;
	}
	@Override
	public String toString() {
		return "Child [childId=" + childId + ", childName=" + childName + ", childweigth=" + childweigth + "]";
	}
	
}
