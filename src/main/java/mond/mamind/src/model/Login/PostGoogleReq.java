package mond.mamind.src.model.Login;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostGoogleReq {
    @NotEmpty
    private String idToken;
}
