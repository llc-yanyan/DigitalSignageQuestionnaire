package com.yanyan.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pusher.rest.Pusher;
import com.yanyan.form.MessageForm;

@Controller
public class MobileController {

	@GetMapping("/mobile")
    public String messages(Model model) {
        return "mobile";
    }

	@PostMapping("/mobile")
    public String messagesPost(Model model, @Valid MessageForm messageForm, BindingResult bindingResult, HttpServletRequest request) {
        Pusher pusher = new Pusher("415427", "438d7f6db8b92d31fc29", "04c6703adad3c89bf9b1");
        pusher.setCluster("ap1");
        pusher.setEncrypted(true);

        pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));

        return "mobile";
    }

}
