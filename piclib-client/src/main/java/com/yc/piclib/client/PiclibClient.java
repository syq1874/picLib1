package com.yc.piclib.client;

import com.yc.piclib.config.FeignClientConfig;
import com.yc.piclib.domain.PicDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservice-provider",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface PiclibClient {

    @RequestMapping(method = RequestMethod.GET, value = "/piclib/{id}")
    String findById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/piclib/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
                   @RequestParam("description") String description);

    @RequestMapping(method = RequestMethod.POST, value = "/piclib",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody PicDomain picDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/piclib/{id}")
    String delete(@RequestParam("id") Integer id);

}
