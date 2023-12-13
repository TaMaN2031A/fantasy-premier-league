package com.fantasy.fantasyleague.AdminPromotion;

import com.fantasy.fantasyleague.Registiration.Model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.data.jpa.domain.Specification;

import static com.fantasy.fantasyleague.Registiration.SharedServices.SharedServices.buildSpecification;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class BuildSpecificationTest {

    @Test
    void BuildSpecificationEmailStringExample() {
        // Arrange
        String specificationType = "email";
        String value = "Ahmed@gmail.com";
        Specification<User>specification = buildSpecification(specificationType,value);
        assertNotNull(specification);
    }
    @Test
    void BuildSpecificationUserNameExample() {
        // Arrange
        String specificationType = "userName";
        String value = "Ahmed";
        Specification<User>specification = buildSpecification(specificationType,value);
        assertNotNull(specification);
    }
    @Test
    void BuildSpecificationErrorExample() {
        String specificationType = "kk";
        String value = "kkk";
        Specification<User>specification=buildSpecification(specificationType, value);
        Root<User> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        assertThrows(IllegalArgumentException.class, () -> specification.toPredicate(root, query, criteriaBuilder));
    }
}
