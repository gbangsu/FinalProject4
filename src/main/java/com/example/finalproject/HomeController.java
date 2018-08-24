package com.example.finalproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;




    @Controller
    public class HomeController {

        @Autowired
        MessageRepository messageRepository;

        @RequestMapping("/")
        public String listCourses(Model model) {
            model.addAttribute("messages", messageRepository.findAll());
            return "list";
        }

        @GetMapping("/add")
        public String content(Model model) {
            model.addAttribute("message", new Message());
            return "main";
        }

        @RequestMapping("/index")
        public String index(){
            return "index";
        }


        @PostMapping("/process")
        public String processForm(@Valid Message message, BindingResult result)
        {
            if (result.hasErrors()) {
                return "main";
            }
            messageRepository.save(message);
            return "redirect:/";

        }



        @RequestMapping ("/detail/{id}")
        public String showCourse(@PathVariable("id") long id, Model model) {
            model.addAttribute("message", messageRepository.findById(id).get());
            return "display";
        }
        @RequestMapping("/update/{id}")
        public String updateCourse(@PathVariable("id") long id, Model model){
            model.addAttribute("message", messageRepository.findById(id).get());
            return "main";
        }
        @RequestMapping("/delete/{id}")
        public String delCourse(@PathVariable("id")long id){
            messageRepository.deleteById(id);
            return "redirect:/";
        }




    }


