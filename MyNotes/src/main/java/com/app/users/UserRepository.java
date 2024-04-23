package com.app.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query("DELETE FROM User u WHERE u.login= :name")
    void deleteUserByLogin(String name);

    User findByLogin(String username);
}
