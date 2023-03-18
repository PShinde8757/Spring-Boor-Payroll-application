package com.PS.payrollapplication.Controller;


import com.PS.payrollapplication.CustomException.EmployeeCustomException;
import com.PS.payrollapplication.Model.Employee;
import com.PS.payrollapplication.Repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employee")                            // get all employee
    List<Employee> all(){
        return repository.findAll();
    }

    @GetMapping("/employee/{id}")                                           // get one employee
    Employee one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(()->new EmployeeCustomException(id));
    }

    @PostMapping("/employee")                                               // add employee
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    @PutMapping("/employee/{id}")                                           // edit employee
    Employee replaceEmployee (@RequestBody Employee newEmployee, @PathVariable Long id){
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(()-> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employee/{id}")                                        // delete employee
    ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
