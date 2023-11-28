package com.fantasy.fantasyleague.Registiration.Repository;

import com.fantasy.fantasyleague.Registiration.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByEmail(String email);

    @Query("SELECT u FROM Admin u WHERE u.email = :email OR u.userName = :userName")
    Admin findByEmailOrUserName(String email, String userName);

}
