package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import dao.LecturerRepository;
import model.Lecturer;




public class ExcelBuilder extends AbstractExcelView {
	 
	@Autowired
	LecturerRepository lRepository;
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
    	/*Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "test");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("Select * from lecturer");*/
        List<Lecturer> listlecture = (List<Lecturer>) model.get("lecturer");
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("listlecture");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        
        
         
        // create header row
        HSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("Id");
        
        header.createCell(1).setCellValue("Designation");
        
        header.createCell(2).setCellValue("Name");
      
        
        
         
        // create data rows
       
         int rowcount=0;
        for (Lecturer lecturer : listlecture) {
            HSSFRow aRow = sheet.createRow(rowcount++);
          //  Lecturer lecture=lRepository.findOne(rowcount);
            aRow.createCell(0).setCellValue(lecturer.getId());
            aRow.createCell(1).setCellValue(lecturer.getDesignation());
            aRow.createCell(2).setCellValue(lecturer.getName());
           
        }
        /*int i = 1;
        while (rs.next()){
            HSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(Integer.toString(rs.getInt(1)));
            row.createCell(1).setCellValue(rs.getString(2));
            row.createCell(2).setCellValue(rs.getString(3));
            i++;
        }*/
    }
 
}