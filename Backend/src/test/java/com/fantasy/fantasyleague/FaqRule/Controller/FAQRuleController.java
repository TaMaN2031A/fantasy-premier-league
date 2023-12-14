package com.fantasy.fantasyleague.FaqRule.Controller;

import com.fantasy.fantasyleague.FaqRule.DTO.FAQDTO;
import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Model.Response;
import com.fantasy.fantasyleague.FaqRule.Service.FAQServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FAQController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class FAQRuleController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FAQServiceImpl faqService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testInsertFAQ() throws Exception {
        FAQDTO faqDTO = FAQDTO.builder()
                .question("New Question")
                .answer("nice question")
                .build();

        when(faqService.insertFAQ(any(FAQDTO.class))).thenReturn(Response.INSERT_SUCCESS.getMessage());

        mockMvc.perform(MockMvcRequestBuilders.post("/faq/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faqDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(Response.INSERT_SUCCESS.getMessage()));

        verify(faqService, times(1)).insertFAQ(any(FAQDTO.class));
    }

    @Test
    public void testDeleteFAQ() throws Exception {

        when(faqService.deleteFAQ(anyInt())).thenReturn(Response.DELETE_SUCCESS.getMessage());

        mockMvc.perform(MockMvcRequestBuilders.delete("/faq/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Response.DELETE_SUCCESS.getMessage()));

        verify(faqService, times(1)).deleteFAQ(1);
    }

    @Test
    public void testUpdateFAQ() throws Exception {
        FAQ updatedFAQ = FAQ.builder()
                .faqID(1)
                .question("new question")
                .answer("new answer")
                .build();

        when(faqService.updateFAQ(any(FAQ.class))).thenReturn(Response.UPDATE_SUCCESS.getMessage());

        mockMvc.perform(MockMvcRequestBuilders.put("/faq/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFAQ)))
                .andExpect(status().isOk())
                .andExpect(content().string(Response.UPDATE_SUCCESS.getMessage()));

        verify(faqService, times(1)).updateFAQ(any(FAQ.class));
    }

    @Test
    public void testGetAll() throws Exception {
        FAQ faq1 = FAQ.builder()
                .faqID(1)
                .question("new question")
                .answer("new answer")
                .build();

        FAQ faq2 = FAQ.builder()
                .faqID(2)
                .question("new question")
                .answer("new answer")
                .build();

        List<FAQ> allFAQs = Arrays.asList(faq1, faq2);

        when(faqService.getAllFAQ()).thenReturn(allFAQs);

        mockMvc.perform(MockMvcRequestBuilders.get("/faq/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(allFAQs)));

        verify(faqService, times(1)).getAllFAQ();
    }

    @Test
    public void testDeleteAll() throws Exception {
        when(faqService.deleteAllFAQ()).thenReturn(Response.DELETE_SUCCESS.getMessage());

        mockMvc.perform(MockMvcRequestBuilders.delete("/faq/deleteAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Response.DELETE_SUCCESS.getMessage()));

        verify(faqService, times(1)).deleteAllFAQ();
    }

}
