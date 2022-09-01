package com.github.xuchengen.mq.listener;

import com.github.xuchengen.consts.RocketMQTopic;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * <p>顺序消费消息监听器
 * <p color="red">
 * 注意：在RocketMQ中两个或更多相同的ConsumerGroup订阅相同的Topic
 * 不同的Tag会造成极其混乱的消费情况，因此我们应该采取不同的ConsumerGroup。
 * <p>作者：徐承恩
 * <p>邮箱：<a href="mailto:xuchengen@gmail.com">xuchengen@gmail.com
 * <p>日期：2022-09-01 16:22
 **/
@Component
public class OrderlyBalanceConsumerListener {

    // 这里因为要实现负载平衡，所以ConsumerGroup、Topic、Tag保持一致。

    @Component
    @RocketMQMessageListener(consumerGroup = "CG-TEST-3",
            topic = RocketMQTopic.USER_TOPIC,
            selectorExpression = RocketMQTopic.ORDERLY_BALANCE_TAG,
            consumeMode = ConsumeMode.ORDERLY)
    public static class OrderlyBalanceConsumerListener1 implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            System.out.println("一号消费者：" + message);
        }
    }

    @Component
    @RocketMQMessageListener(consumerGroup = "CG-TEST-3",
            topic = RocketMQTopic.USER_TOPIC,
            selectorExpression = RocketMQTopic.ORDERLY_BALANCE_TAG,
            consumeMode = ConsumeMode.ORDERLY)
    public static class OrderlyBalanceConsumerListener2 implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            System.out.println("二号消费者：" + message);
        }
    }

}
