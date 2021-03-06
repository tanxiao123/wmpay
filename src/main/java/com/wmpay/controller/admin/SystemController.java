package com.wmpay.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.weimai.tools.Wm;
import com.wmpay.bean.*;
import com.wmpay.bean.VO.AdditionAdminAdd;
import com.wmpay.bean.VO.AdminVO;
import com.wmpay.common.AdminTypeEnum;
import com.wmpay.service.WmAdditionAdminService;
import com.wmpay.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weimai.tools.ResponseBean;
import com.wmpay.bean.VO.SavePermissionVO;
import com.wmpay.common.AdminCommon;
import com.wmpay.common.PageTools;
import com.wmpay.service.WmAdminService;
import com.wmpay.service.WmAuthGroupAccessService;
import com.wmpay.service.WmAuthGroupService;
import com.wmpay.template.Insert;
import com.wmpay.template.ResponseEnum;
import com.wmpay.template.Update;
import com.wmpay.util.DataTableResponse;

@Controller("admin")
@RequestMapping("/admin/system")
public class SystemController {

    @Autowired
    WmAdminService wmAdminService;

    @Autowired
    WmAuthGroupService wmAuthGroupService;

    @Autowired
    WmAuthGroupAccessService wmAuthGroupAccessService;

    @Autowired
    WmAdditionAdminService wmAdditionAdminService;


    /**
     * 登陆
     *
     * @param wmAdmin
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public ResponseBean doAdminLogin(@Valid WmAdmin wmAdmin, BindingResult result) {
        if (result.hasErrors()) {
            ResponseBean responseError = new ResponseBean();
            responseError.setStatus(-3);
            responseError.setCusMsg(result.getFieldError().getDefaultMessage());
            responseError.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseError;
        } else {
            return wmAdminService.login(wmAdmin);
        }
    }

    /**
     * 退出登陆操作
     * @param request
     * @return
     */
    @RequestMapping(value = "doLoginOut", method = RequestMethod.GET)
    public String doLoginOut(HttpServletRequest request) {
        request.getSession().setAttribute(AdminCommon.USER_SESSION, null);
        request.getSession().setAttribute(AdminCommon.USER_TYPE, null);
        return "redirect:/admin/index.do";
    }

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping(value = "index")
    public String redirectIndex(HttpServletRequest request, HttpServletResponse response) {
        Integer adminId = ((WmAdmin) (request.getSession().getAttribute(AdminCommon.USER_SESSION))).getWmAdminId();
        String menu = wmAdminService.getMenuHTML(adminId);
        request.setAttribute("menu", menu);
        return "/admin/index";
    }

    // ====================================================权限模块==========================================================//

