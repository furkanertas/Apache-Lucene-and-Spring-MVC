package com.lucene.mvc.controllers;



import com.lucene.mvc.data.entities.Text;
import com.lucene.mvc.data.services.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/")
public class HelloController {
    @Autowired
    private TextService textService;

    @RequestMapping(method = RequestMethod.GET)
    public String hello (ModelMap model) {
        return "hello";
    }
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public @ResponseBody List<Text> search(@RequestParam("word") String word) {
        return textService.getResult(word);
    }

}