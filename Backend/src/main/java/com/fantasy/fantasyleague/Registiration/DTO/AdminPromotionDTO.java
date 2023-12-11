package com.fantasy.fantasyleague.Registiration.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPromotionDTO {
    private String userName;
    private String email;
}
