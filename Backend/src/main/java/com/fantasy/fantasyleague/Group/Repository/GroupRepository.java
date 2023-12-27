package com.fantasy.fantasyleague.Group.Repository;

import com.fantasy.fantasyleague.Group.Model.GroupFantasy;
import com.fantasy.fantasyleague.Registiration.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends
        JpaRepository<GroupFantasy, Integer> {
    Optional<GroupFantasy> findByName(String name);

    @Query("SELECT g FROM GroupFantasy g " +
            "WHERE g.isPrivate = 0 AND " +
            "g NOT IN (SELECT gf FROM GroupFantasy gf JOIN gf.users u " +
            "WHERE (u.userName = :username OR u.email = :username))")
    List<GroupFantasy> findPublicGroupsNotContainingUser(@Param("username") String userName);

}
