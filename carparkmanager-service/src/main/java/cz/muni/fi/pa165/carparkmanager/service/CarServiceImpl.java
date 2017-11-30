package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.api.exceptions.CarparkmanagerException;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.CarDao;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.DriveDao;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.ServiceCheckDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roman Nedelka
 */
@Service
public class CarServiceImpl implements CarService {

    public static final long KM_LIMIT = 300000L;

    @Autowired
    private CarDao carDao;
  
    @Autowired
    private ServiceCheckDao serviceCheckDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DriveDao driveDao;
  
    @Override
    public void create(Car c) {
        try {
            carDao.create(c);
        } catch (Exception e) {
            throw new DataAccessException("Cannot create Car: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void delete(Car c) {
        try {
            carDao.delete(c);
        } catch (Exception e) {
            throw new DataAccessException("Cannot delete Car: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void update(Car c) {
        try {
            carDao.update(c);
        } catch (Exception e) {
            throw new DataAccessException("Cannot update Car: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public Car findById(Long id) {
        try {
            return carDao.findById(id);
        } catch (Exception e) {
            throw new DataAccessException("Cannot find Car: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Car> findAll() {
        try {
            return carDao.findAll();
        } catch (Exception e) {
            throw new DataAccessException("Cannot findAll Car: " + e.getMessage(), e) {
            };
        }
    }
    
    @Override
    public ServiceCheck checkServiceInterval(Car car){
        
        Date now = new Date();
        long week = 7l * 24 * 60 * 60 * 1000;
        
        Date today = new Date();
        Date datePlusSixMonths = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(datePlusSixMonths);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+6));
        datePlusSixMonths = cal.getTime();
        
        List<ServiceCheck> carsServiceChecks = car.getServiceCheckList();
        
       
        if ( carsServiceChecks != null && !carsServiceChecks.isEmpty()){

            Date lastCheckDate = carsServiceChecks.get(0).getDoneWhen();
            if (lastCheckDate != null) {
                // find lastes service date
                for ( ServiceCheck service : carsServiceChecks) {
                    if (service.getDone()) {
                        if (service.getDoneWhen().after(lastCheckDate))
                            lastCheckDate = service.getDoneWhen();
                    } else {
                        // service is planed
                        return null;
                    }
                }
                
                // create sc 6 months after last
                Date from = today;
                Date to = datePlusSixMonths;
                
                cancelDrives(from, to, car);
                
                ServiceCheck createdSC = createServiceCheck( from, to, car);
                
                if (createdSC == null)
                    throw new DataAccessException("check service interval failed") {};
                
                return createdSC;
                
            } else{
                // service is planed
                return null;
            }
            
            
            
        } else {
            // never in service => create service now
            cancelDrives(now, new Date(now.getTime() + week), car);
            
            ServiceCheck createdSC = createServiceCheck(now, new Date(now.getTime() + week), car);
            
            if (createdSC == null)
                throw new DataAccessException("check service interval failed") {};

            return createdSC;
        }
    }
    
    private ServiceCheck createServiceCheck(Date from, Date to, Car car) {
        ServiceCheck sc = new ServiceCheck();
        sc.setIntervalFrom(from);
        sc.setIntervalTo(to);
        sc.setCar(car);
        sc.setDone(false);
        serviceCheckDao.create(sc);
        return sc;
    }
    
    private void cancelDrives(Date from, Date to, Car car) {
        List<Drive> drives = car.getDriveList();
        if (drives == null )
            return;
        // select drives with car
        for (Drive drive: drives) {
            // cancel drives
            if (drive.getTimeFrom().after(from) && drive.getTimeFrom().before(to) // zacina v intervalu
                    || drive.getTimeTo().after(from) && drive.getTimeTo().before(to) // konci v intervalu
                    || drive.getTimeFrom().before(from) && drive.getTimeTo().after(to) // zacina pred a konci po
                    || drive.getTimeFrom().equals(from) || drive.getTimeTo().equals(to) 
                    ) {
                driveDao.delete(drive);
            }
        }
        
    }
    
    @Override
    public void reserveDrive(long employeeId, long carId, Date from, Date to) throws CarparkmanagerException {
        Car car = carDao.findById(carId);
        if (car.getKmCount() > KM_LIMIT) {
            throw new CarparkmanagerException("Cannot lend a car - count of kilometers exceeded. ");
        }

        Employee employee = employeeDao.findById(employeeId);

        Drive drive = new Drive();
        drive.setCar(car);
        drive.setEmployee(employee);
        drive.setTimeFrom(from);
        drive.setTimeTo(to);

        driveDao.create(drive);

    }

}
