package ismomotors.byd.dynorblue.api.user.service.impl;

import ismomotors.byd.dynorblue.api.user.dto.AuthResponse;
import ismomotors.byd.dynorblue.api.user.dto.LoginRequest;
import ismomotors.byd.dynorblue.api.user.entity.User;
import ismomotors.byd.dynorblue.api.user.repository.UserRepository;
import ismomotors.byd.dynorblue.api.user.service.UserService;
import ismomotors.byd.dynorblue.api.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Bad credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token, user.getUsername());
    }
}
