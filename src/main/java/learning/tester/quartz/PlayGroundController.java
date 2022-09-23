package learning.tester.quartz;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/timer")
@RequiredArgsConstructor
public class PlayGroundController {

    private final PlayGroundService service;

    @GetMapping("/run")
    public void runHelloWorldJob() {
        service.runHelloWorldJob();
    }
}
