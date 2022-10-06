package tn.esprit.spring.wecare.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import lombok.var;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.wecare.entities.ChatRoom;
import tn.esprit.spring.wecare.entities.User;
import tn.esprit.spring.wecare.repositories.ChatRoomRepository;
import tn.esprit.spring.wecare.repositories.UserRepository;

@Slf4j
@Service
public class ChatRoomService {

	@Autowired private ChatRoomRepository chatRoomRepository;
    @Autowired private UserRepository userRepository;
    public String getChatId(String senderId, String recipientId, boolean createIfNotExist) {
    		
    	log.info("sender username " +senderId);
    	User sender = userRepository.findById(Long.valueOf(senderId)).get();
    	log.info("sender : "+sender);
    	
    	log.info("-------------- \n");
    	log.info("recipient username : "+recipientId);
    	User recipient = userRepository.findById(Long.valueOf(recipientId)).get();
    	log.info("recipient : "+recipient);
    	Long idchatRoom = chatRoomRepository.ByUsers(sender, recipient);
    	log.info(" --------- ID CHAT ROOM "+idchatRoom);
    	if (idchatRoom != null) {
    	ChatRoom ch = chatRoomRepository.findById(idchatRoom).orElse(null);
    	return ch.getChatId();
    	}
    
    	if(!createIfNotExist) {
                        return  "";
                    }
    	
    	log.info("RETOUR AVANT CHATID ");
                     var chatId =  senderId+"_"+recipientId;
                     log.info("APRES CHATID "+chatId);
                     
                    ChatRoom chatRoom = new ChatRoom();
                    chatRoom.setChatId(chatId);
                    List<User> users = new ArrayList<>();
                    users.add(recipient);
                    users.add(sender);

                    chatRoom.setUsers(users);
                    log.info("chat room size : " +chatRoom.getUsers().size());
                    chatRoomRepository.save(chatRoom);
                   

                    return chatId;
                
    
    }

}
