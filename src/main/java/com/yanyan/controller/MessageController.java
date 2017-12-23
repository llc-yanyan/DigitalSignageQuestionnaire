package com.yanyan.controller;

import java.net.URI;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import com.yanyan.form.MessageForm;

@Controller
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@GetMapping("/messages")
	public String messages(UriComponentsBuilder builder, Model model) {
		model.addAttribute("messageForm", new MessageForm());

		String connectionId = UUID.randomUUID().toString();
		model.addAttribute("messages", connectionId);

		URI location = builder.path("/mobile/" + connectionId).build().toUri();
		logger.info(location.toString());

		model.addAttribute("qrcode", location.toString());

		return "messages";
	}

	@GetMapping("/messages/{connectionId}")
	public String messages(@PathVariable(name = "connectionId", required = true) String connectionId,
			UriComponentsBuilder builder, Model model) {
		model.addAttribute("messageForm", new MessageForm());

		model.addAttribute("messages", connectionId);

		URI location = builder.path("/mobile/" + connectionId).build().toUri();
		logger.info(location.toString());

		try {
			model.addAttribute("qrcode", location.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "messages";
	}
}
