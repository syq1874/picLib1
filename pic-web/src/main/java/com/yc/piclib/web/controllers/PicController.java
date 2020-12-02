package com.yc.piclib.web.controllers;

import com.yc.piclib.domain.PicDomain;
import com.yc.piclib.future.PiclibFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/piclib")
public class PicController {

    private static Logger logger = LoggerFactory.getLogger(PicController.class.getName());

    @Autowired
    private PiclibFuture piclibFuture;

    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        return piclibFuture.findById(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public CompletableFuture<String> findAll(Integer page, Integer pageSize, String description) {
        return piclibFuture.findPage(page, pageSize, description);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody PicDomain picDomain) throws Exception {
        return piclibFuture.create(picDomain);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return piclibFuture.delete(id);
    }


}
