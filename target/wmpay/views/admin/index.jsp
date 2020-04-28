<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
  <jsp:include page="/views/admin/common/header.jsp">
  		<jsp:param  name="title" value="控制台" />
  </jsp:include>
</head>
<body>
  <!--_menu 作为公共模版分离出去-->
  <jsp:include page="/views/admin/common/_menu.jsp"></jsp:include>
  <!--/_menu 作为公共模版分离出去-->

  <div class="Hui-admin-dislpayArrow">
    <a href="javascript:void(0);" onClick="displaynavbar(this)">
      <i class="Hui-iconfont Hui-iconfont-left">&#xe6d4;</i>
      <i class="Hui-iconfont Hui-iconfont-right">&#xe6d7;</i>
    </a>
  </div>

  <section class="Hui-admin-article-wrapper">
    <!--_header 作为公共模版分离出去-->
    <jsp:include page="/views/admin/common/_header.jsp"></jsp:include>
    <!--/_header 作为公共模版分离出去-->

    <div id="Hui-admin-tabNav" class="Hui-admin-tabNav">
      <div class="Hui-admin-tabNav-wp">
        <ul id="min_title_list" class="acrossTab clearfix" style="width: 241px; left: 0px;">
          <li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
        </ul>
      </div>
      <div class="Hui-admin-tabNav-more btn-group" style="display: none;">
        <a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a>
        <a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
      </div>
    </div>

    <div id="iframe_box" class="Hui-admin-article">
      <div class="show_iframe">
        <iframe id="iframe-welcome" data-scrolltop="0" scrolling="yes" frameborder="0" src="welcome.html"></iframe>
      </div>
    </div>
  </section>
  <div class="contextMenu" id="Huiadminmenu">
    <ul>
      <li id="closethis">关闭当前 </li>
      <li id="closeother">关闭其他 </li>
      <li id="closeall">关闭全部 </li>
    </ul>
  </div>
  
  <jsp:include page="/views/admin/common/footer.jsp"></jsp:include>
  <script>
    $(function () {
      console.log("执行")
      $.post("${pageContext.request.contextPath}/admin/auth/getUserInfo.do", function (result) {
        $("#header_user_name").html(result.data.nickname);
      });
    })
  </script>
</body>
</html>
