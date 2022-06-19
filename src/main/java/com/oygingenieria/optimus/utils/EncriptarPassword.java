package com.oygingenieria.optimus.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword
{
    public static String encriptarPassword(String passwordUsuario)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(passwordUsuario);
    }
}
