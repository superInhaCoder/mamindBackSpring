package mond.mamind.src.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.apache.tomcat.util.net.AprEndpoint;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true, nullable = false)
    private String username;
    @NotNull
    @Column(nullable = false)
    private String password;
    private String name;
    private LocalDateTime createDate;
    public User() {

    }
}