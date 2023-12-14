package com.fantasy.fantasyleague.Registiration.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    private String toEmail;
    private String userName;
    private String subject;
    private String message;
}