    /**
     * 跳转权限页面
     *
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = "permission")
    public String redirectAuth(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/permission/index";
    }

    /**
     * 获取权限列表
     *
     * @param pageTools
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "permissionList", method = RequestMethod.POST)
    public DataTableResponse permissionList(@RequestBody @Valid PageTools pageTools, BindingResult result) {
        DataTableResponse response = new DataTableResponse();
        if (result.hasErrors()) {
            response.setStatus(-3);
            response.setCusMsg(result.getFieldError().getDefaultMessage());
            response.setTipMsg(result.getFieldError().getDefaultMessage());
            return response;
        }

        IPage<WmAuthGroup> serviceResponse = wmAuthGroupService.getAuthGroupList(pageTools);
        response.setStatus(ResponseEnum.SUCCESS.status);
        response.setCusMsg(ResponseEnum.SUCCESS.msg);
        response.setTipMsg(ResponseEnum.SUCCESS.msg);
        response.setData(serviceResponse.getRecords());
        response.setRecordsTotal(serviceResponse.getPages());
        response.setRecordsFiltered(serviceResponse.getTotal());

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "delPermission", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseBean delPermission(@RequestParam("wmAuthGroupId") Integer wmAuthGroupId) {
        ResponseBean responseBean = new ResponseBean();
        if (wmAuthGroupId == null || "".equals(wmAuthGroupId)) {
            responseBean.setStatus(ResponseEnum.NOT_PERMISSION.status);
            responseBean.setCusMsg(ResponseEnum.NOT_PERMISSION.msg);
            responseBean.setTipMsg(ResponseEnum.NOT_PERMISSION.msg);
            return responseBean;
        }
        // 查询当前权限是否有绑定
        if (wmAuthGroupAccessService.getAccessByAuthGroupId(wmAuthGroupId) != null) {
            responseBean.setStatus(ResponseEnum.PERMISSION_REPEAT.status);
            responseBean.setCusMsg(ResponseEnum.PERMISSION_REPEAT.msg);
            responseBean.setTipMsg(ResponseEnum.PERMISSION_REPEAT.msg);
            return responseBean;
        }
        if (!wmAuthGroupService.deletePermission(wmAuthGroupId)) {
            responseBean.setStatus(2);
            responseBean.setCusMsg(ResponseEnum.ERROR.msg);
            responseBean.setTipMsg(ResponseEnum.ERROR.msg);
            return responseBean;
        } else {
            responseBean.setStatus(ResponseEnum.SUCCESS.status);
            responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
            responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
        }
        return responseBean;
    }

    /**
     * 跳转编辑权限界面
     *
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "editPermissionView", method = RequestMethod.GET)
    public String editPermissionView(@RequestParam("id") Integer id, HttpServletRequest request,
                                     HttpServletResponse response) {

        WmAuthGroup group = wmAuthGroupService.getAuthGroupById(id);
        if (group != null) {
            String html = wmAuthGroupService.getGroupByAuthGroupId(id);
            request.setAttribute("group", group);
            request.setAttribute("permission", html);
            return "admin/permission/edit";
        } else {
            request.setAttribute("errorMessage", "抱歉，没有找到权限信息");
            return "error-500";
        }
    }

    /**
     * 修改角色组
     *
     * @param permissionVO
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "savePermission", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean savePermission(@RequestBody @Validated({Update.class}) SavePermissionVO permissionVO, BindingResult result) {
        ResponseBean responseBean = new ResponseBean();
        if (result.hasErrors()) {
            responseBean.setStatus(-3);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        if (wmAuthGroupService.savePermission(permissionVO)) {
            responseBean.setStatus(1);
            responseBean.setCusMsg("SUCCESS");
            responseBean.setTipMsg("SUCCESS");
        } else {
            responseBean.setStatus(-2);
            responseBean.setCusMsg("添加角色失败");
            responseBean.setCusMsg("添加角色失败");
        }
        return responseBean;
    }

    /**
     * 跳转添加角色视图
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "addPermissionView", method = RequestMethod.GET)
    public String addPermissionView(HttpServletRequest request, HttpServletResponse response) {
        String html = wmAuthGroupService.getGroupByAuthGroupId(0);
        request.setAttribute("permission", html);
        return "/admin/permission/add";
    }

    /**
     * 新增角色
     *
     * @param savePermissionVO
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addPermission", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean addPermission(@RequestBody @Validated({Insert.class}) SavePermissionVO savePermissionVO, BindingResult result) {
        ResponseBean responseBean = new ResponseBean();
        if (result.hasErrors()) {
            responseBean.setStatus(-3);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        } else {
            if (wmAuthGroupService.addPermission(savePermissionVO)) {
                responseBean.setStatus(1);
                responseBean.setCusMsg("SUCCESS");
                responseBean.setTipMsg("SUCCESS");
            } else {
                responseBean.setStatus(-2);
                responseBean.setCusMsg("添加角色失败");
                responseBean.setCusMsg("添加角色失败");
            }
        }
        return responseBean;
    }

    /**
     * 跳转管理员列表
     *
     * @return
     */
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String adminView() {
        return "/admin/user/index";
    }


