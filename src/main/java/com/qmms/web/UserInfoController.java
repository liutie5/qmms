package com.qmms.web;

import com.qmms.entity.SysUserInfo;
import com.qmms.sevice.SysUserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    private SysUserInfoService sysUserInfoService;


    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo(){
        return "/userInfo/userList";
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public Page<SysUserInfo> getUserList(int page, int pageSize){
        System.out.println("/getUserList");
        Map<String,String> rs = new HashMap<>();
        rs.put("aa","ddd");
        Page p1 = sysUserInfoService.getUserList(page,pageSize);
        return p1;
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }
}