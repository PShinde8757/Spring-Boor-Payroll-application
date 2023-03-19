package com.PS.payrollapplication.Controller;


import com.PS.payrollapplication.Assemblers.EmployeeModelAssembler;
import com.PS.payrollapplication.CustomException.EmployeeNotFoundException;
import com.PS.payrollapplication.Model.Employee;
import com.PS.payrollapplication.Repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Slf4j
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeModelAssembler employeeModelAssembler;

    public EmployeeController(EmployeeRepository repository, EmployeeModelAssembler employeeModelAssembler) {
        this.repository = repository;
        this.employeeModelAssembler = employeeModelAssembler;
    }

    @GetMapping("/employee")                                                // get all employee
    public CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>> employees=(List<EntityModel<Employee>>) repository.findAll()
                .stream()
                .map(employeeModelAssembler::toModel)
                .toList();
        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @GetMapping("/employee/{id}")                                          // get one employee
    public EntityModel<Employee> one(@PathVariable(name = "id",required = true) Long id) {

        Employee employee= repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return employeeModelAssembler.toModel(employee);
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
