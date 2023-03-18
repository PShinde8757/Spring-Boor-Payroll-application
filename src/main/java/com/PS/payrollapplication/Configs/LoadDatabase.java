package com.PS.payrollapplication.Configs;


import com.PS.payrollapplication.Model.Employee;
import com.PS.payrollapplication.Repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class LoadDatabase {

    private final static Logger log= LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner intiDatabase(EmployeeRepository repository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                log.info("Preloading "+repository.save(new Employee("JE","Pratik","Shinde")));
                log.info("Preloading "+repository.save(new Employee("SE","Sagar","Thapa")));
            }
        };
    }

}
