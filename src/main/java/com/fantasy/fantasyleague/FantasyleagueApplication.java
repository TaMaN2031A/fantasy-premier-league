package com.fantasy.fantasyleague;

import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Model.Rule;
import com.fantasy.fantasyleague.FaqRule.Repository.FAQRepository;
import com.fantasy.fantasyleague.FaqRule.Repository.RuleRepository;
import com.fantasy.fantasyleague.Request.Request;
import com.fantasy.fantasyleague.Request.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Date;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FantasyleagueApplication {// implements CommandLineRunner {
	@Autowired
	private RuleRepository repository;
	@Autowired
	private FAQRepository repo;
	@Autowired
	private RequestRepository requestRepository;
	private static final Logger logger =
			LoggerFactory.getLogger
					(FantasyleagueApplication.class);
	public static void main(String[] args) {
		SpringApplication.run
				(FantasyleagueApplication.class, args);
		logger.info("Salam Aleikom");
	}
//	@Override
//	public void run(String... args) throws Exception {
//		repository.save(new Rule("Don't cheat", new Date()));
//		repo.save(new FAQ("What is the admin name?", "It's abdallah", new Date()));
//		repository.save(new Rule("Sleep Early", new Date()));
//		repository.save(new Rule("Eat healthy food", new Date()));
//		repository.save(new Rule("Focus on the main target", new Date()));
//		requestRepository.save(new Request("Ahmed", "OOP@gmail.com", new Date(), "lol2023", "Egypt"));
//	}

}
