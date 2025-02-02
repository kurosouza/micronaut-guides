package example.micronaut;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.rabbitmq.connect.ChannelInitializer;
import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public class ChannelPoolListener extends ChannelInitializer {

    @Override
    public void initialize(Channel channel) throws IOException {
        channel.exchangeDeclare("micronaut", BuiltinExchangeType.DIRECT, true); // <1>

        channel.queueDeclare("inventory", true, false, false, null); // <2>
        channel.queueBind("inventory", "micronaut", "books.inventory"); // <3>

        channel.queueDeclare("catalogue", true, false, false, null); // <4>
        channel.queueBind("catalogue", "micronaut", "books.catalogue"); // <5>
    }
}
