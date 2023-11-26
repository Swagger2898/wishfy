package com.example.wishfy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wishfy.model.Employee;
import com.example.wishfy.repository.EmployeeRepo;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public void saveEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepo.findById(id);
    }

    public Optional<Employee> updateEmployee(Employee employee) {
        if (employeeRepo.existsById(employee.getId())) {
            return Optional.of(employeeRepo.save(employee));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Integer> deleteEmployee(int id) {
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            return Optional.of(id);
        } else {
            return Optional.empty();
        }
    }
}
