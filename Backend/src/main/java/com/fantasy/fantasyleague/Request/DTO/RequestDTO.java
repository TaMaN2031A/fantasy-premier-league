package com.fantasy.fantasyleague.Request.DTO;

import com.fantasy.fantasyleague.Registiration.Model.Admin;
import com.fantasy.fantasyleague.Registiration.Model.Person;
import com.fantasy.fantasyleague.Request.Model.Request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Getter
@Setter
@ToString
public class RequestDTO {
    private String userName;
    private String email;



//    private final RequestService requestService;

//    @Autowired
//    public RequestDTO(RequestService requestService) {
//        this.requestService = requestService;
//    }


//    public Request mapToRequest(){
//        return new Request(new Person(),new Date()
//        );
//    }
//    public Admin mapToAdmin(){
//        return new Admin(this.userName
//                ,this.email,
//                this.region,
//                this.firstName,
//                this.lastName
//                ,"rontonrt"
//        );
//    }
}
//                this.requestService.getPassword(this.userName)
