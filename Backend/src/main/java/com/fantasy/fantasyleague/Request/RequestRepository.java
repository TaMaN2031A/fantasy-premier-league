package com.fantasy.fantasyleague.Request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends
        JpaRepository<Request, RequestKey> {

}
