package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.FileRepository;
import dao.LecturerRepository;
import model.FileUpload;
import model.Lecturer;
import utility.MailUtility;
import view.PdfView;

//@RestController
@Controller
public class WebController {
	
	
	@Autowired
	private MailUtility mail;
	@Autowired
	private FileRepository fRepository;
	@Autowired
	private LecturerRepository lRepository;
	private static String UPLOADED_FOLDER = "";
	@RequestMapping("/home")
	public String homepage()
	{
		return"home";
	}
	@RequestMapping("/home1")
	public String homepage1()
	{
		return "home1";
	}
	@RequestMapping("/pdf")
	public ModelAndView downloadPdf(HttpServletResponse response)
	{
		List<Lecturer> listLecturer=lRepository.findAll();
		 Map<String, Object> model = new HashMap<String, Object>();
		 model.put("lecturer", listLecturer);
		 response.setContentType("application/pdf");
		 response.setHeader( "Content-disposition", "attachment; filename=pdf" );
		return new ModelAndView(new PdfView(),model);
	}
	@RequestMapping(value="/sendmail",method=RequestMethod.POST)
	public String sendEmail(HttpServletRequest request,
            final @RequestParam("file") MultipartFile file,Model model) throws Exception{
		byte[] bytes = file.getBytes();
	    Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	    Files.write(path, bytes);
	    FileUpload ufile=new FileUpload();
	    ufile.setFileName(file.getOriginalFilename());
	    ufile.setData(file.getBytes());
	    fRepository.save(ufile);
	    final String email = request.getParameter("email");
	    mail.sendEmail(email, "welcome", "attached file is", file);
	    String msg="Successfully mail send to given mail id";
	    model.addAttribute("msg", msg);
        return "home";
        
    }
	@RequestMapping(value="/sendExcelmail",method=RequestMethod.POST)
	public String sendExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request,Model model) throws IOException
	{
		
		byte[] bytes = file.getBytes();
		
	    Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	    Files.write(path,bytes);
	    FileUpload ufile=new FileUpload();
	    ufile.setFileName(file.getOriginalFilename());
	    ufile.setData(file.getBytes());
	    fRepository.save(ufile);
	    XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
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
		final String email = request.getParameter("email");
	    mail.sendEmail(email, "welcome", "attached file is", file);
	    String msg="successfully saved in database and send to email";
	    model.addAttribute("msg",msg);
		return "home1";
	}	//return null;
	
	
}
	/*ObjectMapper mapper = new ObjectMapper();

	@RequestMapping("/save")
	public String saveData() {
		
		 * //Employee emp1 = new Employee("devops"); Set<Address> addr = new
		 * HashSet<Address>(); addr.add(new Address(12, "bhd", "nrml")); addr.add(new
		 * Address(13, "tuh", "bhs")); emp1.setAddr(addr); //dao.save(emp1);
		 
		
		 * Company apple = new Company("Apple"); Company samsung = new
		 * Company("Samsung");
		 * 
		 * Product iphone7 = new Product("Iphone 7", apple); Product iPadPro = new
		 * Product("IPadPro", apple);
		 * 
		 * Product galaxyJ7 = new Product("GalaxyJ7", samsung); Product galaxyTabA = new
		 * Product("GalaxyTabA", samsung);
		 * 
		 * apple.setProducts(new HashSet<Product>() { { add(iphone7); add(iPadPro); }
		 * });
		 * 
		 * samsung.setProducts(new HashSet<Product>() { { add(galaxyJ7);
		 * add(galaxyTabA); } }); Company jack = new Company("Jack"); Set<Product>
		 * product = new HashSet<Product>(); product.add(new Product("Jackson", jack));
		 * product.add(new Product("jackathon", jack)); jack.setProducts(product); //
		 * save companies dao.save(apple); dao.save(samsung); dao.save(jack);
		 
		// System.out.println(apple.getProducts());
		
		 * Parent p1=new Parent("Marsh"); List<Child> c1=new ArrayList<Child>();
		 * c1.add(new Child("child1",46.36521)); c1.add(new Child("child2",52.3625));
		 * c1.add(new Child("child3",58.36952)); p1.setChild(c1); pdao.save(p1);
		 

		
		 Parent p3=new Parent("harish"); 
		 List<Child> child5=new ArrayList<Child>();
		  child5.add(new Child("balu",65.02354)); 
		  child5.add(new Child("abhi",54.36225)); 
		  child5.add(new Child("venu",58.36225));
		  
		  p3.setChild(child5); 
		  pdao.save(p3);
		 
		Parent p2 = new Parent("mahi");
		List<Child> child1 = new ArrayList<Child>();
		Child c4 = new Child("kalpana", 65.362);
		c4.setParent(p2);
		Child c5 = new Child("reddy", 55.362);
		c5.setParent(p2);
		Child c6 = new Child("mani", 45.362);
		c6.setParent(p2);
		child1.add(c4);
		child1.add(c5);
		child1.add(c6);
		dao.save(child1);
		
		List<Doctor> doctor=new ArrayList<Doctor>();
		doctor.add(new Doctor("pankaj","mbbs"));
		doctor.add(new Doctor("kedar","surgen"));
		doctor.add(new Doctor("rakesh","rmp"));
		Patient patient=new Patient("hayden","aus",doctor);
		pdao.save(patient);
		Doctor doctor1=new Doctor("manoher","nerv");
		List<Patient> patient1=new ArrayList<Patient>();
		Patient p1=new Patient("pallavi","hyd");
		Patient p2=new Patient("parimal","hyd");
		Patient p3=new Patient("penukula","hyd");
		p1.setDoctor(doctor);
		p2.setDoctor(doctor);
		p3.setDoctor(doctor);
		patient1.add(p1);
		patient1.add(p2);
		patient1.add(p3);
		doctor1.setPatient(patient1);
		ddao.save(doctor1);
		
		 * Child child=new Child("rohan",23.658); List<Parent> parent=new
		 * ArrayList<Parent>(); parent.add(new Parent("srikar")); parent.add(new
		 * Parent("manohar")); parent.add(new Parent("shankar"));
		 * child.setParent(parent); dao.save(child);
		 

		return "Reg";
	}

	
	 * @RequestMapping("/show") public Set<CompanyDto> showdata() { String result =
	 * "<html>"; // return dao.findAll().toString(); // return
	 * pdao.findOne(72).toString(); // System.out.println(dao.findAll()); for
	 * (Company comHomePage : dao.findAll()) { result += "<div>" + com.toString() +
	 * "</div>"; } return new CompanyDto().getAllCompanies(dao.findAll());
	 * 
	 * }
	 
	@RequestMapping("/show")
	public List<Patient> showData() {

		return pdao.findAll();
		// return "home";

	}

	@RequestMapping("/delete")
	public String delete() {
		pdao.deleteAll();
		return "deleted";
	}

	@RequestMapping("/deleteone")
	public List<Patient> deleteChild(@RequestParam("id") int id) {
		Patient child = pdao.findOne(id);
		pdao.delete(child);
		return pdao.findAll();
	}

	@RequestMapping("/add")
	public List<Patient> addChild(@RequestParam("id") int id) {
		Doctor parent = ddao.findOne(id);
		Patient child = new Patient("varun2","boath");
		Patient child2=new Patient("varma","chennai");
		List<Patient> patient=new ArrayList<Patient>();
		patient.add(child);
		patient.add(child2);
		parent.setPatient(patient);
		pdao.save(child);

		return pdao.findAll();
	}
	@RequestMapping("/adddoctor")
	public List<Doctor> addDoctor(@RequestParam("id") int id) {
		Patient patient = pdao.findOne(id);
		Doctor doctor1 = new Doctor("raheem","surgen");
		Doctor doctor2 = new Doctor("yadav","master");
		List<Doctor> doctor=new ArrayList<Doctor>();
		doctor.add(doctor1);
		doctor.add(doctor2);
		patient.setDoctor(doctor);
		pdao.save(patient);

		return ddao.findAll();
	}
	

	@RequestMapping("/data")
	public Patient show(@RequestParam("id") int id) {
		return pdao.findOne(id);

	}

	
	  @RequestMapping("/findbyid")
	  public Patient	 findById(@RequestParam("id") int id) 
	  {
		  return pdao.findOne(id);
	  }
	  
	  @RequestMapping("/delete") public String delete() {
	 * 
	 * dao.deleteAll(); return "All Deleted";
	 * 
	 * }
	 * 
	 * @RequestMapping("/update") public String update(@RequestParam("id") int id) {
	 * Company company = dao.findOne(id); // company.setName("vijay");
	 * company.setName("Ashes"); dao.save(company); // return dao.findOne(id);
	 * return "updated"; }
	 
}*/
