package com.fantasy.fantasyleague.Request.Repository;

import com.fantasy.fantasyleague.Request.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestRepository extends
        JpaRepository<Request, String> {
    Request findByUserName(String UserName);

//         void removeBy(String UserName);
}