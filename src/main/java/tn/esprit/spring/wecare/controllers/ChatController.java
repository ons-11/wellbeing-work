package tn.esprit.spring.wecare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.wecare.entities.ChatMessage;
import tn.esprit.spring.wecare.services.ChatMessageService;
import tn.esprit.spring.wecare.services.ChatRoomService;

@RestController
@Slf4j
@CrossOrigin
public class ChatController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;

    @MessageMapping("/msgs")
    public void processMessages(@Payload ChatMessage chatMessage) {
    	
    	log.info("in chat ");
    	log.info("chat message : "+chatMessage);
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId);
        log.info("CHAT ID IN CONTROLLER"+chatId); 
        // HISTORIQUE CONVERSATION 
        List<ChatMessage> chatmsgs = chatMessageService.findChatMessages(chatMessage.getSenderId(), chatMessage.getRecipientId());
        
        for (int i = 0 ; i<chatmsgs.size() ; i++) {
        	log.info("size : "+chatmsgs.size());
        	ChatMessage ch = chatmsgs.get(i);
        	messagingTemplate.convertAndSendToUser(
                    chatMessage.getRecipientId(),"/queue/messages",
                    ch);
        	messagingTemplate.convertAndSendToUser(
                    chatMessage.getSenderId(),"/queue/messages",
                    ch);
        	log.info("in loop : "+i);
        }
      
    }
    
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId);
       ChatMessage saved = chatMessageService.save(chatMessage);
        
       
        	messagingTemplate.convertAndSendToUser(
                    chatMessage.getRecipientId(),"/queue/message",
                    saved);
        	messagingTemplate.convertAndSendToUser(
                    chatMessage.getSenderId(),"/queue/message",
                    saved);
        	
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }
}
