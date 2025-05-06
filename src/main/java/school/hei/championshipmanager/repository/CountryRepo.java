package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.CountryMapper;
import school.hei.championshipmanager.model.Country;

import java.util.List;

@AllArgsConstructor
@Repository
public class CountryRepo implements EntityRepo<Country, String> {

    private final CountryMapper countryMapper;
    private final BaseRepo<Country> baseRepo;

    @Override
    public List<Country> getAll(Integer page, Integer pageSize) {
        return baseRepo.getAll("""
                SELECT
                id, name, continent
                FROM
                country
                """, null, page, pageSize, countryMapper);
    }

    public Country getByName(String name) throws EntityNotFoundException {
        return baseRepo.getAll("""
                SELECT
                id, name, continent
                FROM
                country
                WHERE name ILIKE ?
                """, List.of(name), null, null, countryMapper).getFirst();
    }

    @Override
    public Country getById(String id) throws EntityNotFoundException {
        return baseRepo.getAll("""
                SELECT
                id, name, continent
                FROM
                country
                WHERE id = ?
                """, List.of(id), null, null, countryMapper).getFirst();
    }

    @Override
    public int add(Country country) throws EntityAlreadyExistException {
        return baseRepo.add("""
                INSERT INTO
                country
                (id, name, continent)
                VALUES
                (?, ?, ?, ?)
                """, country, countryMapper);
    }

    @Override
    public int update(Country country) {
        return baseRepo.update("""
                UPDATE
                country
                SET
                name = ?, continent = ?
                WHERE
                id = ?
                """, country, countryMapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("""
                DELETE FROM country WHERE id = ?
                """, id);
    }
}
