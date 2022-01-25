package ru.ilbagmanov.pdfcreator.service;

import ru.ilbagmanov.pdfcreator.dto.EmailPasswordDto;
import ru.ilbagmanov.pdfcreator.dto.TokenDto;

public interface LoginService {
    TokenDto login(EmailPasswordDto emailPassword);
}
