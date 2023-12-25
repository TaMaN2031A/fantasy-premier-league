package com.fantasy.fantasyleague.FaqRule.Service;

import com.fantasy.fantasyleague.FaqRule.DTO.FAQDTO;
import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Model.Response;
import com.fantasy.fantasyleague.FaqRule.Repository.FAQRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FAQRuleService {

    @Mock
    private FAQRepository faqRepository;

    @InjectMocks
    private FAQServiceImpl faqService;

    @Test
    public void FAQInsert() throws Exception{
        FAQDTO faqdto = FAQDTO
                .builder()
                .question("How can I get 100 points a week?")
                .answer("Play every day and make good decisions!")
                .build();


        FAQ faq2 = new FAQ("Question 1", "Answer 1");
        when(faqRepository.save(Mockito.any(FAQ.class))).thenReturn(faq2);

        String answer = faqService.insertFAQ(faqdto);

        Assertions.assertEquals(answer, "Inserted Successfully");
    }

    @Test
    public void FAQInserGivenNullAsParamter() throws Exception{
        String answer = faqService.insertFAQ(null);

        Assertions.assertEquals(Response.INSERT_FAIL.getMessage(), answer);
    }

    @Test
    public void UpdateFAQ() throws Exception{

        FAQ faq2 = new FAQ("Question 1", "Answer 1");
        when(faqRepository.existsById(Mockito.anyInt())).thenReturn(true); // Mocking save for any FAQ object
        when(faqRepository.getReferenceById(anyInt())).thenReturn(faq2);
        String answer = faqService.updateFAQ(faq2);

        Assertions.assertEquals(Response.UPDATE_SUCCESS.getMessage(), answer);
    }

    @Test
    public void UpdateFAQFailure() throws Exception{
        FAQ faq2 = new FAQ("Question 1", "Answer 1");
        when(faqRepository.existsById(Mockito.anyInt())).thenReturn(false); // Mocking save for any FAQ object
        String answer = faqService.updateFAQ(faq2);

        Assertions.assertEquals(Response.UPDATE_FAIL.getMessage(), answer);
    }

    @Test
    public void DeleteFAQ() throws Exception{

        when(faqRepository.existsById(Mockito.anyInt())).thenReturn(true); // Mocking save for any FAQ object
        doNothing().when(faqRepository).deleteById(anyInt());

        String answer = faqService.deleteFAQ(anyInt());

        Assertions.assertEquals(Response.DELETE_SUCCESS.getMessage(), answer);
    }

    @Test
    public void DeleteFAQWhenFailure() throws Exception{

        when(faqRepository.existsById(Mockito.anyInt())).thenReturn(false); // Mocking save for any FAQ object

        String answer = faqService.deleteFAQ(anyInt());

        Assertions.assertEquals(Response.DELETE_FAIL.getMessage(), answer);
    }

    @Test
    public void GetAllFAQ() throws Exception{

        List<FAQ> list = new ArrayList<>();
        FAQ faq2 = new FAQ("Question 1", "Answer 1");
        FAQ faq3 = new FAQ("Question 2", "Answer 2");
        list.add(faq2); list.add(faq3);

        when(faqRepository.findAll()).thenReturn(list); // Mocking save for any FAQ object

        List answer = faqService.getAllFAQ();

        Assertions.assertEquals(list, answer);
    }

    @Test
    public void DeleteAll() throws Exception{

        doNothing().when(faqRepository).deleteAll();

        String answer = faqService.deleteAllFAQ();

        Assertions.assertEquals(Response.DELETE_SUCCESS.getMessage(), answer);
    }

}
