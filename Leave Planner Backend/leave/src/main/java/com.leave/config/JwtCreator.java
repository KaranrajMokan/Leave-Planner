package com.leave.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.util.logging.Logger;

public class JwtCreator {

    Algorithm algorithm;
    Logger logger = Logger.getLogger(Logger.class.getName());

    public String creator(String secret, String rollNumber){
        try {
            algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer(rollNumber).sign(algorithm);
        } catch (JWTCreationException exception){
            logger.info("Error creating JWT tokens");
        }
        return "";
    }
}
