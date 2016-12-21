package com.nmc.si_getcreditscore;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
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
    private final static String EXCHANGE_TO_GET_BANKS = "nmc_getcreditscore_to_getbanks_queue";
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

            LoanRequest loanRequest = gson.fromJson(new String(delivery.getBody()), LoanRequest.class);
            int score = getCreditScoreFromWS(loanRequest.getSsn());
            loanRequest.setCreditScore(score);

            byte[] body = gson.toJson(loanRequest).getBytes();
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            builder.correlationId(delivery.getProperties().getCorrelationId());

            channel.basicPublish("", EXCHANGE_TO_GET_BANKS, builder.build(), body);
        }
    }

    private static int getCreditScoreFromWS(String ssn) {
        CreditScoreService_Service service = new CreditScoreService_Service();
        CreditScoreService port = service.getCreditScoreServicePort();
        return port.creditScore(ssn);
    }
}
