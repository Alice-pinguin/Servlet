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
@Entity(name = "skills")
public class Skill implements BaseEntity<Long> {

    private static final long serialVersionUID = -8495286847326134262L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "language")
    private String language;

    @Column(name = "level")
    private String level;

}