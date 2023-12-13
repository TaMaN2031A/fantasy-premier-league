package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.Model.FAQ;

import java.util.List;

public interface FAQService {
    String insertFAQ(FAQ faq);
    String updateFAQ(FAQ faq);
    String deleteFAQ(FAQ faq);
    List<FAQ> getAllFAQ();
    String deleteAllFAQ();

}
