package com.yc.piclib.future;

import com.yc.piclib.domain.PicDomain;
import com.yc.piclib.service.PiclibRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class PiclibFuture {
    @Autowired
    private PiclibRestService piclibRestService;

    @Async
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.findById(id);
        });
    }


    @Async
    public CompletableFuture<String> findPage(Integer page, Integer pageSize,
                                              String description) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.findAll(page, pageSize, description);
        });
    }

    @Async
    public CompletableFuture<String> create(PicDomain picDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.create(picDomain);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return piclibRestService.delete(id);
        });
    }
}