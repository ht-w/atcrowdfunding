import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;

import io.hongting.api.RedisRemoteService;
import io.hongting.crowd.util.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hongting
 * @create 2021 10 17 12:53 PM
 */

public class ShortMessageTest {


    @Autowired
    RedisRemoteService redisRemoteService;
    @Test
    public void testSendMessage(){
        NexmoClient client = NexmoClient.builder().apiKey("df1f08c6").apiSecret("qKKpCk4lAbVuGQfx").build();

        TextMessage message = new TextMessage("Vonage APIs",
                "60163675362",
                "A text message sent using the Vonage SMS API"
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
    }


        @Test
        public void test() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                int random = (int) (Math.random() * 10);
                builder.append(random);
            }

            String code = builder.toString();
            System.out.println(code);
        }





}
