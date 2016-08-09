package com.sunraysoft.hr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.dao.MenuDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.dto.MenuDto;
import com.sunraysoft.hr.domain.dto.Node;
import com.sunraysoft.hr.domain.entity.manage.PmMngMenu;
import com.sunraysoft.hr.service.MenuService;
import com.sunraysoft.hr.service.base.AbstractService;
@Service("menuService")
public class MenuServiceImpl extends AbstractService<PmMngMenu,Long> implements MenuService {

	@Resource
	MenuDao menuDao;
	
	public List<PmMngMenu> findAll() {
		return menuDao.findAll();
	}

	@Override
	public Node<MenuDto> createMenuTree() {
		List<PmMngMenu> menus = menuDao.loadRoots();
		Node<MenuDto> root = new Node<MenuDto>(null); 
		Map<Long, PmMngMenu> menuMap = getMenuMap();
		if(menus == null || menus.isEmpty())  {
			return root;
		} else {
			
			for(PmMngMenu menu : menus) {
				createTree(root, menu, menuMap);
			}
		}
		
		return root;
	}

	private Map<Long, PmMngMenu> getMenuMap() {
		List<PmMngMenu> list = findAll();
		Map<Long, PmMngMenu> map = new HashMap<Long, PmMngMenu>();
		for(PmMngMenu e : list) {
			map.put(e.getPid(), e);
		}
		
		return map;
	}
	
	private void createTree(Node<MenuDto> parent, PmMngMenu menu, Map<Long, PmMngMenu> menuMap) {
		
		MenuDto m = new MenuDto();
		
		m.setId(menu.getPid());
		m.setName(menu.getVcMenuName());
		m.setMemo(menu.getVcMeno());
		m.setPosition(menu.getVcPosition());
		//m.setType(type); //TODO 设置type

		Node<MenuDto> node = new Node<MenuDto>(m, parent, MenuDto.getMenuPositionComparator());
		List<PmMngMenu> children = getChildren(menuMap, menu.getPid());
		if(children != null && !children.isEmpty()) {
			for(PmMngMenu child : children) {
				createTree(node, child, menuMap);
			}
		}
	}
	
	private List<PmMngMenu> getChildren(Map<Long, PmMngMenu> menuMap, Long parentId) {
		List<PmMngMenu> list = new ArrayList<PmMngMenu>();
		for(PmMngMenu e : menuMap.values()) {
			if(parentId == null || parentId == 0 || parentId == -1) {
				if(e.getParent() == null || e.getParent().getPid() == 0 || e.getParent().getPid() == -1) {
					list.add(e);
				}
			} else {
				if(e.getParent() != null && e.getParent().getPid().equals(parentId)) {
					list.add(e);
				}
			}
		}
		
		return list;
	}

	@Override
	public Map<Long, Node<MenuDto>> createMenuMap(Node<MenuDto> root) {
		Map<Long, Node<MenuDto>> map = new HashMap<Long, Node<MenuDto>>();
		privateCreateMenuMap(root, map);
		return map;
	}
	
	private void privateCreateMenuMap(Node<MenuDto> root, Map<Long, Node<MenuDto>> map) {
		if(root.getData() != null) {
			map.put(root.getData().getId(), root);
		}
		
		if(root.hasChild()) {
			for(Node<MenuDto> node : root.getChildren()) {
				privateCreateMenuMap(node, map);
			}
		}
		
	}

	@Override
	public BaseDao<PmMngMenu, Long> getCurrentDao() {
		return menuDao;
	}
}
