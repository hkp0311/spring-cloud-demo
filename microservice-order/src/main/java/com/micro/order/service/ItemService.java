package com.micro.order.service;

import com.micro.order.entity.Item;
import com.micro.order.feign.ItemFeignClient;
import com.micro.order.properties.ItemProperties;
import com.micro.order.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${myspcloud.item.url}")
    private String itemUrl;
    @Autowired
    private OrderProperties orderProperties;

    @Autowired
    private ItemFeignClient itemFeignClient;

    /**
     * 普通请求
     * @param id
     * @return
     */
    public Item queryItemById(Long id) {
        /**
         * 方法：通过固定IP地址调用
         * restTemplate需去掉 @LoadBalanced
         */
        //Item result1 = this.restTemplate.getForObject(orderProperties.getItem().getUrl() + id, Item.class);
        /**
         * 方法：通过该方法走eureka注册中心调用
         * 去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced
         */
        String itemUrl = "http://app-item/item/{id}";
        Item result = restTemplate.getForObject(itemUrl, Item.class, id);
        System.out.println("订单系统调用商品服务,result:" + result);
        System.out.println("===========普通请求 queryItemById-线程名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
    }


    /**
     * 进行容错处理请求
     * fallbackMethod的方法参数个数类型要和原方法一致
     * @param id
     * @return
     */
    //@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    public Item queryItemById2(Long id) {
        Item result = itemFeignClient.queryItemById(id);
        System.out.println("===========HystrixCommand queryItemById-线程名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
        return result;
    }

    /**
     * 请求失败执行的方法
     * 参数与原方法保持一直
     * @param id
     * @return
     */
    public Item queryItemByIdFallbackMethod(Long id) {
        return new Item(id, "查询商品信息出错!", null, null, null);
    }


}

