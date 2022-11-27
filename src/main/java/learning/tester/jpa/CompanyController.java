package learning.tester.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @GetMapping("company")
    public ResponseEntity<?> companyList() {
        List<CompanyEntity> all = companyRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("company/{companyId}")
    public ResponseEntity<?> company(@PathVariable Long companyId) {
        CompanyEntity companyEntity = companyRepository.findById(companyId).get();
        return new ResponseEntity<>(companyEntity, HttpStatus.OK);
    }

    @GetMapping("company/paging")
    public ResponseEntity<?> companyPaged(@PageableDefault(sort = "companyId", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CompanyDto> companyDtoPage = companyService.findPagedDtoList(pageable);
        return ResponseEntity.ok(companyDtoPage);
    }

    @GetMapping("company/paging/entity")
    public ResponseEntity<?> companyPagedEntity(@PageableDefault(sort = "companyId", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CompanyEntity> companyDtoPage = companyService.findPagedEntityList(pageable);
        return ResponseEntity.ok(companyDtoPage);
    }
}
