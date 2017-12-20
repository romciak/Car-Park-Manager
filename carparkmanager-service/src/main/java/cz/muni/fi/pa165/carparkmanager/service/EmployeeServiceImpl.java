package cz.muni.fi.pa165.carparkmanager.service;

import cz.muni.fi.pa165.carparkmanager.persistence.dao.EmployeeDao;
import cz.muni.fi.pa165.carparkmanager.persistence.entity.Employee;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author xbonco1
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void create(Employee employee) {
        try {
            employeeDao.create(employee);
        } catch (Exception e) {
            throw new DataAccessException("Cannot create employee: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void update(Employee employee) {
        try {
            employeeDao.update(employee);
        } catch (Exception e) {
            throw new DataAccessException("Cannot update employee: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void delete(Employee employee) {
        try {
            employeeDao.delete(employee);
        } catch (Exception e) {
            throw new DataAccessException("Cannot delete employee: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public Employee findById(Long id) {
        try {
            return employeeDao.findById(id);
        } catch (Exception e) {
            throw new DataAccessException("Cannot find employee by Id: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Employee> findAll() {
        try {
            return employeeDao.findAll();
        } catch (Exception e) {
            throw new DataAccessException("Cannot findAll employee: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void registerEmployee(Employee employee, String password) {
        try {
            employee.setPasswordHash(createHash(password));
            employeeDao.create(employee);
        } catch (NullPointerException npe){
            throw npe;
        } catch (Exception e){
            throw new DataAccessException("Cannot register employee: " + e.getMessage(), e){
            };
        }
    }

    @Override
    public boolean authenticate(Employee employee, String password) {
        try {
            return validatePassword(password, employee.getPasswordHash());
        } catch (NullPointerException npe){
            throw npe;
        } catch (Exception e){
            throw new DataAccessException("Cannot authenticate employee: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public boolean isAdmin(Employee employee) {
        try {
            return employeeDao.findById(employee.getId()).isAdmin();
        } catch (NullPointerException npe) { 
            throw npe;
        } catch (Exception e) {
            throw new DataAccessException("Cannot check if isAdmin, employee: "+ e.getMessage(), e){  
            };
        }
    }
    
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }
    
    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
