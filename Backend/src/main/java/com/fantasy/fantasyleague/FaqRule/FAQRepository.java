package com.fantasy.fantasyleague.FaqRule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends
        JpaRepository<FAQ, Integer> {

}
