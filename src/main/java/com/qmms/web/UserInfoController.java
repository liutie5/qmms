package com.qmms.web;

import com.qmms.entity.SysPermission;
import com.qmms.entity.SysUserInfo;
import com.qmms.sevice.SysUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

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
//    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo(){
        return "/userInfo/userList";
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public Page<SysUserInfo> getUserList(int page, int pageSize,String userName){
        Page p1 = sysUserInfoService.getUserListWithCondition(page,pageSize,userName);
        for(SysUserInfo u:(List<SysUserInfo>)p1.getContent()){
            u.setPermissionList(null);
        }
        return p1;
    }

    @RequestMapping("/toUserAdd")
    public String toUserAdd(){
        return "/userInfo/userAdd";
    }

    @RequestMapping("/userIsExist")
    @ResponseBody
    public Map<String,Boolean> isUserExist(String userName){
        SysUserInfo sysUserInfo = sysUserInfoService.findByUserName(userName);
        Map<String,Boolean> rs = new HashMap<String,Boolean>();
        if(sysUserInfo!=null){
            rs.put("valid",false);
        }else{
            rs.put("valid",true);
        }
        return rs;
    }

    /**
     * 用户添加;
     * @return
     */
    @PostMapping("/userAdd")
//    @RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public Map<String,String> userInfoAdd(SysUserInfo userInfo){
        String userName = userInfo.getUserName();
        SysUserInfo haveUser = sysUserInfoService.findByUserName(userName);
        Map<String,String> data = new HashMap<>();
        if(haveUser != null && haveUser.getUserId() > 0){
            data.put("errorCode","1");
            data.put("msg","用户名已经存在");
            return data;
        }
        sysUserInfoService.addUser(userInfo);
        data.put("success","1");
        return data;
    }

    @RequestMapping("/toUserEdit")
    public String toUserEdit(int userId,Model model){
        SysUserInfo userInfo = sysUserInfoService.findByUserId(userId);
        model.addAttribute("userInfo",userInfo);
        return "/userInfo/userEdit";
    }

    @PostMapping("/userEdit")
    @ResponseBody
    public Map<String,String> userEdit(SysUserInfo userInfo){
        Map<String,String> data = new HashMap<>();
        sysUserInfoService.updateUser(userInfo);
        data.put("success","1");
        return data;
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @ResponseBody
    public Map<String,String> userDel(int userId){
        Map<String,String> data = new HashMap<>();
        sysUserInfoService.delUser(userId);
        data.put("success","1");
        return data;
    }

    /**
     * 权限配置页面
     */
    @RequestMapping("/userPermission")
    public String userPermission(Integer userId,String permission,Model model){
        List<SysUserInfo> userInfoList = sysUserInfoService.getAllUserList();
        model.addAttribute("userInfos",userInfoList);
        Set<String> permissionSet = new HashSet<String>();
        if(userId != null && userId != 0){
            SysUserInfo selectecUser = sysUserInfoService.findByUserId(userId);
            model.addAttribute("selectUser",selectecUser);
            for(SysPermission p:selectecUser.getPermissionList()){
                permissionSet.add(p.getPermission());
            }
        }
        model.addAttribute("permissionSet",permissionSet);
        if(userId == null){
            userId = -1;
        }
        model.addAttribute("userId",userId);
        return "/userInfo/userPermission";
    }


    /**
     * 权限配置页面
     */
    @RequestMapping("/addPermission")
    @ResponseBody
    public Map<String,String> addPermission(String userId,String permission,Model model){
        Map<String,String> data = new HashMap<>();
        if(StringUtils.isNotBlank(userId)){
            sysUserInfoService.addPermission(Integer.parseInt(userId),permission);
            data.put("success","1");
        }else{
            data.put("msg","请选择用户");
        }
        return data;

    }



}