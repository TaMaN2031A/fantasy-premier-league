package com.fantasy.fantasyleague.FaqRule.Repository;

import com.fantasy.fantasyleague.FaqRule.Model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends
        JpaRepository<Rule, Integer> {

}
