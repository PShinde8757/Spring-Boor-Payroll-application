package com.PS.payrollapplication.Controller;


import com.PS.payrollapplication.Model.Employee;
import com.PS.payrollapplication.Repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Locale;

@Slf4j
@SpringBootApplication
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employee")
    public List<Employee> all() {
        return repository.findAll();
    }
}
