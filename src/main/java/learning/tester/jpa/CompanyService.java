package learning.tester.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Page<CompanyDto> findPagedList(Pageable pageable) {

        return companyRepository.findPagedAll(pageable);
    }
}
