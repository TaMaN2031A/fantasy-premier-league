package com.fantasy.fantasyleague.Request.Service;

import com.fantasy.fantasyleague.Request.Model.Request;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImplementation implements RequestService {

    @Override
    public ResponseEntity<String> insertRequest(Request request) {
        return ResponseEntity.ok("tmam");
    }

    @Override
    public ResponseEntity<String> DeleteRequest(String UserNameOrEmail) {
        return null;
    }

    @Override
    public ResponseEntity<String> BatchDeleteRequest(List<String> UserNameOrEmail) {
        return null;
    }

    @Override
    public ResponseEntity<String> approveRequest(String UserNameOrEmail) {
        return null;
    }

    @Override
    public Request getRequest(String UserNameOrEmail) {
        return null;
    }

    @Override
    public List<Request> getAllRequests() {
        return null;
    }

    @Override
    public List<Request> getRequestsBySpecifications(Specification<Request> requestSpecification) {
        return null;
    }

}
