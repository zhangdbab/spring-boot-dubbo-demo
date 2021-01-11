package com.sans.consumer.controller;

import com.sans.base.dto.User;
import com.sans.base.service.IHelloService;
import com.sans.base.service.IUserService;
import com.sans.base.vo.ResultVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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
    private IUserService userService;
//    @Reference
    @DubboReference(check = false,timeout = 60000)
    private IHelloService helloService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultVO getList(){
        // 远程调用
        List<User> userList = userService.queryList();
        return new ResultVO.Builder<>().code(200).message("success").data(userList).build();
    }



    @RequestMapping(value = "/test",method = RequestMethod.GET)
     public  String  test(){
        return  "99999999999";
     }



    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    public  String  sayHello(){
       return helloService.sayHello();
    }


    @RequestMapping(value = "/list/{id}")
    public User queryByID(@PathVariable String id ) {
        return  userService.queryByID(id);

    }

    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public  String   saveUser(@RequestParam int id, @RequestParam String name,  @RequestParam int number ) {
        User user =new User(id,name,number) ;
        userService.savaUserInfoToMongo(user);
         return  "success";
    }



}