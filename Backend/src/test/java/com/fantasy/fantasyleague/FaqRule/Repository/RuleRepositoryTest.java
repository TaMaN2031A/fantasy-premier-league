package com.fantasy.fantasyleague.FaqRule.Repository;

import com.fantasy.fantasyleague.FaqRule.Model.Rule;
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
public class RuleRepositoryTest {

    @Autowired
    private RuleRepository ruleRepository;

    private Rule rule;

    @BeforeEach
    public void init() {
        this.rule = new Rule();
        this.rule.setRule("Test Rule String");
    }

    @Test
    public void saveRule() {
        ruleRepository.save(rule);
        assertTrue(ruleRepository.existsById(rule.getRuleID()));
    }

    @Test
    public void testSaveAndRetrieveRule() {
        ruleRepository.save(rule);
        Optional<Rule> retrievedRuleOptional = ruleRepository.findById(rule.getRuleID());
        assertTrue(retrievedRuleOptional.isPresent());
        Rule retrievedRule = retrievedRuleOptional.get();
        assertEquals("Test Rule String", retrievedRule.getRule());
    }

    @Test
    public void testExistsById() {
        ruleRepository.save(rule);
        boolean exists = ruleRepository.existsById(rule.getRuleID());
        assertTrue(exists);
    }

    @Test
    public void testReferenceById() {
        ruleRepository.save(rule);
        Rule referenceRule = ruleRepository.getReferenceById(rule.getRuleID());
        assertNotNull(referenceRule);
    }

    @Test
    public void testDeleteById() {
        ruleRepository.save(rule);
        ruleRepository.deleteById(rule.getRuleID());
        Optional<Rule> deletedRule = ruleRepository.findById(rule.getRuleID());
        assertFalse(deletedRule.isPresent());
    }

    @Test
    public void testFindAll() {
        Rule rule2 = new Rule();
        rule2.setRule("Rule 2");

        ruleRepository.save(rule);
        ruleRepository.save(rule2);

        List<Rule> allRules = ruleRepository.findAll();

        assertEquals(2, allRules.size());

        assertTrue(allRules.contains(rule));
        assertTrue(allRules.contains(rule2));
    }

    @Test
    public void testDeleteAll() {
        Rule rule2 = new Rule();
        rule2.setRule("Rule 2");

        ruleRepository.save(rule);
        ruleRepository.save(rule2);

        ruleRepository.deleteAll();

        List<Rule> allRules = ruleRepository.findAll();

        assertTrue(allRules.isEmpty());
    }
}
