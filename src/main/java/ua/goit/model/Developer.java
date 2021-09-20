package ua.goit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "developers")

public class Developer  implements BaseEntity<Long> {

    private static final long serialVersionUID = -6624844557650250520L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "age", nullable = false, length = 2)
    private Integer age;

    @Column(name = "gender", nullable = false, length = 6)
    private String gender;

    @Column(name = "salary", nullable = false, length = 10)
    private Long salary;
}

