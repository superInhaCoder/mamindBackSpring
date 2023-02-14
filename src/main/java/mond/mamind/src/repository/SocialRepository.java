package mond.mamind.src.repository;

import mond.mamind.src.domain.Social;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SocialRepository extends JpaRepository<Social, Long> {
    Social findBySubAndSocialNum(String sub, Long socialNum);
}
