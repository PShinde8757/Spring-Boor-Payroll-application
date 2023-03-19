package com.PS.payrollapplication.Assemblers;


import com.PS.payrollapplication.Controller.EmployeeController;
import com.PS.payrollapplication.Model.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).oneEmployee(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).allEmployee()).withRel("All Employee"));
    }
}
