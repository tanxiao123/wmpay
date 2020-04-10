package com.wmpay.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wmpay.bean.WmAdmin;
import com.wmpay.bean.WmAuthGroup;
import com.wmpay.bean.WmAuthRule;
import com.wmpay.bean.VO.SavePermissionVO;
import com.wmpay.common.PageTools;
import com.wmpay.dao.WmAuthGroupDAO;
import com.wmpay.dao.WmAuthRuleDAO;

@Service
public class WmAuthGroupService {

	@Autowired
	private WmAuthGroupDAO wmAuthGroupDAO;

	@Autowired
	WmAuthRuleDAO wmAuthRuleDAO;

	public IPage<WmAuthGroup> getAuthGroupList(PageTools pageTools) {
		return wmAuthGroupDAO.selectPageAuthGroup(new Page<WmAuthGroup>(pageTools.getStart(), pageTools.getLength()));
	}

	public List<WmAuthGroup> getAuthGroupList() {
		return wmAuthGroupDAO.selectList(new QueryWrapper<WmAuthGroup>());
	}

	public Boolean savePermission(SavePermissionVO savePermissionVO) {
		StringBuffer groupStr = new StringBuffer();
		if (savePermissionVO.getGroupCheck().length > 0) {
			Arrays.sort(savePermissionVO.getGroupCheck());
			for (int i = 0; i < savePermissionVO.getGroupCheck().length; i++) {
				groupStr.append(savePermissionVO.getGroupCheck()[i]);
				groupStr.append("|");
			}
		}
		String newStr = groupStr.substring(0, groupStr.length() - 1);
		WmAuthGroup wmAuthGroup = new WmAuthGroup();
		wmAuthGroup.setWmAuthGroupId(savePermissionVO.getId());
		wmAuthGroup.setName(savePermissionVO.getRoleName());
		wmAuthGroup.setRules(newStr);
		wmAuthGroup.setUpdatedTime(new Date());

		int result = wmAuthGroupDAO.updateById(wmAuthGroup);
		return result > 0;
	}

	public Boolean addPermission(SavePermissionVO savePermissionVO) {
		StringBuffer groupStr = new StringBuffer();
		if (savePermissionVO.getGroupCheck().length > 0) {
			Arrays.sort(savePermissionVO.getGroupCheck());
			for (int i = 0; i < savePermissionVO.getGroupCheck().length; i++) {
				groupStr.append(savePermissionVO.getGroupCheck()[i]);
				groupStr.append("|");
			}
		}
		Date date = new Date();
		String newStr = groupStr.substring(0, groupStr.length() - 1);
		WmAuthGroup wmAuthGroup = new WmAuthGroup();
		wmAuthGroup.setWmAuthGroupId(savePermissionVO.getId());
		wmAuthGroup.setName(savePermissionVO.getRoleName());
		wmAuthGroup.setRules(newStr);
		wmAuthGroup.setUpdatedTime(date);
		wmAuthGroup.setCreatedTime(date);
		wmAuthGroup.setStatus("1");
		wmAuthGroup.setParentId(0);
		int result = wmAuthGroupDAO.insert(wmAuthGroup);
		return result > 0;
	}

	public WmAuthGroup getAuthGroupById(Integer id) {
		return wmAuthGroupDAO.selectById(id);
	}

	/**
	 * 根据主键ID删除权限
	 * 
	 * @param wmAuthGroupId
	 * @return
	 */
	public Boolean deletePermission(Integer wmAuthGroupId) {
		int result = wmAuthGroupDAO.deleteById(wmAuthGroupId);
		return result > 0;
	}

