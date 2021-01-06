package com.sans.consumer.controller;

import com.sans.base.dto.ProviderTestDTO;
import com.sans.base.service.IHelloService;
import com.sans.base.service.IUserService;
import com.sans.base.vo.ResultVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 消费测试接口
 * @Author Sans
 * @CreateTime 2019/11/6 23:09
 */
@RestController
public class ConsumerController {
    // Dubbo远程调用注解
//    @Reference
    @DubboReference(check = false,timeout = 60000)
    private IUserService providerService;
//    @Reference
    @DubboReference(check = false,timeout = 60000)
    private IHelloService helloService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultVO getList(){
        // 远程调用
        List<ProviderTestDTO> providerTestDTOList = providerService.queryList();
        return new ResultVO.Builder<>().code(200).message("success").data(providerTestDTOList).build();
    }
    @RequestMapping(value = "/test",method = RequestMethod.GET)
     public  String  test(){
        return  "99999999999";
     }

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    public  String  sayHello(){
       return helloService.sayHello();
    }
}