package com.fantasy.fantasyleague.Registiration.FaqRule.Service;

import com.fantasy.fantasyleague.Registiration.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.Registiration.FaqRule.Repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Needs refactoring, what if delete is not successful + how to determine it's not
@Service
public class FAQServiceImpl implements FAQService{
    private String deleteResponse = "Deleted Successfully";
    private String insertResponse = "Inserted Successfully";
    private String updateResponse = "Updated Successfully";
    private String deleteResponseF = "Unsuccessful Delete";
    private String insertResponseF = "Unsuccessful Insert";
    private String updateResponseF = "Unsuccessful Update";

    @Autowired
    FAQRepository faqRepository;
    @Override
    public String insertFAQ(FAQ faq) {
        faqRepository.save(faq);
        return insertResponse;
    }

    @Override
    public String updateFAQ(FAQ faq) {
        if(!faqRepository.existsById(faq.getFaqID()))
            return updateResponseF;
        FAQ faqToUpdate = faqRepository.getReferenceById(faq.getFaqID());
        faqToUpdate.setAnswer(faq.getAnswer());
        faqToUpdate.setDate(faq.getDate());
        faqToUpdate.setQuestion(faq.getQuestion());
        faqRepository.save(faqToUpdate);
        return updateResponse;
    }

    @Override
    public String deleteFAQ(FAQ faq) {
        if(!faqRepository.existsById(faq.getFaqID()))
            return deleteResponseF;
        faqRepository.deleteById(faq.getFaqID());
        return deleteResponse;
    }

    @Override
    public List<FAQ> getAllFAQ(){
        return faqRepository.findAll();
    }

    @Override
    public String deleteAllFAQ(){
        faqRepository.deleteAll();
        return deleteResponse;
    }

}
