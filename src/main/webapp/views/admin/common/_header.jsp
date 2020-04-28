<%@page import="com.wmpay.common.AdminCommon"%>
<%@page import="com.wmpay.bean.WmAdmin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <header class="Hui-navbar">
      <div class="navbar">
        <div class="container-fluid clearfix">
          <nav id="Hui-topNav" class="nav navbar-nav">
            <ul class="clearfix">
            </ul>
          </nav>
          <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar">
            <ul class="clearfix">
              <li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">
                <i class="Hui-iconfont">&#xe6d5;</i>
                <span id="header_user_name">ADMIN</span>
              </a>
                <ul class="dropDown-menu menu radius box-shadow">
                  <li><a href="${pageContext.request.contextPath }/admin/system/doLoginOut.do">退出</a></li>
                </ul>
              </li>
              <!-- <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li> -->
            </ul>
          </nav>
        </div>
      </div>
    </header>

