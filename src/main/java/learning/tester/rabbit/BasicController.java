package learning.tester.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static learning.tester.rabbit.Constant.topicExchangeName;

@RestController
@RequiredArgsConstructor
public class BasicController {

    private final RabbitTemplate rabbitTemplate;
    private static int count = 1;

    @GetMapping("/")
    public void sendMessage() {
        rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!: " + count);
        count++;
    }
}
