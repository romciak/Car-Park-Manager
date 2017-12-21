package cz.muni.fi.pa165.carparkmanager.sample.data;

import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.ClassificationOfEmployeesEnum;
import cz.muni.fi.pa165.carparkmanager.persistence.enums.UserRoleEnum;
import cz.muni.fi.pa165.carparkmanager.service.CarService;
import cz.muni.fi.pa165.carparkmanager.service.DriveService;
import cz.muni.fi.pa165.carparkmanager.service.EmployeeService;
import cz.muni.fi.pa165.carparkmanager.service.ServiceCheckService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author romciak
 */
@Service
@Transactional
public class SampleDataLoaderFacadeImpl implements SampleDataLoaderFacade {

    @Autowired
    private CarService carService;

    @Autowired
    private DriveService driveService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ServiceCheckService serviceCheckService;

    private List<Car> carList = new ArrayList<>();

    private List<Employee> employeeList = new ArrayList<>();

    @Override
    public void loadSampleData() throws ParseException {
        createCars();
        createEmployees();
        createDrives();
        createServiceChecks();
    }

    private void createCars() {
        createCar("BMW", "fuel", 5000, "2010", "personal", "xxxx");
        createCar("Audi", "fuel", 6000, "2015", "personal", "yyyy");
    }

    private void createCar(String brand, String engineType, int kmCount, String productionYear, String type, String vin) {
        Car car = new Car();

        car.setBrand(brand);
        car.setEngineType(engineType);
        car.setKmCount(0);
        car.setProductionYear(productionYear);
        car.setType(type);
        car.setVin(vin);

        carService.create(car);
        carList.add(car);
    }

    private void createEmployees() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        createEmployee(format.parse("25.08.1976"), ClassificationOfEmployeesEnum.CLEANING_SERVICE, "Kevin", "McCallister", "kevin@example.com", "Kevko123");
        createEmployee(format.parse("20.16.1990"), ClassificationOfEmployeesEnum.ENGINEER, "Zdeno", "Chara", "zdeno@example.com", "zdenozpopradu");
        createEmployee(format.parse("31.01.1985"), ClassificationOfEmployeesEnum.VOLUNTEER, "Donald", "TheDuck", "donald@example.com", "URFired");
        createEmployee(format.parse("11.03.1977"), ClassificationOfEmployeesEnum.MANAGER, "Kiskaty", "Uze", "kiskaty@example.com", "najprezident");
    }

    private void createEmployee(Date dateOfBirth, ClassificationOfEmployeesEnum classification, String firstname, String surname, String email, String pass) {
        Employee employee = new Employee();

        employee.setBirthDate(dateOfBirth);
        employee.setClassification(classification);
        employee.setFirstname(firstname);
        employee.setSurname(surname);
        employee.setEmail(email);
        
        // Setting admin role 
        if (pass.equals("najprezident"))
        {
            employee.setUserRole(UserRoleEnum.ADMINISTRATOR);
        }
        else
        {
            employee.setUserRole(UserRoleEnum.USER);
        }
        
        employeeService.create(employee);
        employeeService.registerEmployee(employee, pass);
        employeeList.add(employee);
    }

    private void createDrives() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        createDrive(carList.get(0), employeeList.get(2), 150, format.parse("13.12.2017"), format.parse("05.11.2017"));
        createDrive(carList.get(1), employeeList.get(3), 150, format.parse("13.12.2017"), format.parse("05.11.2017"));
    }

    private void createDrive(Car car, Employee employee, int kmCount, Date timeFrom, Date timeTo) {
        Drive drive = new Drive();

        drive.setCar(car);
        drive.setEmployee(employee);
        drive.setKm(kmCount);
        drive.setTimeFrom(timeFrom);
        drive.setTimeTo(timeTo);

        driveService.create(drive);
    }

    private void createServiceChecks() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        createServiceCheck(carList.get(0), true, format.parse("15.10.2017"), format.parse("05.10.2017"), format.parse("15.10.2017"));
        createServiceCheck(carList.get(1), false, null, format.parse("08.01.2018"), format.parse("13.01.2018"));

    }

    private void createServiceCheck(Car car, boolean done, Date doneWhen, Date intervalFrom, Date intervalTo) {
        ServiceCheck serviceCheck = new ServiceCheck();

        serviceCheck.setCar(car);
        serviceCheck.setDone(done);
        serviceCheck.setDoneWhen(doneWhen);
        serviceCheck.setIntervalFrom(intervalFrom);
        serviceCheck.setIntervalTo(intervalTo);

        serviceCheckService.create(serviceCheck);
    }
}
