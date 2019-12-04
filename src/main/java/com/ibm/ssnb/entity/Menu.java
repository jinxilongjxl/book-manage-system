package com.ibm.ssnb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private Integer pId; // 父菜单Id， 根是-1 然后是自己编id

    @Column(length = 50)
    private String name; // 菜单名称

    @Column(length = 200)
    private String url; // 菜单地址

    @Column(length = 10)
    private Integer state; // 菜单节点类型

    @Column(length = 100)
    private String icon; //图标

    @Column(length = 100)
    private String permissions; // 对应的shiro权限，user：add permission也可以是中文

    @Column(length = 10)
    private String type; // 默认0是选项卡内打开， 1 新窗口打开 2 是弹出窗口打开

    @Column(length = 50)
    private String divId; // layui 菜单id


    @NotNull(message = "排序编号不能为空")
    @Column(length = 10)

    private Integer orderNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDivId() {
        return divId;
    }

    public void setDivId(String divId) {
        this.divId = divId;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}
