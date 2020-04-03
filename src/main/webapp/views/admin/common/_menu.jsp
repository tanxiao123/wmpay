<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <aside class="Hui-admin-aside-wrapper">
    <div class="Hui-admin-logo-wrapper">
      <a class="logo navbar-logo" href="/aboutHui.shtml">
        <i class="va-m iconpic global-logo"></i>
        <span class="va-m">威迈智能账单系统</span>
      </a>
    </div>
    <div class="Hui-admin-menu-dropdown bk_2">
      <%=request.getAttribute("menu") %>
    </div>
  </aside>
  <div class="Hui-admin-aside-mask"></div>