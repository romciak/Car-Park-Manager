package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.CarDao;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.DriveDao;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.ServiceCheckDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
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

    @Autowired
    private CarDao carDao;
    
    @Autowired
    private ServiceCheckDao serviceCheckDao;
    
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
        long fourMonths = 4l * 30 * 24 * 60 * 60 * 1000;
        long week = 7l * 24 * 60 * 60 * 1000;
        
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
                Date from = new Date( lastCheckDate.getTime() + fourMonths);
                Date to = new Date( lastCheckDate.getTime() + fourMonths + week);
                
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
    
}
