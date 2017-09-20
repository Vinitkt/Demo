package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

@Entity
public class Lecturer  {
@Id
private int id;
private String name;
private String designation;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
/*public Workbook exportEventsToCSV() {
    try {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
        HSSFCellStyle cellStyle = workbook.createCellStyle();

        FileOutputStream fileOut = new FileOutputStream("/home/test/test3.xlsx");
        workbook.write(fileOut);

        return workbook;
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;*/
}

