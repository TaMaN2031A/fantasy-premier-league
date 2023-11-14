package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
// Needs refactoring, what if delete is not successful + how to determine it's not
public class FAQServiceImpl implements FAQService{
    String deleteResponse = "Deleted Successfully";
    String insertResponse = "Inserted Successfully";
    String updateResponse = "Updated Successfully";

    @Autowired
    FAQRepository faqRepository;
    @Override
    public String insertFAQ(FAQ faq) {
        faqRepository.save(faq);
        return insertResponse;
    }

    @Override
    public String updateFAQ(FAQ faq) {
        faqRepository.deleteById(faq.getFaqID());
        faqRepository.save(faq);
        return updateResponse;
    }

    @Override
    public String deleteFAQ(FAQ faq) {
        faqRepository.deleteById(faq.getFaqID());
        return deleteResponse;
    }
}
