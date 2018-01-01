package com.yanyan.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pusher.rest.Pusher;
import com.yanyan.form.MessageForm;

/**
 * 
 * @author yanai
 *
 */
@Controller
public class MobileController {

	private String connectionId;
	private static final Logger logger = LoggerFactory.getLogger(MobileController.class);
	private int counter = 0;

	/**
	 * 
	 * @param connectionId
	 * @param model
	 * @return
	 */
	@GetMapping("/mobile/{connectionId}")
    public String messages(@PathVariable(name = "connectionId", required = true) String connectionId, Model model) {
		this.connectionId = connectionId;
		model.addAttribute("messages", connectionId);
        return "mobile";
    }

	/**
	 * 
	 * @param model
	 * @param messageForm
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@PostMapping("/mobile")
    public String messagesPost(Model model, @Valid MessageForm messageForm, BindingResult bindingResult, HttpServletRequest request) {
        Pusher pusher = new Pusher("415427", "438d7f6db8b92d31fc29", "04c6703adad3c89bf9b1");
        pusher.setCluster("ap1");
        pusher.setEncrypted(true);

		model.addAttribute("messages", connectionId);
        pusher.trigger(connectionId, "my-event", Collections.singletonMap("message", messageForm.getText()));

        logger.info("Called getWhiskies." + connectionId);
        logger.info("Called text." + messageForm.getText());

        if((counter % 10) == 0){
            pusher.trigger(connectionId, "mb-event", Collections.singletonMap("text", counter));
            logger.info("Called text. limit10.");
        }
        counter++;

        return "mobile";
    }
}
