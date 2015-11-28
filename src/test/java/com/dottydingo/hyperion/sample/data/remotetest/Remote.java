package com.dottydingo.hyperion.sample.data.remotetest;

import com.dottydingo.hyperion.client.HyperionClient;
import com.dottydingo.hyperion.client.builder.RequestFactory;
import com.dottydingo.hyperion.client.builder.query.QueryBuilder;
import com.dottydingo.hyperion.sample.data.api.Department;
import com.dottydingo.hyperion.sample.data.api.Employee;
import com.dottydingo.hyperion.sample.data.api.Territory;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class Remote
{
    private static final DateTimeFormatter DATE_FORMATTER = ISODateTimeFormat.date();
    private static Logger logger = LoggerFactory.getLogger(Remote.class);

    public static void main(String[] args) throws Exception
    {
        QueryBuilder qb = new QueryBuilder();
        HyperionClient client = new HyperionClient("http://localhost:8080/data");

        RequestFactory<Department,Long> departmentFactory = new RequestFactory<>(Department.class);
        RequestFactory<Territory,Long> territoryFactory = new RequestFactory<>(Territory.class);
        RequestFactory<Employee,Long> employeeFactory = new RequestFactory<>(Employee.class);

        Department department = buildDepartment("Engineering");

        department = departmentFactory
                .create(department)
                .returnFields("id")
                .execute(client);

        logger.info("Department ID: {}",department.getId());

        List<Territory> territories = new ArrayList<>();
        territories.add(buildTerritory("Northeast"));
        territories.add(buildTerritory("Midwest"));

        territories = territoryFactory
                .create(territories)
                .returnFields("id")
                .execute(client);

        logger.info("Northeast ID: {}",territories.get(0).getId() );
        logger.info("Midwest ID: {}",territories.get(1).getId() );

        Employee supervisor = new Employee();
        supervisor.setFirstName("Joe");
        supervisor.setLastName("Supervisor");
        supervisor.setTitle("Pointer Haired Boss");
        supervisor.setDateOfBirth(DATE_FORMATTER.parseDateTime("1950-10-01").toDate());
        supervisor.setDateOfHire(DATE_FORMATTER.parseDateTime("2010-06-01").toDate());
        supervisor.setAddress("123 Main Street");
        supervisor.setCity("Seattle");
        supervisor.setState("WA");
        supervisor.setPostalCode("98122");
        supervisor.setHomePhone("5675551212");
        supervisor.setDepartmentId(department.getId());
        supervisor.setTerritoryIds(Arrays.asList(territories.get(0).getId(),territories.get(1).getId()));
        
        supervisor = employeeFactory
                .create(supervisor)
                .returnFields("id")
                .execute(client);

        logger.info("Supervisor ID: {}",supervisor.getId());

        Employee employee = new Employee();
        employee.setFirstName("Joe");
        employee.setLastName("Employee");
        employee.setTitle("Engineer");
        employee.setDateOfBirth(DATE_FORMATTER.parseDateTime("1960-10-01").toDate());
        employee.setDateOfHire(DATE_FORMATTER.parseDateTime("2012-06-01").toDate());
        employee.setAddress("123 Elm Street");
        employee.setCity("Seattle");
        employee.setState("WA");
        employee.setPostalCode("98122");
        employee.setHomePhone("5675551212");
        employee.setDepartmentId(department.getId());
        employee.setSupervisorId(supervisor.getId());
        employee.setTerritoryIds(Arrays.asList(territories.get(0).getId(),territories.get(1).getId()));

        employee = employeeFactory
                .create(employee)
                .returnFields("id")
                .execute(client);

        logger.info("Employee ID: {}",employee.getId());

    }

    private static Department buildDepartment(String name)
    {
        Department department = new Department();
        department.setName(name);

        return department;
    }

    private static Territory buildTerritory(String name)
    {
        Territory territory = new Territory();
        territory.setName(name);

        return territory;
    }
}
