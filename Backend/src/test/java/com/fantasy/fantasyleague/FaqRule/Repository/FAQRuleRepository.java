package com.fantasy.fantasyleague.FaqRule.Repository;

import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Repository.FAQRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FAQRuleRepository {

    @Autowired
    private FAQRepository faqRepository;

    private FAQ faq;

    @BeforeEach
    public void init() {
        this.faq = new FAQ("Test Question", "Test Answer");
    }

    @Test
    public void saveFAQ() {
        faqRepository.save(faq);
        assertTrue(faqRepository.existsById(faq.getFaqID()));
    }

    @Test
    public void testSaveAndRetrieveFAQ() {

        FAQ savedFAQ = faqRepository.save(faq);
        Optional<FAQ> retrievedFAQOptional = faqRepository.findById(savedFAQ.getFaqID());
        assertTrue(retrievedFAQOptional.isPresent());
        FAQ retrievedFAQ = retrievedFAQOptional.get();
        assertEquals("Test Question", retrievedFAQ.getQuestion());
        assertEquals("Test Answer", retrievedFAQ.getAnswer());
    }

    @Test
    public void testExistsById() {
        FAQ savedFAQ = faqRepository.save(faq);
        boolean exists = faqRepository.existsById(savedFAQ.getFaqID());
        assertTrue(exists);
    }

    @Test
    public void testReferenceById() {
        FAQ savedFAQ = faqRepository.save(faq);
        FAQ referenceFAQ = faqRepository.getReferenceById(savedFAQ.getFaqID());
        assertNotNull(referenceFAQ);
    }

    @Test
    public void testDeleteById() {
        FAQ savedFAQ = faqRepository.save(faq);
        faqRepository.deleteById(savedFAQ.getFaqID());
        Optional<FAQ> deletedFAQ = faqRepository.findById(savedFAQ.getFaqID());
        assertFalse(deletedFAQ.isPresent());
    }

    @Test
    public void testFindAll() {
        FAQ faq2 = new FAQ("Question 1", "Answer 1");

        faqRepository.save(faq);
        faqRepository.save(faq2);

        List<FAQ> allFAQs = faqRepository.findAll();

        assertEquals(2, allFAQs.size());

        assertTrue(allFAQs.contains(faq));
        assertTrue(allFAQs.contains(faq2));
    }

    @Test
    public void testDeleteAll() {
        // Create some FAQ entities
        FAQ faq2 = new  FAQ("Question 2", "Answer 2");


        // Save the FAQ entities using the repository
        faqRepository.save(faq);
        faqRepository.save(faq2);

        // Delete all FAQs from the repository using deleteAll method
        faqRepository.deleteAll();

        // Retrieve all FAQs after deletion
        List<FAQ> allFAQs = faqRepository.findAll();

        // Ensure that the findAll method retrieves no FAQs after deletion
        assertTrue(allFAQs.isEmpty());
    }

}
