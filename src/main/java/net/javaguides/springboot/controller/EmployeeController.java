package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/tableDetails")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
   public List<Employee> getAllEmployees(){
       return employeeRepository.findAll();
    }

    // build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestParam String providerName, @RequestParam String flowName, @RequestBody Employee employee) {
    //   DownTim downTime = new DownTime(downTimeInfo.downTimeStart, downTimeInfo.downTimeEnd);
        Employee newProvider = new Employee(providerName, flowName, employee.downTimeFrom, employee.downTimeTo);
        return employeeRepository.save(newProvider);

        // return employeeRepository.save(employee);
    }

    // build get employee by id REST API
    @GetMapping("specific")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam String providerName, @RequestParam String flowName){
        Employee employee = employeeRepository.findById(providerName)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + providerName));
        return ResponseEntity.ok(employee);
    }

    // build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id,@RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setProviderName(employeeDetails.getProviderName());
        updateEmployee.setFlowName(employeeDetails.getFlowName());
        updateEmployee.setDownTimeFrom(employeeDetails.getDownTimeFrom());
        updateEmployee.setDownTimeTo(employeeDetails.getDownTimeTo());


        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}