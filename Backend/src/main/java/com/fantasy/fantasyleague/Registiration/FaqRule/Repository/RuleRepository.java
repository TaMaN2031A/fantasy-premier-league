package com.fantasy.fantasyleague.Registiration.FaqRule.Repository;

import com.fantasy.fantasyleague.Registiration.FaqRule.Model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends
        JpaRepository<Rule, Integer> {

}