    /**
     * 获取管理员列表
     *
     * @param pageTools
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "adminList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public DataTableResponse adminList(@RequestBody @Validated PageTools pageTools, BindingResult result) {
        DataTableResponse responseBean = new DataTableResponse();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        } else {
            IPage<WmAdmin> serviceResponse = wmAdminService.getAdminList(pageTools);
            responseBean.setStatus(ResponseEnum.SUCCESS.status);
            responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
            responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
            responseBean.setData(serviceResponse.getRecords());
            responseBean.setRecordsTotal(serviceResponse.getPages());
            responseBean.setRecordsFiltered(serviceResponse.getTotal());
        }
        return responseBean;
    }

    /**
     * 跳转编辑管理员视图
     *
     * @param wmAdminId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "editAdminView", method = RequestMethod.GET)
    public String editAdminView(@RequestParam("wmAdminId") Integer wmAdminId, HttpServletRequest request, HttpServletResponse response) {
        PageTools page = new PageTools();
        page.setStart(0);
        page.setLength(999);
        WmAdmin wmAdmin = wmAdminService.getAdminInfo(wmAdminId);
        List<WmAuthGroup> authGroupList = wmAuthGroupService.getAuthGroupList();
        request.setAttribute("wmAdmin", wmAdmin);
        request.setAttribute("authGroupList", authGroupList);
        return "/admin/user/edit";
    }

    /**
     * 获取角色列表  不分页
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAuthGroupList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean getAuthGroupList() {
        ResponseBean responseBean = new ResponseBean();
        List<WmAuthGroup> authGroupList = wmAuthGroupService.getAuthGroupList();
        responseBean.setStatus(ResponseEnum.SUCCESS.status);
        responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
        responseBean.setData(authGroupList);
        return responseBean;
    }

    /**
     * 查询管理员所属的角色组信息
     *
     * @param wmAdminId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAdminRule", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean getAdminRule(@RequestParam("wmAdminId") Integer wmAdminId) {
        ResponseBean responseBean = new ResponseBean();
        WmAuthGroupAccess result = wmAuthGroupAccessService.getAccessByAdminId(wmAdminId);
        if (result != null) {
            responseBean.setStatus(ResponseEnum.SUCCESS.status);
            responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
            responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
            responseBean.setData(result);
        } else {
            responseBean.setStatus(ResponseEnum.ERROR.status);
            responseBean.setCusMsg(ResponseEnum.ERROR.msg);
            responseBean.setTipMsg(ResponseEnum.ERROR.msg);
        }
        return responseBean;
    }

    /**
     * 编辑管理员信息
     *
     * @param adminVO
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editAdmin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean editAdmin(@Validated(Update.class) AdminVO adminVO, BindingResult result) {
        ResponseBean responseBean = new ResponseBean();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        if (wmAdminService.editAdminInfo(adminVO)) {
            // 查询角色关联是否存在 如果存在 那么重新赋予管理员角色信息
            WmAuthGroupAccess groupAccessServer = wmAuthGroupAccessService.getAccessByAdminId(adminVO.getWmAdminId());
            if (groupAccessServer != null) {
                if (!groupAccessServer.getWmAuthGroupId().equals(adminVO.getRuleId())) {
                    WmAuthGroupAccess groupAccess = new WmAuthGroupAccess();
                    groupAccess.setWmAuthGroupAccessId(groupAccessServer.getWmAuthGroupAccessId());
                    groupAccess.setWmAdminId(adminVO.getWmAdminId());
                    groupAccess.setWmAuthGroupId(adminVO.getRuleId());
                    wmAuthGroupAccessService.updateGroupIdById(groupAccess);
                }
                responseBean.setStatus(ResponseEnum.SUCCESS.status);
                responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
                responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
                return responseBean;
            }

        }
        responseBean.setStatus(ResponseEnum.ERROR.status);
        responseBean.setCusMsg(ResponseEnum.ERROR.msg);
        responseBean.setTipMsg(ResponseEnum.ERROR.msg);

        return responseBean;
    }


    /**
     * 禁用管理员
     *
     * @param wmAdminId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "forbiddenAdmin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean forbiddenAdmin(@RequestParam("wmAdminId") Integer wmAdminId) {
        ResponseBean responseBean = new ResponseBean();
        if (wmAdminId == null || wmAdminId <= 0) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(ResponseEnum.FIELD_ERROR.msg);
            responseBean.setTipMsg(ResponseEnum.FIELD_ERROR.msg);
            return responseBean;
        }
        if (wmAdminService.forbidden(wmAdminId)) {
            responseBean.setStatus(ResponseEnum.SUCCESS.status);
            responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
            responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
            return responseBean;
        } else {
            responseBean.setStatus(ResponseEnum.ERROR.status);
            responseBean.setCusMsg(ResponseEnum.ERROR.msg);
            responseBean.setTipMsg(ResponseEnum.ERROR.msg);
            return responseBean;
        }
    }

    /**
     * 删除管理员
     *
     * @param wmAdminId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteAdmin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean deleteAdmin(@RequestParam("wmAdminId") Integer wmAdminId) {
        ResponseBean responseBean = new ResponseBean();
        if (wmAdminId != null && wmAdminId > 0) {
            if (wmAdminService.deleteAdmin(wmAdminId)) {
                responseBean.setStatus(ResponseEnum.SUCCESS.status);
                responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
                responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
                return responseBean;
            }
        }
        responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
        responseBean.setCusMsg(ResponseEnum.FIELD_ERROR.msg);
        responseBean.setTipMsg(ResponseEnum.FIELD_ERROR.msg);
        return responseBean;
    }


    /**
     * 跳转添加管理员视图
     *
     * @return
     */
    @RequestMapping(value = "addAdminView", method = RequestMethod.GET)
    public String addAdminView() {
        return "/admin/user/add";
    }

