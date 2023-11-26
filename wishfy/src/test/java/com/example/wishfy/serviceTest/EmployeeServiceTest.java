package com.example.wishfy.serviceTest;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.wishfy.model.Employee;
import com.example.wishfy.repository.EmployeeRepo;
import com.example.wishfy.service.EmployeeService;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testSaveEmployee() {
  
        Employee employee = new Employee(1, "John Doe", "john@example.com", "IT", Date.valueOf("2023-11-30"));

        employeeService.saveEmployee(employee);

        verify(employeeRepo, times(1)).save(employee);
    }

    @Test
    void testGetAllEmployees() {
        
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John Doe", "john@example.com", "IT", Date.valueOf("2023-11-30")),
                new Employee(2, "Jane Doe", "jane@example.com", "HR", Date.valueOf("2023-11-30"))
        );

        when(employeeRepo.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();
        
        assertEquals(employees, result);
    }

    @Test
    void testGetEmployeeById() {
  
        int employeeId = 1;
        Employee employee = new Employee(employeeId, "John Doe", "john@example.com", "IT",Date.valueOf("2023-11-30"));

        when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employee));

      
        Optional<Employee> result = employeeService.getEmployeeById(employeeId);

      
        assertEquals(Optional.of(employee), result);
    }

    @Test
    void testUpdateEmployee() {
       
        Employee existingEmployee = new Employee(1, "Swapnil", "Swapnil@example.com", "IT", Date.valueOf("2023-11-30"));
        Employee updatedEmployee = new Employee(1, "Swapnil Updated", "Swapnil@example.com", "IT", Date.valueOf("2023-11-30"));

        when(employeeRepo.existsById(existingEmployee.getId())).thenReturn(true);
        when(employeeRepo.save(updatedEmployee)).thenReturn(updatedEmployee);

        Optional<Employee> result = employeeService.updateEmployee(updatedEmployee);

        assertEquals(Optional.of(updatedEmployee), result);
    }

    @Test
    void testUpdateEmployee_NotFound() {
        
        Employee nonExistingEmployee = new Employee(1, "Non-Existing", "nonexisting@example.com", "IT", Date.valueOf("2023-11-30"));

        when(employeeRepo.existsById(nonExistingEmployee.getId())).thenReturn(false);

        Optional<Employee> result = employeeService.updateEmployee(nonExistingEmployee);

        assertEquals(Optional.empty(), result);
    }

    @Test
    void testDeleteEmployee() {
        
        int employeeId = 1;

        when(employeeRepo.existsById(employeeId)).thenReturn(true);

        Optional<Integer> result = employeeService.deleteEmployee(employeeId);

        assertEquals(Optional.of(employeeId), result);
        verify(employeeRepo, times(1)).deleteById(employeeId);
    }

    @Test
    void testDeleteEmployee_NotFound() {
        
        int nonExistingEmployeeId = 99;

        when(employeeRepo.existsById(nonExistingEmployeeId)).thenReturn(false);

        Optional<Integer> result = employeeService.deleteEmployee(nonExistingEmployeeId);

        assertEquals(Optional.empty(), result);
        verify(employeeRepo, never()).deleteById(nonExistingEmployeeId);
    }
}
