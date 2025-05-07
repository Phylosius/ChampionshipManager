package school.hei.championshipmanager.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.hei.championshipmanager.exeptions.EntityAlreadyExistException;
import school.hei.championshipmanager.exeptions.EntityNotFoundException;
import school.hei.championshipmanager.mappers.PlayerMapper;
import school.hei.championshipmanager.model.Player;

import java.util.List;

@AllArgsConstructor
@Repository
public class PlayerRepo implements EntityRepo<Player, String> {
    private final PlayerMapper playerMapper;
    private final BaseRepo<Player> baseRepo;

    @Override
    public List<Player> getAll(Integer page, Integer pageSize) {
        return baseRepo.getAll("""
                SELECT
                id, name, age, nationality
                FROM
                player
                """, null, null, null, playerMapper);
    }

    @Override
    public Player getById(String id) throws EntityNotFoundException {
        return baseRepo.getAll("""
                SELECT
                id, name, age, nationality
                FROM
                player
                WHERE id = ?
                """, List.of(id), null, null, playerMapper).getFirst();
    }

    public int save(Player player) {
        if (exists(player.getId())) {
            return update(player);
        } else {
            return add(player);
        }
    }

    @Override
    public int add(Player player) throws EntityAlreadyExistException {
        if (exists(player.getId())) {
            throw new EntityAlreadyExistException("Player with id " + player.getId() + " already exists");
        }

        return baseRepo.add("""
                INSERT INTO player
                (id, name, age, nationality)
                VALUES
                (?, ?, ?, ?)
                """, player, playerMapper);
    }

    @Override
    public int update(Player player) {
        return baseRepo.update("""
                UPDATE player
                SET name = ?, age = ?, nationality = ?
                WHERE id = ?
                """, player, playerMapper);
    }

    @Override
    public int delete(String id) throws EntityNotFoundException {
        return baseRepo.delete("DELETE FROM player WHERE id = ?", id);
    }

    public boolean exists(String id) {
        return baseRepo.isExists("SELECT COUNT(*) FROM player WHERE id = ?", id);
    }
}
