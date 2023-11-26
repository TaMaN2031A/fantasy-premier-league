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
public class FantasyleagueApplication {
	public static void main(String[] args) {
		SpringApplication.run
				(FantasyleagueApplication.class, args);
	}

}
