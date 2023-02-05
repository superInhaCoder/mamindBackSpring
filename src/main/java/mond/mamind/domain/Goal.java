package mond.mamind.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "goal_category_id")
    private GoalCategory goalCategory;
    @Column(length=16)
    String subject;
    @Column(length=16)
    String content;
    @Column(length=16)
    String step;

    public Goal() {

    }
}
