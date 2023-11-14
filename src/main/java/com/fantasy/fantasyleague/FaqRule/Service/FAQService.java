package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.Model.FAQ;

public interface FAQService {
    String insertFAQ(FAQ faq);
    String updateFAQ(FAQ faq);
    String deleteFAQ(FAQ faq);

}
