package com.fantasy.fantasyleague.Registiration.Repository;

import com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO;
import com.fantasy.fantasyleague.Registiration.Model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email OR u.userName = :userName")
    User findByEmailOrUserName(String email, String userName);

    @Query("SELECT new com.fantasy.fantasyleague.Registiration.DTO.AdminPromotionDTO(u.email, u.userName) FROM User u")
    Page<AdminPromotionDTO> findAllUsers(Pageable pageable);

    Page<AdminPromotionDTO> findAll(Specification<User> spec, Pageable pageable);


    private long getTotal(EntityManager entityManager, Specification<User> specification) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);

        cq.select(cb.count(root)).where(getSpecification(root,cq,entityManager,specification));

        return entityManager.createQuery(cq).getSingleResult();
    }

    private Predicate getSpecification(Root<User>root,CriteriaQuery<Long> cq,EntityManager entityManager,Specification<User> specification) {

        if(specification == null) {
            return null;
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        return specification.toPredicate(root, cq, cb);

    }

}
