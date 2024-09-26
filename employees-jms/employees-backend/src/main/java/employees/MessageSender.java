package employees;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

@ApplicationScoped
public class MessageSender {

    @Inject
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/EmployeeQueue")
    private Queue queue;

    public void sendMessage(String name) {
        context.createProducer().send(queue, name);
    }

}
