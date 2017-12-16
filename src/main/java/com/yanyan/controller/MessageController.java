package com.yanyan.controller;

import java.util.Collections;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pusher.rest.Pusher;
import com.yanyan.form.MessageForm;

@Controller
public class MessageController {

	private String connectionId;
	
    @GetMapping("/messages")
    public String messages(Model model) {
        model.addAttribute("messageForm", new MessageForm());

        connectionId = "17e6d750-c0fe-401c-83cf-57d0baf4420e";
//        connectionId = UUID.randomUUID().toString();
        model.addAttribute("messages", connectionId);

        return "messages";
    }

    @PostMapping("/messages")
    public String messagesPost(Model model, @Valid MessageForm messageForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "messages";
        }
        Pusher pusher = new Pusher("415427", "438d7f6db8b92d31fc29", "04c6703adad3c89bf9b1");
        pusher.setCluster("ap1");
        pusher.setEncrypted(true);

        pusher.trigger(connectionId, "my-event", Collections.singletonMap("message", "hello world"));

        return "messages";
    }
}
