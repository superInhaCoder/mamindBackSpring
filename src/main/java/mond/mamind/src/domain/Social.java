package mond.mamind.src.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private Long socialNum; // 1: Google, 2: Apple
    private String sub;
    private String email;
    public Social() {

    }
}
