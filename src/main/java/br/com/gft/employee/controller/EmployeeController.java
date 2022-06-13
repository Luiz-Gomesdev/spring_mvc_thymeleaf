package br.com.gft.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.employee.entities.Employee;
import br.com.gft.employee.repositories.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping({ "/list", "/" })
	public ModelAndView getAllEmployees() {
		ModelAndView mv = new ModelAndView("list-employees");
		mv.addObject("employees", employeeRepository.findAll());
		return mv;
	}

	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mv = new ModelAndView("add-employee-form");
		Employee newEmployee = new Employee();
		mv.addObject("employee", newEmployee);
		return mv;
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		employeeRepository.save(employee);
		return "redirect:/list";
	}

	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {

		ModelAndView mv = new ModelAndView("add-employee-form");

		Employee employee = employeeRepository.findById(employeeId).get();

		mv.addObject("employee", employee);

		return mv;

	}

	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {

		employeeRepository.deleteById(employeeId);

		return "redirect:/list";

	}

}
