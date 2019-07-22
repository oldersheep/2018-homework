package com.xxx.micro.user.repository;

import com.xxx.micro.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description
 * @Date 2019/7/21 21:07
 * @Version 1.0
 */
@Repository
public class UserRepository {

    private ConcurrentMap<Long, User> repository =
            new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator =
            new AtomicLong(0);

    public Collection<User> findAll() {
        return repository.values();
    }

    public boolean save(User user) {
        Long id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.putIfAbsent(id, user) == null;
    }

}
