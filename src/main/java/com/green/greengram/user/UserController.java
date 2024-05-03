package com.green.greengram.user;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.user.model.SignInPostReq;
import com.green.greengram.user.model.SignInRes;
import com.green.greengram.user.model.SignUpPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "유저 컨트롤러", description = "유저 CRUD, sign-up, sign-out")
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")
    @Operation(summary = "회원가입", description = "프로필 사진은 필수가 아닙니다.")
    public ResultDto<Integer> postUser(@RequestPart(required = false) MultipartFile pic ,
                                       @RequestPart SignUpPostReq p){
        log.info("pic: {}", pic);
        log.info("p: {}", p);
        int result = service.postSignUp(pic, p);

        return ResultDto.<Integer>builder().
                httpStatus(HttpStatus.OK).
                resultMsg("회원가입 성공").
                resultData(result).
                build();
    }

    @PostMapping("sign-in")
    @Operation(summary = "인증처리", description = "")
    public ResultDto<SignInRes> postSignIn(@RequestBody SignInPostReq p){
        log.info("p: {}", p);
        SignInRes result = service.postSignIn(p);

        return ResultDto.<SignInRes>builder().
                httpStatus(HttpStatus.OK).
                resultMsg("인증성공").
                resultData(result).
                build();
    }
}
