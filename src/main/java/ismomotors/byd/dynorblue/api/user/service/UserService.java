package ismomotors.byd.dynorblue.api.user.service;

import ismomotors.byd.dynorblue.api.user.dto.AuthResponse;
import ismomotors.byd.dynorblue.api.user.dto.LoginRequest;

public interface UserService {

    AuthResponse login(LoginRequest request);
}
