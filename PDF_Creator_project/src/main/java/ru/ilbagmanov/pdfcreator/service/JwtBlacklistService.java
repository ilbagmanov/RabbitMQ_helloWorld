package ru.ilbagmanov.pdfcreator.service;

public interface JwtBlacklistService {

    void add(String token);

    boolean exists(String token);
}
