package com.PS.payrollapplication.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity

public class Employee {

        private @Id @GeneratedValue Long id;
        private String name;
        private String role;
        private String first_name;
        private String last_name;

        public Employee(String role,String first_name,String last_name){
            this.role=role;
            this.first_name=first_name;
            this.last_name=last_name;
        }

    public String getName() {
        return first_name+ " "+last_name;
    }

    public void setName(String name) {
        String[] firstnameAndLastname =name.split(" ");
        this.first_name= firstnameAndLastname[0];
        this.last_name=firstnameAndLastname[1];
    }
}
