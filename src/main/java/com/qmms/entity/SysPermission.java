package com.qmms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SysPermission implements Serializable {
    @Id
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private String name;//名称.

    public SysPermission(){};
    public SysPermission(String permission,String name){
        this.permission = permission;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}