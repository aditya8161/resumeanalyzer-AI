package com.resumeanalyzer.auth;

import com.resumeanalyzer.dto.AuthResponse;
import com.resumeanalyzer.dto.LoginRequest;
import com.resumeanalyzer.dto.RegisterRequest;
import com.resumeanalyzer.entity.RefreshToken;
import com.resumeanalyzer.entity.User;
import com.resumeanalyzer.exception.AppException;
import com.resumeanalyzer.repository.RefreshTokenRepository;
import com.resumeanalyzer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${spring.application.jwt.refresh-token-expiry}")
    private long refreshTokenExpiry;

    public AuthResponse register(RegisterRequest request) {
        User user = userService.createUser(request);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = createAndSaveRefreshToken(user);

        return buildAuthResponse(user, accessToken, refreshToken);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );

        User user = userService.findByEmail(request.getEmail());

        refreshTokenRepository.deleteAllByUserId(user.getId());

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = createAndSaveRefreshToken(user);

        return buildAuthResponse(user, accessToken, refreshToken);
    }

    @Transactional
    public AuthResponse refreshToken(String refreshToken) {
        RefreshToken stored = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new AppException("Invalid refresh token", HttpStatus.UNAUTHORIZED));

        if (stored.isRevoked()) {
            throw new AppException("Refresh token revoked", HttpStatus.UNAUTHORIZED);
        }

        if (stored.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new AppException("Refresh token expired, please login again", HttpStatus.UNAUTHORIZED);
        }

        User user = stored.getUser();
        String newAccessToken = jwtService.generateAccessToken(user);

        return buildAuthResponse(user, newAccessToken, refreshToken);
    }

    @Transactional
    public void logout(Long userId) {
        refreshTokenRepository.deleteAllByUserId(userId);
    }

    private String createAndSaveRefreshToken(User user) {
        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .revoked(false)
                .expiryDate(LocalDateTime.now().plusSeconds(refreshTokenExpiry / 1000))
                .build();

        refreshTokenRepository.save(refreshToken);
        return token;
    }

    private AuthResponse buildAuthResponse(User user, String accessToken, String refreshToken) {
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().name())
                .build();
    }
}
