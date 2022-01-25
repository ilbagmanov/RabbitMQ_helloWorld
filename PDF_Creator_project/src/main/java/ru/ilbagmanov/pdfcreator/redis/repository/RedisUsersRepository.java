package ru.ilbagmanov.pdfcreator.redis.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.ilbagmanov.pdfcreator.redis.model.RedisUser;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}
