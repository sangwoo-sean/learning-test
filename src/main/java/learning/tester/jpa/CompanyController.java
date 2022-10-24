package learning.tester.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CompanyController {

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

}