    /**
     * 新增管理员
     *
     * @param adminVO
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addAdmin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean addAdmin(@Validated(Insert.class) AdminVO adminVO, BindingResult result) {
        ResponseBean responseBean = new ResponseBean();
        if (result.hasErrors()) {
            responseBean.setStatus(ResponseEnum.FIELD_ERROR.status);
            responseBean.setCusMsg(result.getFieldError().getDefaultMessage());
            responseBean.setTipMsg(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        int inertResult = wmAdminService.addAdminInfo(adminVO);
        if (inertResult > 0) {
            WmAuthGroupAccess groupAccess = new WmAuthGroupAccess();
            groupAccess.setWmAdminId(inertResult);
            groupAccess.setWmAuthGroupId(adminVO.getRuleId());
            if (wmAuthGroupAccessService.insertGroup(groupAccess)){
                responseBean.setStatus(ResponseEnum.SUCCESS.status);
                responseBean.setCusMsg(ResponseEnum.SUCCESS.msg);
                responseBean.setTipMsg(ResponseEnum.SUCCESS.msg);
                return responseBean;
            }
        }
        responseBean.setStatus(ResponseEnum.ERROR.status);
        responseBean.setCusMsg(ResponseEnum.ERROR.msg);
        responseBean.setTipMsg(ResponseEnum.ERROR.msg);

        return responseBean;
    }

    /**
     * 加载添加代理管理员
     * @param typeId
     * @param request
     * @return
     */
    @RequestMapping(value = "getAdditionAdminView", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getAdditionAdminView(@RequestParam("typeId")String typeId, @RequestParam("userId")Integer userId, HttpServletRequest request) {
        request.setAttribute("typeId", typeId);
        request.setAttribute("userId", userId);
        return "/admin/addition/add";
    }

    /**
     * 添加代理管理员API
     * @param wmAdditionAdmin
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveAdditionAdmin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean saveAdditionAdmin(@Validated AdditionAdminAdd wmAdditionAdmin, BindingResult result) {
        if (result.hasErrors()) {
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, result.getFieldError().getDefaultMessage());
        }
        WmAdditionAdmin adminBean = new WmAdditionAdmin();
        adminBean.setPassword(wmAdditionAdmin.getPassword() );
        adminBean.setIsDefaultPay("0");
        adminBean.setUserId(wmAdditionAdmin.getUserId() );
        adminBean.setUsername(wmAdditionAdmin.getUsername() );
        adminBean.setNickname(wmAdditionAdmin.getNickname() );
        adminBean.setType(wmAdditionAdmin.getType() );
        adminBean.setLogintime(new Date());
        adminBean.setUpdatedTime(new Date() );
        adminBean.setCreatedTime(new Date() );
        adminBean.setSalt(Wm.getRandom(8) );
        adminBean.setStatus("1");
        int response = wmAdditionAdminService.saveAddition(adminBean);
        if (response > 0){
            // 添加用户角色对应
            return wmAdditionAdminService.addWmAdditionGroupAccess(wmAdditionAdmin.getRuleId(),response)
                    ? AppResponse.success() : AppResponse.error(ResponseEnum.ERROR);
        }else{
            return AppResponse.error(ResponseEnum.ERROR);
        }
    }

    /**
     * 加载编辑代理管理员视图
     * @param typeId
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping(value = "editAdditionAdminView")
    public String  editAdditionAdminView(@RequestParam("typeId")String typeId, @RequestParam("userId") Integer userId, HttpServletRequest request) {

        WmAdditionAdmin admin = wmAdditionAdminService.getWmAddition(typeId, userId);
        WmAdditionGroupAccess group =  wmAdditionAdminService.getWmAdditionGroupById(admin.getWmAdditionAdminId() );
        request.setAttribute("groupId", group.getWmAuthGroupId());
        request.setAttribute("admin", admin);
        return "/admin/addition/edit";
    }

    /**
     * 编辑代理管理员
     * @param wmAdditionAdmin
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editAdditionAdmin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseBean editAdditionAdmin(@Validated(Update.class)  WmAdditionAdmin wmAdditionAdmin, @RequestParam("ruleId")Integer ruleId, BindingResult result) {
        if (result.hasErrors()) {
            return AppResponse.error(ResponseEnum.FIELD_ERROR.status, result.getFieldError().getDefaultMessage()) ;
        }
        if (wmAdditionAdminService.updateAdditionById(wmAdditionAdmin) ){
            // 修改当前用户所选角色
            WmAdditionGroupAccess dbResult = wmAdditionAdminService.getWmAdditionGroupById(wmAdditionAdmin.getWmAdditionAdminId() );
            if ( dbResult != null ){
                if (!dbResult.getWmAuthGroupId().equals(ruleId) ){
                    WmAdditionGroupAccess dbAccess = wmAdditionAdminService.getWmAdditionGroupById(wmAdditionAdmin.getWmAdditionAdminId() );
                    WmAdditionGroupAccess groupAccess = new WmAdditionGroupAccess();
                    // TODO: 此处使用DB查询来获取角色ID 并进行赋值
                    groupAccess.setWmAdditionGroupAccessId(dbAccess.getWmAdditionGroupAccessId() );
                    groupAccess.setWmAdditionAdminId(wmAdditionAdmin.getWmAdditionAdminId() );
                    groupAccess.setWmAuthGroupId(ruleId);
                    wmAdditionAdminService.updateWmAdditionGroup(groupAccess);
                }
                return AppResponse.success();
            }else{
                return AppResponse.error(ResponseEnum.ERROR.status, "管理员角色为空");
            }
        }else{
            return AppResponse.error(ResponseEnum.ERROR);
        }
    }

}
