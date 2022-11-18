package learning.tester.connection;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@Getter
//@Scope(value = "singleton")
//@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionScope
public class ConnectionPojo {
    private String data;

    @PostConstruct
    public void init() {
        data = UUID.randomUUID().toString();
        System.out.println("data = " + data + " created");
    }
}
