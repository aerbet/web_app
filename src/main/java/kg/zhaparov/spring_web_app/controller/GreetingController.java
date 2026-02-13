package kg.zhaparov.spring_web_app.controller;

import kg.zhaparov.spring_web_app.domain.Message;
import kg.zhaparov.spring_web_app.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GreetingController {

    private final MessageRepository repository;

    public GreetingController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String greetingRoot(
            Model model
    ) {
        Iterable<Message> messages = repository.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping()
    public String addMessage(
            @RequestParam String text,
            @RequestParam String tag,
            Model model
    ) {
        Message message = new Message(text, tag);
        repository.save(message);

        Iterable<Message> messages = repository.findAll();
        System.out.println(messages);
        model.addAttribute("messages", messages);
        return "redirect:/";
    }

    @DeleteMapping
    public String deleteMessage() {
        repository.deleteAll();
        return "redirect:/";
    }
}
