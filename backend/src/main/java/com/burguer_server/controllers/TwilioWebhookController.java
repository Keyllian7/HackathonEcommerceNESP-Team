package com.burguer_server.controllers;


import com.burguer_server.model.MessageModel;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class TwilioWebhookController {

    public static final String RECEIVER_PHONE_NUMBER = System.getenv("RECEIVER_PHONE_NUMBER");
    public static final String SERVER_PHONE_NUMBER = System.getenv("SERVER_PHONE_NUMBER");

    @PostMapping("/sms")
    public String receiveSms(@RequestParam("From") String from, @RequestParam("Body") String body) {
        // Criar um modelo de mensagem
        MessageModel messageModel = new MessageModel(from, body);

        /// Log ou manipulação adicional
        System.out.println("Mensagem recebida de: " + from);
        System.out.println("Conteúdo da mensagem: " + body);

        // Enviar uma mensagem de resposta
        Message responseMessage = Message.creator(
                new PhoneNumber(RECEIVER_PHONE_NUMBER),
                new PhoneNumber(SERVER_PHONE_NUMBER), // Número de telefone da sua Twilio
                "Recebemos sua mensagem: " + body
        ).create();

        System.out.println("Resposta enviada. Message SID: " + responseMessage.getSid());

        // Responder com uma mensagem simples (opcional)
        return "Mensagem recebida!";
    }
}