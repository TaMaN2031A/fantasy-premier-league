//package com.fantasy.fantasyleague.FaqRule;
//
//import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
//import com.fantasy.fantasyleague.FaqRule.Service.FAQService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.TestPropertySource;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.Date;
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
//@TestPropertySource("classpath:test_local.properties")
//@SpringBootTest
//public class FaqTest {
//
//    @Autowired
//    private FAQService service;
//
//    @Test
//    void FaqTest1(){
//        FAQ faq = new FAQ("how to play ?" , "just choose your team and you get point according to matches." , new Date());
//        assertEquals("Inserted Successfully" , service.insertFAQ(faq));
//    }
//
//    @Test
//    void FaqTest2(){
//        assertEquals("Deleted Successfully" , service.deleteAllFAQ());
//    }
//
//    @Test
//    void FaqTest3(){
//        FAQ faq = new FAQ("Q1 ?" , "Answer1." , new Date());
//        assertEquals("Inserted Successfully" , service.insertFAQ(faq));
//        assertEquals("Deleted Successfully" , service.deleteFAQ(faq));
//    }
//
//    @Test
//    void FaqTest4(){
//        FAQ faq1 = new FAQ("Q1 ?" , "Answer1." , new Date());
//        assertEquals("Unsuccessful Delete" , service.deleteFAQ(faq1));
//    }
//
//
//
//
//    @Test
//    void FaqTest6(){
//        FAQ faq1 = new FAQ("Q1 ?" , "Answer1." , new Date());
//        assertEquals("Inserted Successfully" , service.insertFAQ(faq1));
//        FAQ faq2 = new FAQ("Q3 ?" , "Answer3." , new Date());
//        assertEquals( "Unsuccessful Update", service.updateFAQ(faq2));
//    }
//
//
//    @Test
//    void FaqTest7(){
//        service.deleteAllFAQ();
//        FAQ faq1 = new FAQ("Q1 ?" , "Answer1." , new Date());
//        service.insertFAQ(faq1);
//        FAQ faq2 = new FAQ("Q2 ?" , "Answer2." , new Date());
//        service.insertFAQ(faq2);
//        FAQ faq3 = new FAQ("Q3 ?" , "Answer3." , new Date());
//        service.insertFAQ(faq3);
//        FAQ faq4 = new FAQ("Q4 ?" , "Answer4." , new Date());
//        service.insertFAQ(faq4);
//        FAQ faq5 = new FAQ("Q5 ?" , "Answer5." , new Date());
//        service.insertFAQ(faq5);
//        FAQ faq6 = new FAQ("Q6 ?" , "Answer6." , new Date());
//        service.insertFAQ(faq6);
//        assertEquals(6 , service.getAllFAQ().size());
//
//    }
//
//}
