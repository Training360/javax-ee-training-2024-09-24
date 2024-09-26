package employees;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:/jms/queue/EmployeeQueue"
        )
})
@Slf4j
public class EmployeesMessageDrivenBean implements MessageListener {

    @Override
    @SneakyThrows
    public void onMessage(Message message) {
        if (message instanceof TextMessage textMessage) {
            log.info("JMS message received: " + textMessage.getText());
        }
    }
}
