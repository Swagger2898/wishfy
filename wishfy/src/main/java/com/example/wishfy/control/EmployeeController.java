package com.example.wishfy.control;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wishfy.model.Employee;
import com.example.wishfy.repository.EmployeeRepo;

@RestController
public class EmployeeController {
	
    @Autowired
	EmployeeRepo re;

	@PostMapping("/add")
	public void saveEmp(@RequestBody Employee e) {
		re.save(e);
	}
	
	@GetMapping("/getList")
	public List<Employee> getEmps(){
		return re.findAll();
	}
	
	@GetMapping("/getEmp/{id}")
	public Optional<Employee> getEmpById(@PathVariable int id) {
		return re.findById(id);
	}
	
	@PutMapping("/update")
	public Employee updateEmp(@RequestBody Employee e) {
		if(re.existsById(e.getId())) {
			re.save(e);
			
		}
		return e;
	}

	@DeleteMapping("delete/{id}")
	public int deleteEmp(@PathVariable int id) {
		re.deleteById(id);
		return id;
	}

}
