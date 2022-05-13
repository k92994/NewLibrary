package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){
        return userService.loginCheck(userName,userPwd);
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo login(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword){
        //int i = 1/0;
        //获取用户的id
        int id = LoginUserUtil.releaseUserIdFromCookie(request);

        userService.userUpdate(id,oldPassword,newPassword,confirmPassword);

        return success();
    }
    //打开修改密码页面
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        //int i =1/0;
        return "user/password";
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    /*@RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryUserBYParams(UserQuery query){
        return userService.queryUserBYParams(query);
    }*/

    /**
     * 跳转用户模块
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "/user/user";
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    /*@PostMapping("save")
    @ResponseBody
    public ResultInfo saveUser(User user){
        userService.saveUser(user);
        return success("用户添加成功");
    }*/

    /*@PostMapping("updateUser")
    @ResponseBody
    public ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户修改成功");
    }*/


    //打开添加/修改页面
    @RequestMapping("toUpdateAddPage")
    public String toUpdateAddPage(Integer id,HttpServletRequest request){
        //如果是修改操作，那么需要将数据显示在页面中
        /*if(id!=null){
            User user = userService.selectByPrimaryKey(id);
            AssertUtil.isTrue(null==user,"数据异常请重试");
            request.setAttribute("user",user);
        }*/
        return "user/add_update";
    }


    /**
     * 批量删除
     */
    /*@PostMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteUsers(Integer[] ids){
        userService.deleteUsers(ids);
        return success();
    }*/
}
