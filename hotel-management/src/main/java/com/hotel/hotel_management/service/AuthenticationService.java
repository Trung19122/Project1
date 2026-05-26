package com.hotel.hotel_management.service;


import com.hotel.hotel_management.dto.request.AuthenticationRequest;
import com.hotel.hotel_management.dto.request.InstrospectRequest;
import com.hotel.hotel_management.dto.request.LogoutRequest;
import com.hotel.hotel_management.dto.request.RefreshRequest;
import com.hotel.hotel_management.dto.response.AuthenticationResponse;
import com.hotel.hotel_management.dto.response.InstrospectResponse;
import com.hotel.hotel_management.entity.InvalidToken;
import com.hotel.hotel_management.entity.User;
import com.hotel.hotel_management.exception.AppException;
import com.hotel.hotel_management.exception.ErrorCode;
import com.hotel.hotel_management.repository.InvalidTokenRepository;
import com.hotel.hotel_management.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    InvalidTokenRepository invalidTokenRepository;
    @NonFinal
    @Value("${jwt.secret}")
    protected String SECRET_KEY ;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION ;

    @NonFinal
    @Value("${jwt.refresh-duration}")
    protected long REFRESH_DURATION ;

    public InstrospectResponse instrospect(InstrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        try {
            verifyToken(token,false);
        }catch (AppException e){
            return InstrospectResponse.builder()
                    .valid(false)
                    .build();
        }
        return InstrospectResponse.builder()
                .valid(true)
                .build();


    }
    public AuthenticationResponse isAuthenticated(AuthenticationRequest request){
        log.info("Authenticating user: {}", request.getUsername());
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));


        boolean passwordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
        log.info("Password match result: {}", passwordMatch);
        if (!passwordMatch) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token = generateToken(user);
        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    private String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimset = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("hotel-management.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION,ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimset.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Failed to sign JWT token",  e);
            throw new RuntimeException("Failed to sign JWT token", e);
        }
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role ->
                    stringJoiner.add("ROLE_" + role.getName())
            );
        }

        return stringJoiner.toString();
    }

    public void logOut(LogoutRequest request) throws ParseException, JOSEException {
        try{
            var signToken = verifyToken(request.getToken(),true);
            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
            InvalidToken invalidToken = InvalidToken.builder()
                    .id(jit)
                    .expiryTime(expiryTime)
                    .build();

            invalidTokenRepository.save(invalidToken);
        }catch (AppException e){
            log.info("Token already expired or invalid");
        }


    }

    private SignedJWT verifyToken(String token ,boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
        SignedJWT jwt = SignedJWT.parse(token);
        Date expirationTime = (isRefresh)
                ? new Date(jwt.getJWTClaimsSet().getIssueTime().toInstant().plus(REFRESH_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                : jwt.getJWTClaimsSet().getExpirationTime();
        var verfied = jwt.verify(verifier);
        if(!(verfied && expirationTime.after(new Date()))){
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        if (invalidTokenRepository.existsById(jwt.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        return jwt;
    }

    public AuthenticationResponse refreshToken (RefreshRequest request) throws ParseException, JOSEException {
        var signToken = verifyToken(request.getToken(),true);
        var jit = signToken.getJWTClaimsSet().getJWTID();
        var expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
        InvalidToken invalidToken = InvalidToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();

        invalidTokenRepository.save(invalidToken);
        var username = signToken.getJWTClaimsSet().getSubject();
        var user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        var token = generateToken(user);
        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

}
