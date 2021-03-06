<%@page import="com.wmpay.common.AdminCommon"%>
<%@page import="com.wmpay.bean.WmAdmin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <header class="Hui-navbar">
      <div class="navbar">
        <div class="container-fluid clearfix">
          <nav id="Hui-topNav" class="nav navbar-nav">
            <ul class="clearfix">
              <li><a data-href="article-add.html" data-title="新增资讯" onclick="Hui_admin_tab(this)" href="javascript:;">新增资讯</a></li>
              <li class="dropDown dropDown_hover">
                <a class="dropDown_A" href="javascript:;">顶部菜单</a>
                <ul class="dropDown-menu menu radius box-shadow">
                  <li class="">
                    <a href="#">二级导航</a>
                  </li>
                  <li class="">
                    <a href="#">二级导航</a>
                  </li>
                </ul>
              </li>
            </ul>
          </nav>
          <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar">
            <ul class="clearfix">
              <li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A"><%= ((WmAdmin)( request.getSession().getAttribute(AdminCommon.USER_SESSION) )).getUsername() %> <i class="Hui-iconfont">&#xe6d5;</i></a>
                <ul class="dropDown-menu menu radius box-shadow">
                  <li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
                  <li><a href="#">切换账户</a></li>
                  <li><a href="#">退出</a></li>
                </ul>
              </li>
              <!-- <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li> -->
            </ul>
          </nav>
        </div>
      </div>
    </header>