package com.fantasy.fantasyleague.FaqRule.Controller;

import com.fantasy.fantasyleague.FaqRule.Model.FAQ;
import com.fantasy.fantasyleague.FaqRule.Service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Needs getters, filters, to be done in next phase
@RestController
@CrossOrigin
@RequestMapping("/faq")
public class FAQController {
    @Autowired
    FAQService faqService;
    @PostMapping("/insert")
    public String insertFAQ(@RequestBody FAQ faq) {
        return faqService.insertFAQ(faq);
    }
    @DeleteMapping("/delete")
    public String deleteFAQ(@RequestBody FAQ faq) {
        return faqService.deleteFAQ(faq);
    }
    @PostMapping("/update")
    public String updateFAQ(@RequestBody FAQ faq) {
        return faqService.updateFAQ(faq);
    }
    @GetMapping("/getAll")
    public List<FAQ> getAllFAQ() {
        return faqService.getAllFAQ();
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllFAQ() {
        return faqService.deleteAllFAQ();
    }
}
