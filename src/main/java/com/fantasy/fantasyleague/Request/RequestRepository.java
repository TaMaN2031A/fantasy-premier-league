package com.fantasy.fantasyleague.Request;

import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends
        CrudRepository<Request, RequestKey> {

}
