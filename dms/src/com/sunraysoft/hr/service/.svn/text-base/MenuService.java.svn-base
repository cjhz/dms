package com.sunraysoft.hr.service;

import java.util.List;
import java.util.Map;

import com.sunraysoft.hr.domain.dto.MenuDto;
import com.sunraysoft.hr.domain.entity.manage.PmMngMenu;
import com.sunraysoft.hr.service.base.BaseService;
import com.sunraysoft.hr.domain.dto.Node;

public interface MenuService extends BaseService<PmMngMenu,Long> {

	public List<PmMngMenu> findAll();
	
	public Node<MenuDto> createMenuTree();
	
	public Map<Long, Node<MenuDto>> createMenuMap(Node<MenuDto> root);
}
