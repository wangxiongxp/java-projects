package com.wx.example.controller;

import com.wx.example.bean.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by wangxiong on 2017/12/20.
 */
@RestController
@RequestMapping(value="/users")
public class UserController {

    //这里为了方便测试，直接将数据存储在map中，实际请从数据库获取.
    private static Map<Long,User> users = Collections.synchronizedMap(new HashMap<>());

    /**
     *返回所有的用户.
     *@return
     */
    @RequestMapping(value="", method=RequestMethod.GET)
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<>(users.values());
        return r;
    }

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    /**
     * post保存用户.
     *@param user
     *@return
     */
    @RequestMapping(value ="",method=RequestMethod.POST)
    public String postUser(User user){
        // 处理"/users/"的POST请求，用来创建User
        //@ModelAttribute User user
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(),user);
        return"success";
    }

    /**
     *使用put 进行更新用户.
     *@param id
     *@param user
     *@return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, User user){
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id,u);
        return"success";
    }

    /**
     *使用delete删除用户.
     *@param id
     *@return
     */
    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        // url中的id可通过@PathVariable绑定到函数的参数中
        users.remove(id);
        return"success";
    }

}