	/**
	 * 根据角色组查询路由菜单
	 * 
	 * @param wmAuthGroupId
	 * @param type
	 * @return
	 */
	public String getGroupByAuthGroupId(Integer wmAuthGroupId) {
		StringBuffer html = new StringBuffer();
		StringBuffer menuBuffer = new StringBuffer();
		StringBuffer ppMenuStr = new StringBuffer();

		// 查询所有的菜单 并将菜单转为Integer
		String result = wmAuthRuleDAO.getAllMenu();
		String[] rule = result.split(",");
		Integer[] ruleId = new Integer[rule.length];

		for (int i = 0; i < ruleId.length; i++) {
			ruleId[i] = Integer.parseInt(rule[i]);
		}

		// 查询所有父节点的菜单
		List<WmAuthRule> parentMenus = wmAuthRuleDAO.getParentMenus(ruleId);
		// 查询所有子节点的菜单
		List<WmAuthRule> menus = wmAuthRuleDAO.getNotParentMenus(ruleId);

		// 根据子节点菜单查询功能路由 TODO: 只有子节点中可以添加功能路由
		Integer[] ppIds = new Integer[menus.size()];
		for (int i = 0; i < menus.size(); i++) {
			ppIds[i] = menus.get(i).getWmAuthRuleId();
		}

		// 根据子节点IDS查询所有功能路由
		List<WmAuthRule> ppMenus = wmAuthRuleDAO.getFunctionMenusInIds();

		// 获取管理员所拥有的菜单权限
		WmAuthGroup adminGroup = new WmAuthGroup();
		if (wmAuthGroupId > 0) {
			adminGroup = wmAuthGroupDAO.selectById(wmAuthGroupId);
		}
		String adminRule = adminGroup.getRules();
		Set<String> set = new HashSet<String>();
		if (adminRule != null) {
			String[] adminRules = adminRule.split("\\|");
			set = new HashSet<String>(Arrays.asList(adminRules));
		}

		// 遍历父节点菜单
		for (int i = 0; i < parentMenus.size(); i++) {
			String parentMenuCheck = set.contains(String.valueOf(parentMenus.get(i).getWmAuthRuleId())) ? "checked"
					: "";
			html.append("<dl class=\"Hui-admin-permission-list\">");
			html.append("<dt>");
			html.append("<label><input type=\"checkbox\" " + parentMenuCheck + " name=\"groupCheck[]\" data-group="
					+ parentMenus.get(i).getWmAuthRuleId() + " value=" + parentMenus.get(i).getWmAuthRuleId() + "   >"
					+ parentMenus.get(i).getTitle() + "</label>");
			html.append("</dt>");

			// 查询子级菜单
			for (int j = 0; j < menus.size(); j++) {
				if (menus.get(j).getParentId().equals(parentMenus.get(i).getWmAuthRuleId())) {

					String menuCheck = set.contains(String.valueOf(menus.get(j).getWmAuthRuleId())) ? "checked" : "";
					menuBuffer.append("<dl class=\"clearfix Hui-admin-permission-list2\">");
					menuBuffer.append("<dt>");
					menuBuffer.append("<label class=\"\">");
					menuBuffer.append("<input type=\"checkbox\" " + menuCheck + " name=\"groupCheck[]\" data-group="
							+ menus.get(j).getWmAuthRuleId() + " value=" + menus.get(j).getWmAuthRuleId() + "  >"
							+ menus.get(j).getTitle());
					menuBuffer.append("</label>");
					// 查询子级菜单下的功能权限
					for (int k = 0; k < ppMenus.size(); k++) {
						if (ppMenus.get(k).getParentId().equals(menus.get(j).getWmAuthRuleId())) {
							String ppMenuCheck = set.contains(String.valueOf(menus.get(j).getWmAuthRuleId()))
									? "checked"
									: "";
							ppMenuStr.append("<label>");
							ppMenuStr.append("<input type=\"checkbox\" " + ppMenuCheck
									+ " name=\"groupCheck[]\" data-group=" + ppMenus.get(k).getWmAuthRuleId()
									+ " value=" + ppMenus.get(k).getWmAuthRuleId() + "  >" + ppMenus.get(k).getTitle());
							ppMenuStr.append("</label>");
						}
					}
					menuBuffer.append("</dt>");
					if (ppMenuStr.length() > 0) {
						menuBuffer.append("<dd>");
						menuBuffer.append(ppMenuStr);
						menuBuffer.append("</dd>");
						ppMenuStr = new StringBuffer(); // 在此清空标记
					}
					menuBuffer.append("</dl>");
				}

			}
			if (menuBuffer.length() > 0) {
				html.append("<dd>");
				html.append(menuBuffer);
				html.append("</dd>");
				menuBuffer = new StringBuffer(); // 在此清空标记
			}
			html.append("</dl>");
		}
		return html.toString();
	}

}
