package ru.ilbagmanov.pdfcreator.util;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;

public class JwtUtils {

    public static Long getId(String token) {
        String id = JWT.decode(token).getSubject();
        return Long.valueOf(id);
    }
}
