package com.fantasy.fantasyleague.Registiration.Repository;

import com.fantasy.fantasyleague.Registiration.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email OR u.userName = :userName")
    User findByEmailOrUserName(String email, String userName);

}
