package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.FileRepository;
import dao.LecturerRepository;
import dao.PassportRepository;
import dao.PersonRepository;
import model.FileUpload;
import model.Lecturer;
import model.Passport;
import model.Person;
import view.ExcelBuilder;

@Controller
public class OTOcontroller {
@Autowired
PassportRepository idRepository;
@Autowired
PersonRepository manRepository;
@Autowired
LecturerRepository lRepository;
@Autowired
FileRepository fRepository;
final static Logger logger = Logger.getLogger(OTOcontroller.class);
SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
private static String UPLOADED_FOLDER = "";
@RequestMapping("/save")
public List<Passport> saveOTO() throws ParseException
{
	/*Person person=new Person("shekhar");
	
	//manRepository.save(perso;
	Passport passport=new Passport();
	Passport passport5=new Passport();
	try {
		
		Date date=sdf.parse("23-05-2126");
		
		passport5.setExpireDate(date);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	passport5.setPerson(person);*/
	Person person=new Person("ramesh");
	Passport passport=new Passport();
	passport.setExpireDate(sdf.parse("10-05-2024"));
	passport.setPerson(person);
	idRepository.save(passport);
	logger.info("passport successfully saved with person");
	return idRepository.findAll();
}
@RequestMapping("/showAll")
public List<Lecturer> show()
{
	
	//logger.info("the details of passport"+new Person().getPassport().getPassportNumber());
	logger.info("person passport is");
	return lRepository.findAll();
}
@RequestMapping("/findbyid")
public Person findbyid(@RequestParam("id") int id)
{
	
	return manRepository.findOne(id);
}
@RequestMapping("/addPassport")
public List<Passport> adding() throws ParseException
{
Passport passport=new Passport();
passport.setExpireDate(sdf.parse("26-02-2032"));
return idRepository.findAll();
}
@RequestMapping("/passportid")
public List<Passport> addperson(@RequestParam("id")int id)
{
	Passport passport=idRepository.findOne(id);
	/*Person person=new Person("balu");
	passport.setPerson(person);
	idRepository.save(passport);*/
	//logger.info("the person name is"+passport.getPerson().getPersonName());
	return idRepository.findAll();
	
	
	
}
@RequestMapping("/update")
public Person update(@RequestParam("id")int id)
{
	Person person=manRepository.findOne(id);
	person.setPersonName("maneesh");
	manRepository.save(person);
return manRepository.findOne(id);	
}
@RequestMapping("deletebyid")
public List<Passport> delete(@RequestParam("id")int id)
{
	Passport passport=idRepository.findOne(id);
	idRepository.delete(passport);
	return idRepository.findAll();
}
@RequestMapping("/deletedata")
public String deletedata()
{
	lRepository.deleteAll();
	return "deleted";
}
@RequestMapping("/")
public String homepage()
{
	return"home";
}
@RequestMapping("/modelview")
public ModelAndView downloading(HttpServletResponse response) throws IOException
{
	List<Lecturer> listlecture=lRepository.findAll();
	 Map<String, Object> model = new HashMap<String, Object>();
	 model.put("lecturer",listlecture);
	response.setContentType( "application/ms-excel" );
    response.setHeader( "Content-disposition", "attachment; filename=test3.xls" );
    
    /*Workbook wb=new Lecturer().exportEventsToCSV();
    wb.write(response.getOutputStream());*/
    return new ModelAndView(new ExcelBuilder(),model);	
	
}
@RequestMapping("formatExcel")
public String formatingexcel() throws IOException
{
	
	FileInputStream file=new FileInputStream("/home/test/Desktop/ExcelSheet1.xlsx");
	logger.info("controller coming");
	XSSFWorkbook workbook = new XSSFWorkbook(file);
	XSSFSheet sheet = workbook.getSheetAt(0);
	Iterator<Row> rowIterator = sheet.iterator();
	Lecturer lecturer=new Lecturer();
	for (int i = 1; i <= sheet.getLastRowNum(); i++) {
		Row row = (Row) sheet.getRow(i);
		lecturer.setId((int) row.getCell(0).getNumericCellValue());
		lecturer.setName(row.getCell(1).getStringCellValue());
		lecturer.setDesignation(row.getCell(2).getStringCellValue());
		lRepository.save(lecturer);
}
       
   // file.close();
return "exported";	
}
@RequestMapping("/download")
public String exportTable(@RequestParam("id") int id,HttpServletResponse res) throws IOException
{
	FileUpload file=fRepository.findOne(id);
	res.addHeader("Content-Disposition",
		      "attachment;filename="
		              +file.getFileName());
	FileCopyUtils.copy(
            file.getData(), 
	     res.getOutputStream());
	return "home";
	
	
}
//for retrieve data from database and form as excelsheet and download that
@RequestMapping("/downloading")
public String exportTablefile() throws ClassNotFoundException, SQLException, IOException
{
	Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "test");
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("Select * from lecturer");
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("lawix10");
    HSSFRow rowhead = sheet.createRow(0);
    rowhead.createCell(0).setCellValue("Id");
    rowhead.createCell(1).setCellValue("Name");
    rowhead.createCell(2).setCellValue("Designation");
    
    int i = 1;
    while (rs.next()){
        HSSFRow row = sheet.createRow(i);
        row.createCell(0).setCellValue(Integer.toString(rs.getInt(1)));
        row.createCell(1).setCellValue(rs.getString(2));
        row.createCell(2).setCellValue(rs.getString(3));
        i++;
    }
    FileOutputStream fileOut = new FileOutputStream("/home/test/test10.xlsx");
    workbook.write(fileOut);
	return "exported";
}

@RequestMapping(value="/upload",method=RequestMethod.POST)
public String uploadFile(@RequestParam("file") MultipartFile file,Model model,
        RedirectAttributes redirectAttributes) throws Exception
{
	
	if (file.isEmpty()) {
		String msg="please select a file";
		model.addAttribute("msg",msg);
        return "home";
	}
	/*else {
	//service.store(file);
	model.addAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
	 //Files.add(file.getOriginalFilename());
	}*/
	byte[] bytes = file.getBytes();
    Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
    //Files.write(path, bytes);
    Files.write(path, bytes);
    FileUpload ufile=new FileUpload();
    ufile.setFileName(file.getOriginalFilename());
    ufile.setData(file.getBytes());
    fRepository.save(ufile);
    String msg="successfully uploaded with"+  file.getOriginalFilename();
    /*redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded '" + file.getOriginalFilename() + "'");*/
    model.addAttribute("msg",msg);
	             
    return "Uploadfile";

    
	}
}
