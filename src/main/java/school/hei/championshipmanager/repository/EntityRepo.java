package school.hei.championshipmanager.repository;

import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;

import java.util.List;

public interface EntityRepo<T, I> {

    public List<T> getAll(Integer page, Integer pageSize);
    public T getById(I id) throws EntityNotFoundException;
    public int add(T entity) throws EntityAlreadyExistException;
    public int update(T entity);
    public int delete(I id) throws EntityNotFoundException;
}
