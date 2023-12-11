//package com.fantasy.fantasyleague.Registiration.Service;
//
//import com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO;
//import com.fantasy.fantasyleague.Request.Model.Request;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//public interface RequestService {
//    //add request
//    ResponseEntity<String>insertRequest(AdminPromotionDTO adminPromotionDTO);
//    //delete request
//    ResponseEntity<String>DeleteRequest(String UserNameOrEmail);
//
//    ResponseEntity<String>BatchDeleteRequest(List<String>UserNameOrEmail);
//
//    ResponseEntity<String> approveRequest(String UserNameOrEmail);
//
//    Request getRequest(String UserNameOrEmail);
//    //getAll request
//    List<Request>getAllRequests();
//    //get a request by a certain specification
//    List<Request> getRequestsBySpecifications(Specification<Request>requestSpecification);
//    //filter request according to anything
//
//}
