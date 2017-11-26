package cz.muni.fi.pa165.carparkmanager.service.utils;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 *
 * @author Roman Nedelka
 */
public interface DataMapper {

    public <T> List<T> mapTo(Collection<?> from, Class<T> to);

    public <T> T mapTo(Object from, Class<T> to);

    public Mapper getMapper();

}
