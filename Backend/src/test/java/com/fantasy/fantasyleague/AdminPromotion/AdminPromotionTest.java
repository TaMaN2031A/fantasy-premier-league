//package com.fantasy.fantasyleague.AdminPromotion;
//import com.fantasy.fantasyleague.Request.Model.Request;
//import com.fantasy.fantasyleague.Request.Service.RequestService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.TestPropertySource;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
//@TestPropertySource("classpath:test_local.properties")
//@SpringBootTest
//public class AdminPromotionTest {
//
//
//    @Autowired
//    private RequestService service;
//    private final Request request;
//
//    AdminPromotionTest(){
//        request = new Request();
//    }
//    //smoke Tests
//    @Test
//    void insertRequestSmokeTest(){
//        assertEquals(HttpStatus.OK, service.insertRequest(request).getStatusCode());
//    }
//    @Test
//    void getAllRequestsSmokeTest(){
//        service.getAllRequests();
//    }
//    @Test
//    void getRequestsBySpecificationsSmokeTest(){
//        Specification<Request> st = (root, query, criteriaBuilder) -> null;
//        service.getRequestsBySpecifications(st);
//    }
//    @Test
//    void DeleteRequestSmokeTest(){
//        String UserNameOrEmail="";
//        assertEquals(HttpStatus.OK, service.insertRequest(request).getStatusCode());
//        assertEquals(HttpStatus.OK,service.DeleteRequest(UserNameOrEmail).getStatusCode());
//        assertEquals(HttpStatus.NOT_FOUND,service.DeleteRequest(UserNameOrEmail).getStatusCode());
//    }
//    @Test
//    void BatchDeleteRequestSmokeTest(){
//        List<String> SetOfUserNamesOrEmails =new ArrayList<>();
//        SetOfUserNamesOrEmails.add("ahmed");
//        SetOfUserNamesOrEmails.add("mohamed");
//        assertEquals(HttpStatus.OK, service.insertRequest(request).getStatusCode());
//        assertEquals(HttpStatus.OK,service.BatchDeleteRequest(SetOfUserNamesOrEmails).getStatusCode());
//        assertEquals(HttpStatus.NOT_FOUND,service.BatchDeleteRequest(SetOfUserNamesOrEmails).getStatusCode());
//    }
//    @Test
//    void ApproveRequestSmokeTest(String UserNameOrEmail){
//        service.approveRequest(UserNameOrEmail);
//    }
//    @Test
//    void GetRequestSmokeTest(String UserNameOrEmail){
//        service.getRequest(UserNameOrEmail);
//    }
//
//    @Test
//    void GetAllTest(){
//        Request request1 = new Request();
//        Request request2 = new Request();
//        Request request3 = new Request();
//
//        assertEquals(HttpStatus.OK, service.insertRequest(request1).getStatusCode());
//        assertEquals(HttpStatus.OK, service.insertRequest(request2).getStatusCode());
//        assertEquals(HttpStatus.OK, service.insertRequest(request3).getStatusCode());
//        assertEquals(HttpStatus.CONFLICT,service.insertRequest(request1).getStatusCode());
//        assertEquals(3,service.getAllRequests().size());
//    }
//    @Test
//    void InsertRequestTest(){
//        Request request1 = new Request();
//
//        assertEquals(HttpStatus.OK, service.insertRequest(request1).getStatusCode());
//        assertEquals(HttpStatus.CONFLICT,service.insertRequest(request1).getStatusCode());
////        request2 make it with parameters that are not complete some of them are null or all of them
//        assertEquals(1,service.getAllRequests().size());
//    }
//    @Test
//    void BatchRequestTest(){
//        Request request1 = new Request();
//        Request request2 = new Request();
//        Request request3 = new Request();
//        List<String>req = new ArrayList<>();
//        req.add(request1.getUserName());
//        req.add(request2.getUserName());
//        req.add(request3.getUserName());
//        assertEquals(HttpStatus.OK, service.insertRequest(request1).getStatusCode());
//        assertEquals(HttpStatus.OK, service.insertRequest(request2).getStatusCode());
//        assertEquals(HttpStatus.OK, service.insertRequest(request3).getStatusCode());
////        batch request should return no of successful deletes
//        assertEquals(HttpStatus.OK, service.BatchDeleteRequest(req).getStatusCode());
//        assertEquals(HttpStatus.CONFLICT,service.insertRequest(request1).getStatusCode());
//        assertEquals(3,service.getAllRequests().size());
//    }
//
//    @Test
//    void DeleteRequestTest(){
//        Request request1 = new Request();
//        Request request2 = new Request();
//        Request request3 = new Request();
//
//        assertEquals(HttpStatus.OK, service.insertRequest(request1).getStatusCode());
//        assertEquals(HttpStatus.OK, service.insertRequest(request2).getStatusCode());
//        assertEquals(HttpStatus.OK, service.insertRequest(request3).getStatusCode());
//
//        assertEquals(HttpStatus.OK, service.DeleteRequest(request1.getUserName()).getStatusCode());
//        assertEquals(HttpStatus.NOT_FOUND, service.DeleteRequest(request1.getUserName()).getStatusCode());
//        assertEquals(HttpStatus.OK, service.DeleteRequest(request2.getUserName()).getStatusCode());
//        assertEquals(HttpStatus.OK, service.DeleteRequest(request3.getUserName()).getStatusCode());
//
//        assertEquals(0,service.getAllRequests().size());
//    }
//    @Test
//    void GetRequestByTest(){
//        Request request1 = new Request();
//        Request request2 = new Request();
//        Request request3 = new Request();
//
//
//        Specification<Request> st = (root, query, criteriaBuilder) -> null;
//
//        assertEquals(HttpStatus.OK, service.insertRequest(request1).getStatusCode());
//        assertEquals(HttpStatus.OK, service.insertRequest(request2).getStatusCode());
//        assertEquals(HttpStatus.OK, service.insertRequest(request3).getStatusCode());
//
//        assertEquals(2,service.getRequestsBySpecifications(st).size());
//    }
//    @Test
//    void GetRequestTest(){
//        Request request1 = new Request();
//        assertEquals(HttpStatus.OK, service.insertRequest(request1).getStatusCode());
//        assertEquals(service.getRequest(request1.getUserName()),request1);
//        assertEquals(HttpStatus.OK,service.DeleteRequest(request1.getUserName()).getStatusCode());
////        remove comment and put null
////        assertEquals(null,service.getRequest(request1.getUserName()));
//    }
//
//    @Test
//    void ApproveRequestTest(){
//        Request request1 = new Request();
//        assertEquals(HttpStatus.OK, service.insertRequest(request1).getStatusCode());
//        int no_before_approving = service.getAllRequests().size();
//        assertEquals(HttpStatus.OK,service.approveRequest(request1.getUserName()).getStatusCode());
//        assertEquals(no_before_approving-1,service.getAllRequests().size());
//        assertEquals(request1,service.getRequest(request1.getUserName()));
////        check that it transferred to admin table and removed from user table
//    }
//    //Tests to be added
////inserting a user with an email that is not in the user database
//
////inserting a user with an email that is  in the user database but with an email that is not in
////inserting a user with an email that is  in the user database but with an email that is different to the email of this user
//}