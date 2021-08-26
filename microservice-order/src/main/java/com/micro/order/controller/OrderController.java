package com.micro.order.controller;

import com.micro.order.entity.Order;
import com.micro.order.service.OrderService;
import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2Doc
@Api("订单服务")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("根据orderId查询订单")
    @GetMapping(value = "order/{orderId}")
    public Order queryOrderById(@ApiParam("订单编号") @PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderById(orderId);
    }

    @ApiOperation("根据orderId查询订单接口2")
    @GetMapping(value = "order2/{orderId}")
    public Order queryOrderById2(@ApiParam("订单编号") @PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderByIdx(orderId);
    }
}

