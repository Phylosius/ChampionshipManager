package school.hei.championshipmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.hei.championshipmanager.enums.Continent;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country {
    private String id;
    private String name;
    private Continent continent;
}
