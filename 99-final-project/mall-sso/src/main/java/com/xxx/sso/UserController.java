package com.xxx.sso;

import com.xxx.user.IUserCoreService;
import com.xxx.user.dto.UserLoginRequest;
import com.xxx.user.dto.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserCoreService iUserCoreService;

    @PostMapping("/login")
    public ResponseEntity doLogin(String username, String password) {
        UserLoginRequest request = new UserLoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        UserLoginResponse response = iUserCoreService.login(request);

        return ResponseEntity.ok(response);
    }
}
