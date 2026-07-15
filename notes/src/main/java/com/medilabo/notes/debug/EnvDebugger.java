package com.medilabo.notes.debug;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Temporary diagnostic class to verify environment variables
 * and Spring property resolution at startup.
 *
 * DELETE THIS CLASS before committing / deploying to production.
 */
@Component
public class EnvDebugger {

    @Value("${spring.data.mongodb.uri:NOT_SET}")
    private String mongoUri;

    @Value("${jwt.secret:NOT_SET}")
    private String jwtSecret;

    @PostConstruct
    public void checkEnv() {
        System.out.println("========== ENV DEBUG START ==========");

        // 1. Raw JVM-level environment variables (bypasses Spring entirely)
        String rawDbEnv = System.getenv("DB_CONNECT_STRING");
        String rawJwtEnv = System.getenv("JWT_SECRET");

        System.out.println("Raw System.getenv(\"DB_CONNECT_STRING\"): "
                + (rawDbEnv != null ? "SET (length " + rawDbEnv.length() + ")" : "NULL"));
        System.out.println("Raw System.getenv(\"JWT_SECRET\"): "
                + (rawJwtEnv != null ? "SET (length " + rawJwtEnv.length() + ")" : "NULL"));

        // 2. Spring-resolved properties (goes through placeholder resolution)
        System.out.println("Resolved spring.data.mongodb.uri: "
                + ("NOT_SET".equals(mongoUri) ? "NOT_SET" : "SET (length " + mongoUri.length() + ")"));
        System.out.println("Resolved jwt.secret: "
                + ("NOT_SET".equals(jwtSecret) ? "NOT_SET" : "SET (length " + jwtSecret.length() + ")"));

        System.out.println("=========== ENV DEBUG END ============");
    }
}


