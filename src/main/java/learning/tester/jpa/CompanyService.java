package learning.tester.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Page<CompanyDto> findPagedDtoList(Pageable pageable) {
        Specification<CompanyEntity> spec = ((root, query, criteriaBuilder) -> null);

        spec = spec.and(CompanySpecification.likeCompanyName("Bro")); //작동하지 않음

        return companyRepository.findPagedAll(spec, pageable);
    }

    public Page<CompanyEntity> findPagedEntityList(Pageable pageable) {
        Specification<CompanyEntity> spec = ((root, query, criteriaBuilder) -> null);

        spec = spec.and(CompanySpecification.likeCompanyName("Bro"));

        return companyRepository.findAll(spec, pageable);
    }
}
