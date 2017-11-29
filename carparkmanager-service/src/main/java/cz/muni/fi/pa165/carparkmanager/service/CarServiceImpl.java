package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.CarDao;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.DriveDao;
import cz.muni.fi.pa165.carparkmanager.persistence.dao.ServiceCheckDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.ServiceCheck;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Car;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Drive;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
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
    public ServiceCheck checkServiceInterval(Car car) {

        Date currentTime = new Date();
        Date currentTimePlusWeek = DateUtils.addWeeks(currentTime, 1);

        List<ServiceCheck> carServiceCheckList = car.getServiceCheckList();

        // car doesnt have any service checks planned => create one with range
        // of one week from 'now' on and cancel all the planned drives within
        // this week; employees cannot drive a car, that hasnt been checked
        if (carServiceCheckList == null || carServiceCheckList.isEmpty()) {
            cancelDrives(currentTime, currentTimePlusWeek, car);
            ServiceCheck createdSC = createServiceCheck(currentTime, currentTimePlusWeek, car);
            return createdSC;
        }

        Date latestCheckDate = carServiceCheckList.get(0).getDoneWhen();
        // check is planned, but not done - no need to plan the new one
        if (latestCheckDate == null) {
            return null;
        }

        // go through remaining checks and find the latest
        for (ServiceCheck serviceCheck : carServiceCheckList) {
            if (serviceCheck.getDone()) {
                if (serviceCheck.getDoneWhen().after(latestCheckDate)) {
                    latestCheckDate = serviceCheck.getDoneWhen();
                }
            } else {
                // check is planned, but not done - no need to plan the new one
                return null;
            }
        }

        // all service checks are done, plan the new one 6 months after the last one
        // range of the check = one week
        Date latestCheckPlusSixMonths = DateUtils.addMonths(latestCheckDate, 6);
        Date from = latestCheckPlusSixMonths;
        Date to = DateUtils.addWeeks(latestCheckPlusSixMonths, 1);

        cancelDrives(from, to, car);

        ServiceCheck createdSC = createServiceCheck(from, to, car);

        return createdSC;
    }

    /**
     * Creates service check with the given time interval.
     *
     * @param from start of the interval
     * @param to end of the interval
     * @param car car to plan the service check to
     * @return created service check
     */
    private ServiceCheck createServiceCheck(Date from, Date to, Car car) {
        ServiceCheck sc = new ServiceCheck();
        sc.setIntervalFrom(from);
        sc.setIntervalTo(to);
        sc.setCar(car);
        sc.setDone(false);
        try {
            serviceCheckDao.create(sc);
        } catch (Exception e) {
            throw new DataAccessException("Cannot create ServiceCheck: " + e.getMessage(), e) {
            };
        }
        return sc;
    }

    /**
     * Deletes drives, which are in time collision with a service check.
     *
     * @param from start of the time interval
     * @param to end of the time interval
     * @param car car, that drives belong to
     */
    private void cancelDrives(Date from, Date to, Car car) {
        List<Drive> drives = car.getDriveList();
        if (drives == null) {
            return;
        }
        // go through car's drives and check time intervals
        for (Drive drive : drives) {
            // cancel drives
            if (drive.getTimeFrom().after(from) && drive.getTimeFrom().before(to)
                    || drive.getTimeTo().after(from) && drive.getTimeTo().before(to)
                    || drive.getTimeFrom().before(from) && drive.getTimeTo().after(to)
                    || drive.getTimeFrom().equals(from) || drive.getTimeTo().equals(to)) {
                driveDao.delete(drive);
            }
        }

    }

}
