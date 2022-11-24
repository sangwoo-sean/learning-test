package learning.tester.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    @Query("select new learning.tester.jpa.CompanyDto(c.companyId, c.companyCode, c.companyName) from CompanyEntity c")
    Page<CompanyDto> findPagedAll(Pageable pageable);
}
