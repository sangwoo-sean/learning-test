package learning.tester.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Subscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String msg = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());

            RedisMessage sendMessage = objectMapper.readValue(msg, RedisMessage.class);
            log.info("{}", sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
