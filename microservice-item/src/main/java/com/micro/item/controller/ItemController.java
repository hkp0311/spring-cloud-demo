package com.micro.item.controller;

import com.micro.item.config.JdbcConfigBean;
import com.micro.item.entity.Item;
import com.micro.item.service.ItemService;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api("swagger demo")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private JdbcConfigBean jdbcConfigBean;
    /**
     * 对外提供接口服务，查询商品信息
     * @param id
     * @return
     */
    @ApiOperation("查询商品信息")
    @GetMapping(value = "item/{id}")
    public Item queryItemById(@ApiParam("商品id") @PathVariable("id") Long id) {
        return this.itemService.queryItemById(id);
    }
    @ApiOperation("config测试")
    @GetMapping(value = "testConfig")
    public String testConfig(){
        return this.jdbcConfigBean.toString();
    }
}

