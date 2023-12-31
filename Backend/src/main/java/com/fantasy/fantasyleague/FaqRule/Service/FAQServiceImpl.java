package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.DTO.FAQDTO;
import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Model.Response;
import com.fantasy.fantasyleague.FaqRule.Repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQServiceImpl implements FAQService{

    final
    FAQRepository faqRepository;

    @Autowired
    public FAQServiceImpl(FAQRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public String insertFAQ(FAQDTO faq) {
        try {
            if(faq == null) {
                throw new IllegalArgumentException("Input parameter is null");
            }

            FAQ newFaq = new FAQ(faq.getQuestion(), faq.getAnswer());
            newFaq.setQuestion(faq.getQuestion());
            newFaq.setAnswer(faq.getAnswer());
            faqRepository.save(newFaq);
            return Response.INSERT_SUCCESS.getMessage();
        } catch(IllegalArgumentException e) {
            return Response.INSERT_FAIL.getMessage();
        }
    }

    @Override
    public String updateFAQ(FAQ faq) {
        if(!faqRepository.existsById(faq.getFaqID()))
            return Response.UPDATE_FAIL.getMessage();
        FAQ faqToUpdate = faqRepository.getReferenceById(faq.getFaqID());
        faqToUpdate.setAnswer(faq.getAnswer());
        faqToUpdate.setQuestion(faq.getQuestion());
        faqRepository.save(faqToUpdate);
        return Response.UPDATE_SUCCESS.getMessage();
    }

    @Override
    public String deleteFAQ(int id) {
        if(!faqRepository.existsById(id))
            return Response.DELETE_FAIL.getMessage();
        faqRepository.deleteById(id);
        return Response.DELETE_SUCCESS.getMessage();
    }

    @Override
    public List<FAQ> getAllFAQ(){
        return faqRepository.findAll();
    }

    @Override
    public String deleteAllFAQ(){
        faqRepository.deleteAll();
        return Response.DELETE_SUCCESS.getMessage();
    }

}
