package com.fantasy.fantasyleague.FaqRule.Controller;

import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// Needs getters, filters, to be done in next phase
@RestController
@RequestMapping("/rule")
public class FAQController {
    @Autowired
    FAQService faqService;
    @PostMapping("/insert")
    public String insertFAQ(@RequestBody FAQ faq) {
        String response = faqService.insertFAQ(faq);
        System.out.println(response);
        return response;
    }
    @DeleteMapping("/delete")
    public String deleteFAQ(@RequestBody FAQ faq) {
        String response = faqService.deleteFAQ(faq);
        System.out.println(response);
        return response;
    }
    @PostMapping("/update")
    public String updateFAQ(@RequestBody FAQ faq) {
        String response = faqService.updateFAQ(faq);
        System.out.println(response);
        return response;
    }
}
