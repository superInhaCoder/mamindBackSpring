package mond.mamind.src.model.Login;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostPasswordRes {
    private String token;
}
