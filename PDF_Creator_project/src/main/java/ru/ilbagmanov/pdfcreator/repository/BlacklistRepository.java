package ru.ilbagmanov.pdfcreator.repository;

public interface BlacklistRepository {
    void save(String token);

    boolean exists(String token);
}
