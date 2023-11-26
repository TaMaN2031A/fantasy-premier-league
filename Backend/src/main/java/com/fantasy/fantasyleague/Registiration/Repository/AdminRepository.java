package com.fantasy.fantasyleague.Registiration.Repository;

import com.fantasy.fantasyleague.Registiration.Model.Admin;
import com.fantasy.fantasyleague.Registiration.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByEmail(String email);
    Admin findByUserName(String userName);
}
