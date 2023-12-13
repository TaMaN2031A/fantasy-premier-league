package com.fantasy.fantasyleague.Registiration.FaqRule.Service;

import com.fantasy.fantasyleague.Registiration.FaqRule.Model.FAQ;

import java.util.List;

public interface FAQService {
    String insertFAQ(FAQ faq);
    String updateFAQ(FAQ faq);
    String deleteFAQ(FAQ faq);
    List<FAQ> getAllFAQ();
    String deleteAllFAQ();

    }
