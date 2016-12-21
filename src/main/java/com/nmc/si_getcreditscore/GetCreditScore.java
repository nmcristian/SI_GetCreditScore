package com.nmc.si_getcreditscore;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.bank.credit.web.service.CreditScoreService;
import org.bank.credit.web.service.CreditScoreService_Service;

public class GetCreditScore {

    private final static String QUEUE_FROM_WS_NAME = "nmc_webserver_to_getcreditscore_queue";
    private final static String QUEUE_TO_CREDIT_BUREAU = "nmc_getcreditscore_to_creditbureau_queue";
    private final static String QUEUE_FROM_CREDIT_BUREAU = "nmc_creditbureau_to_getcreditscore_queue";
    private final static String QUEUE_TO_GET_BANKS = "nmc_getcreditscore_to_getbanks_queue";
    private static final Gson gson = new Gson();

    public static void main(String[] argv) throws IOException, TimeoutException, InterruptedException, ClassNotFoundException, Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("datdb.cphbusiness.dk");
        factory.setPort(5672);
        factory.setUsername("student");
        factory.setPassword("cph");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_FROM_WS_NAME, false, false, false, null);

        QueueingConsumer consumer_from_ws = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_FROM_WS_NAME, true, consumer_from_ws);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer_from_ws.nextDelivery();

            System.out.println(" [x] Received from the web app: " + new String(delivery.getBody()));
            LoanRequest lr = gson.fromJson(new String(delivery.getBody()), LoanRequest.class);

//            work some more with the attributes being sent to the Credit Bureau = new CPRDTO, then start listening to QUEUE_FROM_CREDIT_BUREAU for message with correlation id = message id just sent
            int score = getCreditScoreFromWS(lr.getSsn());
            System.out.println(score);
        }
    }

    private static int getCreditScoreFromWS(String ssn) {
        CreditScoreService_Service service = new CreditScoreService_Service();
        CreditScoreService port = service.getCreditScoreServicePort();
        return port.creditScore(ssn);
    }
}
