package learning.tester.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RequestMapping("/redis")
@RestController
@RequiredArgsConstructor
public class RedisController {
    private static final String testTopicName = "test";
    private static final ChannelTopic testTopic = new ChannelTopic(testTopicName);

    private final RedisService redisService;

    @PostConstruct
    public void init() {
        redisService.createTopic(testTopic);
    }

    @PostMapping
    public void pushMessage(@RequestBody RedisMessage message) {
        redisService.publish(testTopic, message);
    }
}