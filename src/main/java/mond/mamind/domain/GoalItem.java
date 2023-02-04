package mond.mamind.domain;

import jakarta.persistence.*;
import lombok.Data;

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
