package learning.tester.connection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Scope(value = "singleton")
//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConnectionPojo {
    private String data;

    @PostConstruct
    public void init() {
        data = UUID.randomUUID().toString();
    }
}
