package com.fantasy.fantasyleague.Registiration.SharedServices;
import com.fantasy.fantasyleague.Registiration.Model.Person;
import com.fantasy.fantasyleague.Registiration.Model.Role;
import com.fantasy.fantasyleague.Registiration.Model.User;
import com.fantasy.fantasyleague.Registiration.Repository.AdminRepository;
import com.fantasy.fantasyleague.Registiration.Repository.UserRepository;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

public class SharedServices {
    public static Person findEntity(AdminRepository adminRepository, UserRepository userRepository, String email, String userName, Role role) {
        return role == Role.ADMIN ?
                adminRepository.findByEmailOrUserName(email, userName):
                userRepository.findByEmailOrUserName(email, userName);
    }
    public static <T, E> Specification<E> buildSpecification(String specificationType, T value) {
        return (root, query, builder) -> {
            Path<?> attribute;
            // The 'root' represents the root entity of the query (e.g., User entity)
            // It allows access to attributes of the entity in the criteria query
            switch (specificationType) {
                case "email" -> attribute = root.get("email");
                case "userName" -> attribute = root.get("userName");
                // Add more cases for other specifications as needed
                default ->{
                    throw new IllegalArgumentException("Unsupported specification type: " + specificationType);
                }
            }

            // The 'builder' provides a variety of methods for constructing criteria queries
            // The condition is dynamically generated based on the type of the attribute
            if (value instanceof String) {
                Expression<String> expression = attribute.as(String.class);
                return builder.like(expression, "%" + value + "%");
            } else if (value instanceof Number) {
                if (value instanceof Integer) {
                    Expression<Integer> expression = attribute.as(Integer.class);
                    return builder.lessThanOrEqualTo(expression, (Integer) value);
                } else if (value instanceof Double) {
                    Expression<Double> expression = attribute.as(Double.class);
                    return builder.lessThanOrEqualTo(expression, (Double) value);
                } else {
                    throw new IllegalArgumentException("Unsupported value type: " + value.getClass());
                }
            } else {
                throw new IllegalArgumentException("Unsupported value type: " + value.getClass());
            }
        };
    }
    public static User generateUser(String email,
                             String userName,
                             String region,
                             String firstName,
                             String lastName,
                             String password) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRegion(region);
        user.setUserName(userName);
        return user;
    }
}

