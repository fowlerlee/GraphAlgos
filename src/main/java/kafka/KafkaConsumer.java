package kafka;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class KafkaConsumer {

    ConsumerRecord<Integer, String> record = new ConsumerRecord<Integer, String>("chat", 2, 1, 1, "lee");
    Properties properties = new Properties();

}
