package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.AddressRepository;
import model.Address;

@Controller
public class AddressController {
	@Autowired
	public AddressRepository aReopsitory;

	@RequestMapping("/addressPage")
	public String homepage() {
		return "Address";
	}

	@RequestMapping(value = "/saveAddress", method = RequestMethod.POST)
	public String addAddress(Address address,Model model) {
		aReopsitory.save(address);
		String msg="successfully saved with hno"+address.getHno();
		model.addAttribute("msg",msg);
		return "Address";
	}

	@RequestMapping("/deleteAddress")
	public String delete() {
		aReopsitory.deleteAll();
		return "deleted";
	}
}
