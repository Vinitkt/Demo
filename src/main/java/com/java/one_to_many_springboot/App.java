package com.java.one_to_many_springboot;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */

@Configuration
@SpringBootApplication
@EnableJpaRepositories("dao")
@EntityScan("model")
@ComponentScan({ "controller","config" ,"utility"})
public class App extends SpringBootServletInitializer{
	final static Logger logger = Logger.getLogger(App.class);
	private int maxUploadSizeInMb = 1;
	public static void main(String[] args)  {
		SpringApplication.run(App.class, args);
		
	}

	/*@Transactional
	public void run() throws Exception {
		Employee emp1 = new Employee("testers");
		Set<Address> addr = new HashSet<Address>();
		addr.add(new Address(12, "bhd", "nrml"));
		addr.add(new Address(13, "tuh", "bhs"));
		// emp1.setAddr(addr);
		logger.debug("inside condtion" + dao.count());

		dao.save(emp1);
		System.out.println("address is " + emp1.getEname());
	}*/
}
