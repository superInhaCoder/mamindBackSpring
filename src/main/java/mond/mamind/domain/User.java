package mond.mamind.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    @Column(length = 64)
    private String sub;
    @Column(length = 64)
    private String name;
    @ManyToOne
    @JoinColumn(name = "social_id")
    private Social social;
    private LocalDateTime createDate;

    public User() {

    }
}