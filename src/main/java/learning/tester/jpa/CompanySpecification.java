package learning.tester.jpa;

import org.springframework.data.jpa.domain.Specification;

public class CompanySpecification {

    public static Specification<CompanyEntity> equalsCompanyName(String companyName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("companyName"), companyName);
    }

    public static Specification<CompanyEntity> likeCompanyName(String companyName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("companyName"), "%" + companyName + "%");
    }
}
