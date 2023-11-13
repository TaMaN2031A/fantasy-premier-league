package com.fantasy.fantasyleague.Registiration.Repository;

import com.fantasy.fantasyleague.Registiration.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
