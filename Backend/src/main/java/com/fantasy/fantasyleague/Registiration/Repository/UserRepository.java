package com.fantasy.fantasyleague.Registiration.Repository;

import com.fantasy.fantasyleague.Registiration.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email OR u.userName = :userName")
    User findByEmailOrUserName(String email, String userName);

    // get all users
    @Query("SELECT new com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO(u.email, u.userName) FROM User u")
    Page<User> findAllUsers(Pageable pageable);

    // search by specification
    Page<User> findAll(Specification<User> spec, Pageable pageable);

    User findByUserName(String userName);
}
