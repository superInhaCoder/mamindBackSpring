package mond.mamind.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64)
    private String subject;
    @Column(length = 64)
    private String content;
}
