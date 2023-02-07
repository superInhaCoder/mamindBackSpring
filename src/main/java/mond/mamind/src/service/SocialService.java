package mond.mamind.src.service;

import mond.mamind.config.BaseException;
import mond.mamind.src.domain.User;
import mond.mamind.src.model.PostUserReq;
import mond.mamind.src.model.PostUserRes;
import mond.mamind.src.repository.SocialRepository;
import mond.mamind.src.repository.UserRepository;
import mond.mamind.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static mond.mamind.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Transactional
public class SocialService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final SocialRepository socialRepository;

    @Autowired
    public SocialService(JwtService jwtService, UserRepository userRepository, SocialRepository socialRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.socialRepository = socialRepository;
    }

    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        try {
            User user = new User();
            user.setCreateDate(LocalDateTime.now());
            user.setName(postUserReq.getName());
            user.setPassword(postUserReq.getPassword());
            userRepository.save(user);
            PostUserRes postUserRes = new PostUserRes();
            postUserRes.setToken(loginUser(user.getId()));
            return postUserRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public String loginUser(Long userId) throws BaseException {
        return jwtService.createJwt(userId);
    }

    /*
    // 회원정보 수정(Patch)
    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
        String pwd;
        try {
            // 암호화: postUserReq에서 제공받은 비밀번호를 보안을 위해 암호화시켜 DB에 저장합니다.
            // ex) password123 -> dfhsjfkjdsnj4@!$!@chdsnjfwkenjfnsjfnjsd.fdsfaifsadjfjaf
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(patchUserReq.getPassword()); // 암호화코드
            patchUserReq.setPassword(pwd);
        } catch (Exception ignored) { // 암호화가 실패하였을 경우 에러 발생
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            int result = userDao.modifyUserName(patchUserReq); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // 회원탈퇴(Delete)
    public void deleteUser(int userIdx) throws BaseException {
        try {
            int result = userDao.deleteUser(userIdx); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
            if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
                throw new BaseException(MODIFY_FAIL_DELETEUSER);
            }
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            throw new BaseException(DATABASE_ERROR);
        }
    }*/
}
