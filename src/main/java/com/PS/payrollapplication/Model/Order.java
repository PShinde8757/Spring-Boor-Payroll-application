package com.PS.payrollapplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name="CUSTOMER_ORDER")
public class Order {
    private @GeneratedValue @Id Long id;
    private String description;
    private Status status;

    public  Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order(String description, Status status) {
        this.description = description;
        this.status = status;
    }


}