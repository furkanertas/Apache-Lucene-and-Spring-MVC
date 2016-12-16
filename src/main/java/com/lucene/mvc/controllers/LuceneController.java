package com.lucene.mvc.controllers;

import com.lucene.mvc.data.entities.Text;
import com.lucene.mvc.data.services.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Toshiba on 22.7.2016.
 */
@Controller
@RequestMapping("/lucene")
public class LuceneController {
    @Autowired
    private LuceneService luceneService;

    @RequestMapping(method = RequestMethod.GET)
    public String goHome () {
        return "lucene";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public @ResponseBody List<Text> search(@RequestParam("word") String word) {
        return luceneService.search(word);
    }
}