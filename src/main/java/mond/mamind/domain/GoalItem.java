package mond.mamind.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class GoalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64)
    private String content;
    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    public GoalItem() {

    }
}
