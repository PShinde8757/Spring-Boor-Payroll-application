package com.PS.payrollapplication.Controller;

import com.PS.payrollapplication.CustomException.EmployeeNotFoundException;
import com.PS.payrollapplication.Model.Employee;
import com.PS.payrollapplication.Model.Order;
import com.PS.payrollapplication.Repository.OrderRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/order")
    public List<Order>allOrder(){
        return  repository.findAll();
    }

    @GetMapping("/order/{id}")
    public Order oneOrder(@PathVariable long id){
       return repository.findById(id)
               .orElseThrow(()->new EmployeeNotFoundException(id));

    }


}
