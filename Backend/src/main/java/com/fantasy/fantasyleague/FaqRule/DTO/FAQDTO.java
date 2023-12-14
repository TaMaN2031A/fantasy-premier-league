package com.fantasy.fantasyleague.FaqRule.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Builder
@Data
public class FAQDTO {
    private String question;
    private String answer;
}
