package com.springboot.app.services.Impli;

import com.springboot.app.Entities.Employee;
import com.springboot.app.Exceptions.ResourceNotFoundException;
import com.springboot.app.Repositories.EmployeeRepository;
import com.springboot.app.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpli implements EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpli(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public Employee getEmployeeById(long id) {
// Optional<Employee> employee = employeeRepository.findById(id);
// if(employee.isPresent()) {
// return employee.get();
// }else {
// throw new ResourceNotFoundException("Employee", "Id", id);
// }
        return employeeRepository.findById(id).orElseThrow(() ->
                new
                        ResourceNotFoundException("Employee", "Id", id));
    }
    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Employee existingEmployee =
                employeeRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Employee ","Id", id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
// save existing employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }
    @Override
    public void deleteEmployee(long id) {
// check whether a employee exist in a DB or not
        employeeRepository.findById(id).orElseThrow(() ->
                new
                        ResourceNotFoundException("Employee ","Id", id));
        employeeRepository.deleteById(id);
    }
}
