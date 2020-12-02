package com.yc.piclib.restapi.controllers;


import com.google.gson.Gson;
import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.PicDomain;
import com.yc.piclib.service.PicService;
import com.yc.piclib.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/piclib")
public class PiclibRestController {
    private static Logger logger = LoggerFactory.getLogger(PiclibRestController.class);

    @Autowired
    private PicService picService;

    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            PicDomain pic = picService.findOne(id);
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", pic);
            //map.put("msg","");
            return new Gson().toJson(map);
        });
    }


    //TODO: 用于测试异步与非异步的区别: test: local call
    @RequestMapping(value = "/test/{id}")
    public String testFindById(@PathVariable Integer id) {
        PicDomain pic = picService.findOne(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("data", pic);
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer pageSize, String description) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                PicDomain picDomain = new PicDomain();

                if (CommonUtils.isNotNull(page)) {
                    picDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    picDomain.setPageSize(pageSize);
                }
                if (CommonUtils.isNotNull(description)) {
                    picDomain.setDescription(description);
                }
                PageDomain<PicDomain> pageDomain = picService.listByPage(picDomain);

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("data", pageDomain);


                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody PicDomain picDomain) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            picService.save(picDomain);
            logger.info("新增->ID=" + picDomain.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", picDomain);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            picService.delete(id);

            logger.info("删除->ID=" + id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            return new Gson().toJson(map);
        });
    }

}
