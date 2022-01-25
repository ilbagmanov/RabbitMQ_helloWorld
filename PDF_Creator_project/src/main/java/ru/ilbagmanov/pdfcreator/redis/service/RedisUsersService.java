package ru.ilbagmanov.pdfcreator.redis.service;

import ru.ilbagmanov.pdfcreator.model.User;

public interface RedisUsersService {

    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);
}
