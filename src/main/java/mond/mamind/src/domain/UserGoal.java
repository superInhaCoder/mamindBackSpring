package mond.mamind.src.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table
public class UserGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Social social;
    private Boolean success;

    public UserGoal() {

    }
}