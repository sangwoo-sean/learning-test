package learning.tester.connection;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionPojo connectionPojo;

    @GetMapping("/connection")
    public ConnectionPojo getConnection() {
        return connectionPojo;
    }
}
