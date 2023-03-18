package com.PS.payrollapplication.Repository;

import com.PS.payrollapplication.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <Employee,Long>{

}
