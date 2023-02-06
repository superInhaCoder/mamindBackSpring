package mond.mamind.src.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @Type(type = "uuid-char")
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