package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.service.EmployeeService;
import com.cognizant.orm_learn.hibernate.HibernateEmployeeDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrmLearnApplication {

    private static EmployeeService employeeService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        employeeService = context.getBean(EmployeeService.class);

        // Spring Data JPA
        Employee emp1 = new Employee();
        emp1.setName("John");
        emp1.setSalary(50000.0);
        employeeService.addEmployee(emp1);

        // Hibernate
        HibernateEmployeeDao dao = new HibernateEmployeeDao();
        Employee emp2 = new Employee();
        emp2.setName("Alice");
        emp2.setSalary(60000.0);
        dao.addEmployee(emp2);
    }
}
