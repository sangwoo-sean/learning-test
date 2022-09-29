package learning.tester.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisMessageListenerContainer redisMessageListener;
    private final Publisher redisPublisher;
    private final Subscriber redisSubscriber;

    public void createTopic(ChannelTopic channel) {
        redisMessageListener.addMessageListener(redisSubscriber, channel);
    }

    public void publish(ChannelTopic channel, RedisMessage message) {
        redisPublisher.publish(channel, message);
    }

    public void deleteTopic(ChannelTopic channel) {
        redisMessageListener.removeMessageListener(redisSubscriber, channel);
    }
}
