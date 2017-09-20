package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="parent2")
public class Parent implements Serializable {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int parentId;
private String parentName;
/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JsonManagedReference*/
@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
private List<Child> child = new ArrayList<Child>();
public int getParentId() {
	return parentId;
}
public void setParentId(int parentId) {
	this.parentId = parentId;
}
public String getParentName() {
	return parentName;
}
public void setParentName(String parentName) {
	this.parentName = parentName;
}

public List<Child> getChild() {
	return child;
}
public void setChild(List<Child> child) {
	this.child = child;
}
public Parent() {
	}
public Parent(String parentName) {
	super();
	this.parentName = parentName;
}

@Override
public String toString() {
	return "Parent [parentId=" + parentId + ", parentName=" + parentName + ", child=" + child + "]";
}


}
