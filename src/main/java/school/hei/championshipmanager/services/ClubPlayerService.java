package school.hei.championshipmanager.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.championshipmanager.dto.ClubPlayerRest;
import school.hei.championshipmanager.mappers.ClubPlayerMapper;
import school.hei.championshipmanager.model.ClubPlayer;
import school.hei.championshipmanager.repository.ClubPlayerRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ClubPlayerService {

    private final ClubPlayerRepository clubPlayerRepository;
    private final ClubPlayerMapper clubPlayerMapper;

    public List<ClubPlayerRest> getPlayers(String nameContaining, String clubNameContaining,
                                           Integer ageMin, Integer ageMax,
                                           Integer page, Integer pageSize)
    {
        List<ClubPlayer> players = clubPlayerRepository.getAllFiltered(nameContaining, clubNameContaining,
                                                                        ageMin, ageMax,
                                                                        page, pageSize);

        return players.stream().map(clubPlayerMapper::toDTO).toList();
    }
}
