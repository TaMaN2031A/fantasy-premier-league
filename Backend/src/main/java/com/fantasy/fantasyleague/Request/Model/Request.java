package com.fantasy.fantasyleague.Request.Model;

import com.fantasy.fantasyleague.Registiration.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Requests")
public class Request {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private String userName;

    @OneToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    private User Name;

    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User Email;

    private Date date;
//    @PrePersist
//    private void prePersist() {
//        if (Email != null) {
//            this.userName = Email.getUserName();
//        }
//    }
}
