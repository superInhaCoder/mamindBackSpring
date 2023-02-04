package mond.mamind.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class DoctorCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64)
    private String subject;

    public DoctorCategory() {

    }
}
