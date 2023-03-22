package com.PS.payrollapplication.Controller;

import com.PS.payrollapplication.Assemblers.OrderModelAssembler;
import com.PS.payrollapplication.CustomException.OrderNotFoundException;
import com.PS.payrollapplication.Model.Order;
import com.PS.payrollapplication.Model.Status;
import com.PS.payrollapplication.Repository.OrderRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler orderModelAssembler;

    public OrderController(OrderRepository repository, OrderModelAssembler orderModelAssembler) {
        this.orderRepository = repository;
        this.orderModelAssembler = orderModelAssembler;
    }


    @GetMapping("/order")
    public CollectionModel<EntityModel<Order>> allOrder(){
        List<EntityModel<Order>> orders= orderRepository.findAll()
                .stream()
                .map(orderModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(orders,
                linkTo(methodOn(OrderController.class).allOrder()).withSelfRel());
    }

    @GetMapping("/order/{id}")
    public EntityModel<Order> oneOrder(@PathVariable long id){
       Order order= orderRepository.findById(id)
               .orElseThrow(()->new OrderNotFoundException(id));

       return orderModelAssembler.toModel(order);
    }

    @PostMapping("/order")
    public ResponseEntity<EntityModel<Order>> newOrder(@RequestBody Order order){
        order.setStatus(Status.IN_PROGRESS);
        Order newOrder = orderRepository.save(order);

        return ResponseEntity
                .created(linkTo(methodOn(OrderController.class).oneOrder(newOrder.getId())).toUri())
                .body(orderModelAssembler.toModel(newOrder));
    }

    @DeleteMapping("/order/{id}/cancel")
    public  ResponseEntity<?>cancel(@PathVariable Long id){
        Order order= orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException(id));

        if(order.getStatus()==Status.IN_PROGRESS){
            order.setStatus(Status.CANCELLED);
            return  ResponseEntity.ok(orderModelAssembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method Not Allowed")
                        .withDetail("You Cannot Cancel this order that is in "+order.getStatus()+" Status"));
    }

    @PutMapping("/order/{id}/complete")
    public ResponseEntity<?>complete(@PathVariable Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException(id));

        if(order.getStatus()==Status.IN_PROGRESS){
            order.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(orderModelAssembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE,MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method Not Allowed")
                        .withDetail("You Cannot Complete this order that is in "+order.getStatus()+" Status"));
    }

    @DeleteMapping("/order/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
