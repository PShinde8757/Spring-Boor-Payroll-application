package com.PS.payrollapplication.Controller;


import com.PS.payrollapplication.Assemblers.EmployeeModelAssembler;
import com.PS.payrollapplication.CustomException.EmployeeNotFoundException;
import com.PS.payrollapplication.Model.Employee;
import com.PS.payrollapplication.Repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
    public CollectionModel<EntityModel<Employee>> allEmployee(){
        List<EntityModel<Employee>> employees= repository.findAll()
                .stream()
                .map(employeeModelAssembler::toModel)
                .toList();
        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).allEmployee()).withSelfRel());
    }

    @GetMapping("/employee/{id}")                                          // get one employee
    public EntityModel<Employee> oneEmployee(@PathVariable Long id) {

        Employee employee= repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return employeeModelAssembler.toModel(employee);
    }

    @PostMapping("/employee")                                               // add employee
    public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee){
        EntityModel<Employee> addEmployee=employeeModelAssembler.toModel(repository.save(newEmployee));
        return ResponseEntity
                .created(addEmployee.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(addEmployee);

    }

    @PutMapping("/employee/{id}")                                           // edit employee
    ResponseEntity<?> replaceEmployee (@RequestBody Employee newEmployee, @PathVariable Long id){
        Employee updatedEmployee= repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(()-> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
        EntityModel<Employee> addEmployee= employeeModelAssembler.toModel(updatedEmployee);
        return ResponseEntity
                .created(addEmployee.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(addEmployee);
    }

    @DeleteMapping("/employee/{id}")                                        // delete employee
    ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
