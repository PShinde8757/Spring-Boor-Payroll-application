package com.PS.payrollapplication.Configs;


import com.PS.payrollapplication.Model.Employee;
import com.PS.payrollapplication.Model.Order;
import com.PS.payrollapplication.Model.Status;
import com.PS.payrollapplication.Repository.EmployeeRepository;
import com.PS.payrollapplication.Repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class LoadDatabase {

    private final static Logger log= LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner intiDatabase(EmployeeRepository employeeRepository , OrderRepository orderRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                log.info("Preloading "+employeeRepository.save(new Employee("JE","Pratik","Shinde")));
                log.info("Preloading "+employeeRepository.save(new Employee("SE","Sagar","Thapa")));

//                employeeRepository.findAll().forEach(employee -> log.info("Preloaded "+ employee));

                orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
                orderRepository.save(new Order("iPhone",Status.IN_PROGRESS));

                orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
            }
        };
    }
}
