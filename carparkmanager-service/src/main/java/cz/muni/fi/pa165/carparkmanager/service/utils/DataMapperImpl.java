package cz.muni.fi.pa165.carparkmanager.service.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roman Nedelka
 */
@Service
public class DataMapperImpl implements DataMapper {

    @Autowired
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(Collection<?> from, Class<T> to) {
        if (from == null){
            throw new DataAccessException("Object returned by called method is null"){};
        }
        
        
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : from) {
            mappedCollection.add(dozer.map(object, to));
        }
        return mappedCollection;
    }

    @Override
    public <T> T mapTo(Object from, Class<T> to) {
        if (from == null){
            throw new DataAccessException("Object returned by called method is null"){};
        }
        return dozer.map(from, to);
    }

    @Override
    public Mapper getMapper() {
        return dozer;
    }

}
