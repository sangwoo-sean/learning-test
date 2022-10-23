package learning.tester.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository companyRepository;

    @GetMapping("company")
    public ResponseEntity<?> company() {
        List<CompanyEntity> all = companyRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

}
