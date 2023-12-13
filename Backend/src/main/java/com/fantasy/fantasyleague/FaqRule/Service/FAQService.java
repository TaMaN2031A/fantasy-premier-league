package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.DTO.FAQDTO;
import com.fantasy.fantasyleague.FaqRule.Model.FAQ;

import java.util.List;

public interface FAQService {
    String insertFAQ(FAQDTO faq);
    String updateFAQ(FAQ faq);
    String deleteFAQ(int id);
    List<FAQ> getAllFAQ();
    String deleteAllFAQ();

    }
