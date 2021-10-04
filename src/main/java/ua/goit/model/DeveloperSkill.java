package ua.goit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "developers_skills")

public class DeveloperSkill implements BaseEntity<Long> {

    private static final long serialVersionUID = 4613032931473118565L;

    @Id
    @Column(name = "id_developer")
    private Long developerId;

    @Column(name = "id_skill")
    private Long skillId;


    @Override
    public Long getId() {
        return developerId;
    }
}
