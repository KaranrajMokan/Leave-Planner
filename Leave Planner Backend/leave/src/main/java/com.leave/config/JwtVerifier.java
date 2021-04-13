package com.leave.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.logging.Logger;

public class JwtVerifier {

    Algorithm algorithm;
    Logger logger = Logger.getLogger(Logger.class.getName());

    public void verifier(String secret, String rollNumber, String token) {
        try {
            algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(rollNumber).build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            logger.info("Error verifying JWT tokens");
        }
    }
}
