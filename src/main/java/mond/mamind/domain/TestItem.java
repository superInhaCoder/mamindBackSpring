package mond.mamind.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@Table
public class TestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64)
    private String content;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Social social;
}
