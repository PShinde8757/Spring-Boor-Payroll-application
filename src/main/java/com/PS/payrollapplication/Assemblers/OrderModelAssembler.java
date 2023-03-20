package com.PS.payrollapplication.Assemblers;

import com.PS.payrollapplication.Controller.OrderController;
import com.PS.payrollapplication.Model.Order;
import com.PS.payrollapplication.Model.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    @Override
    public EntityModel<Order> toModel(Order order) {
        EntityModel<Order> ordermodel = EntityModel.of(order,
                linkTo(methodOn(OrderController.class).oneOrder(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).allOrder()).withRel("All Order "));

    return ordermodel;
    }
}
