package com.sunraysoft.hr.web.action.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.constant.Constant;
import com.sunraysoft.hr.constant.LogConst;
import com.sunraysoft.hr.domain.dto.MenuDto;
import com.sunraysoft.hr.domain.dto.Node;
import com.sunraysoft.hr.domain.entity.base.BizEntity;
import com.sunraysoft.hr.domain.entity.basic.PmBscLoginInfo;
import com.sunraysoft.hr.domain.entity.manage.PmMngMenu;
import com.sunraysoft.hr.domain.entity.manage.PmMngOper;
import com.sunraysoft.hr.domain.entity.manage.PmMngRoleInfo;
import com.sunraysoft.hr.domain.entity.manage.PmMngRoleOper;
import com.sunraysoft.hr.service.LoginInfoService;
import com.sunraysoft.hr.service.MenuService;
import com.sunraysoft.hr.service.OperService;
import com.sunraysoft.hr.service.RoleService;
import com.sunraysoft.hr.util.BeanUtil;
import com.sunraysoft.hr.util.LogUtils;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.JsonWrapper;
import com.sunraysoft.hr.web.data.dto.OperLogDto;
import com.sunraysoft.hr.web.data.dto.Page;

@Controller 
@Scope("prototype")
@Namespace("/permission/role")
@Results({
	@Result(name="redirectPageQuery", type="redirect", location="pageQuery.action?success=${success}&msg=${toUtf8Encode(msg)}&search.vcRoleName=${toUtf8Encode2(search.vcRoleName)}&pageNum=${pageNum}")
})
public class RoleAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

//	private static final Log log = LogFactory.getLog(RoleAction.class);
	
	private Long roleId;
	private PmMngRoleInfo search;
	private PmMngRoleInfo role;
	private String operIds;
	
	private Map<Long, PmMngMenu> menuMap;
	private Map<Long, PmMngOper> operMap;
	
	@Resource private RoleService roleService;
	@Resource private LoginInfoService loginInfoService;
	@Resource private MenuService menuService;
	@Resource private OperService operService;
	
	@Action(LIST)
	public String list() {
		return LIST;
	}
	
	@Action(ADD)
	//@OperCode(code=OpcodeConstant.OPCODE_060302)
	public String add() {
		return ADD;
	}
	
	@Action(EDIT)
	//@OperCode(code=OpcodeConstant.OPCODE_060303)
	public String edit() throws Exception {
		if(roleId != null) {
			role = roleService.find(roleId);
			if(role == null) {
				setFailMsg( "待修改的角色不存在" );
				return REDIRECT_PAGE_QUERY;
			}
		}
		return EDIT;
	}
	
	@Action(PAGE_QUERY)
	//@OperCode(code=OpcodeConstant.OPCODE_060301)
	public String pageQuery() {
		if(search == null) {
			search = new PmMngRoleInfo();
		}
		
		search.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmMngRoleInfo> pg = new Page<PmMngRoleInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("pid");
		pg.setOrder(Page.DESC);
		page = roleService.findPage(pg, search);
		setPage(page);
		
		return LIST;
	}
	
	@Action(CREATE)
	//@OperCode(code=OpcodeConstant.OPCODE_060302)
	public String create() throws Exception {
		if(!checkRoleNameUnique()) {
			setFailMsg("角色名已存在");
			return ADD;
		}
		
		role.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		
		roleService.saveOrUpdate(role);
		setSuccessMsg( "新建成功" );
		
		//logCreate(role);
		
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action(UPDATE)
	//@OperCode(code=OpcodeConstant.OPCODE_060303)
	public String update() throws Exception {
		if(!checkRoleNameUnique()) {
			setFailMsg("角色名已存在");
			return EDIT;
		}
		
		PmMngRoleInfo dbRole = roleService.find(role.getPid());
		if(dbRole == null) {
			setFailMsg( "待修改的角色不存在" );
		} else {
			String[] properties = new String[] {"vcRoleName", "vcMemo"};
			String logMsg = makeLogUpdate(dbRole, role);
			
			BeanUtil.copyProperties(role, dbRole, properties);
			roleService.saveOrUpdate(dbRole);
			setSuccessMsg( "修改成功" );
			logUpdate(logMsg, dbRole.getPid());
		}
		
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action(DESTORY)
	//@OperCode(code=OpcodeConstant.OPCODE_060304)
	public String destory() throws Exception {
		PmMngRoleInfo dbRole = roleService.find(roleId);
		if(dbRole == null) {
			setFailMsg( "待删除的角色不存在" );
		} else if(roleId < Constant.SEQUENCE_START_WITH) {
			setFailMsg( "选中的记录不允许被删除" );
		} else {
			List<PmBscLoginInfo> staffs = loginInfoService.findLoginInfoByRole( roleId );
			if(staffs == null || staffs.isEmpty()) {
				//dbRole.setVcDeleteFlag(Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_INVALID));
				//roleService.saveOrUpdate(dbRole);
				roleService.remove(dbRole);
				setSuccessMsg( "删除成功" );
				logDestory(dbRole);
				
			} else {
				setFailMsg( "待删除的角色已经被引用" );
			}
		}
		
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action(SHOW)
	//@OperCode(code=OpcodeConstant.OPCODE_060301)
	public String show() throws Exception {
		if(roleId != null) {
			role = roleService.find(roleId);
			if(role == null) {
				setFailMsg( "待查看的角色不存在" );
				return REDIRECT_PAGE_QUERY;
			}
			
			menuMap = getMenuMaps();
			operMap = getOperMaps();
			
			Node<MenuDto> root = createOperTree( role.getPmMngRoleOpers() );
			MenuDto menu = new MenuDto();
			menu.setName("操作");
			root.setData(menu);
			
			StringBuilder sb = new StringBuilder();
			generateHtml(sb, root);
			setJsonText( sb.toString() );
			
			//System.out.println(sb);
		}
		return SHOW;
	}
	
	@Action("ajaxCheckRoleNameUnique")
	public String ajaxCheckRoleNameUnique() {
		boolean result = checkRoleNameUnique();
		JsonWrapper wrapper = new JsonWrapper();
		
		wrapper.setFlag(result);
		wrapper.setMsg(result ? "角色名可用" : "角色名已存在");
		
		JSONObject jsonObj = JSONObject.fromObject(wrapper);
		setJsonText( jsonObj.toString() );
		
		return JSON_PAGE;
	}
	
	@Action("roleOperPage")
	//@OperCode(code=OpcodeConstant.OPCODE_060305)
	public String roleOperPage() throws Exception {
		
		PmMngRoleInfo dbRole = roleService.find(roleId);
		if(dbRole == null) {
			setFailMsg( "待修改的角色不存在" );
			return REDIRECT_PAGE_QUERY;
			
		} else if(roleId < Constant.SEQUENCE_START_WITH) {
			setFailMsg( "选中的记录不允许被编辑" );
			return REDIRECT_PAGE_QUERY;
			
		} else {
			Set<PmMngRoleOper> pairList = dbRole.getPmMngRoleOpers();
			Set<Long> operIdList = new HashSet<Long>();
			for(PmMngRoleOper ror : pairList) {
				operIdList.add(ror.getPmMngOper().getPid());
			}
			
			Node<MenuDto> root = menuService.createMenuTree();
			if(root.getData() == null) {
				MenuDto menu = new MenuDto();
				menu.setName("所有操作");
				menu.setId(0L);
				root.setData(menu);
			}
			
			Map<Long, Node<MenuDto>> nodeMap = menuService.createMenuMap(root);
			List<PmMngOper> opers = operService.findBy("vcDeleteFlag", BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL);
			Set<Node<MenuDto>> nodeSet = new HashSet<Node<MenuDto>>();
			
			for(PmMngOper oper : opers) {
				Node<MenuDto> node = nodeMap.get(oper.getPmMngMenu().getPid());
				MenuDto operMenu = new MenuDto(oper.getPid(), oper.getVcOperName(), null, 5, null);
				operMenu.setChecked(operIdList.contains(oper.getPid()));
				Node<MenuDto> operNode = new Node<MenuDto>(operMenu, node);
				
				nodeSet.add(operNode);
			}
			
			setChecked(nodeSet);
			
			StringBuilder sb = new StringBuilder();
			generateHtml(sb, root, operIdList);
			
			setJsonText(sb.toString());
		}
	
		return "role_oper_edit";
	}
	
	@Action("persistRoleOperPair")
	public String persistRoleOperPair() throws Exception {
		
		JsonWrapper wrapper = new JsonWrapper();
		
		PmMngRoleInfo dbRole = roleService.find(roleId);

		if(dbRole == null) {
			wrapper.setFlag(false);
			wrapper.setMsg("修改的记录不存在");
		} else if(roleId < Constant.SEQUENCE_START_WITH) {
			wrapper.setFlag(false);
			wrapper.setMsg("选中的记录不允许被编辑");
		} else {
			List<Long> operIdList = new ArrayList<Long>();
			String[] array = operIds.split("\\,");
			for(int i = 0; i < array.length; i++) {
				if(array[i]!=null&&!array[i].equals("")){
					operIdList.add(Long.parseLong(array[i]));
				}
			}
			
			roleService.persistRoleOperRelation(dbRole, operIdList);
			//logRoleOperUpdate(dbRole, operIdList);
			//TODO 修改权限日志有问题
			
			wrapper.setFlag(true);
			wrapper.setMsg("操作成功");
		}
		
		JSONObject json = JSONObject.fromObject(wrapper);
		this.setJsonText( json.toString() );
		
		return JSON_PAGE;
	}
	
	private void setChecked(Set<Node<MenuDto>> children) {
		if(children == null || children.isEmpty()) {
			return;
		}
		
		Set<Node<MenuDto>> parentSet = new HashSet<Node<MenuDto>>();
		for(Node<MenuDto> e : children) {
			if(e.getParent() != null) {
				parentSet.add(e.getParent());
				if(e.getData().isChecked()) { 
					e.getParent().getData().setCount( e.getParent().getData().getCount() + 1 );
					if(e.getParent().getData().getCount() > 0) {
						e.getParent().getData().setChecked(true);
					}
				}
			}
		}
		
		setChecked(parentSet);
	}
	
	private void generateHtml(StringBuilder sb, Node<MenuDto> node, Set<Long> checkedList) {
			//boolean checked = node.getData().getType() == 5 && checkedList.contains(node.getData().getId());
			String checkedHtml = node.getData().isChecked() ? "checked" : "";
		
			if(!node.hasChild()) {
				sb.append("<dl><dt><input type='checkbox' level='" + node.getData().getType() + "' value='" + node.getData().getId() + "' " +  checkedHtml + " /> <a style='text-decoration:none;' href='javascript:void(0);'>" + node.getData().getName() + "</a></dt></dl>");
			} else if(node.hasChild()) {
				sb.append("<dl>");
				sb.append("<dd><input type='checkbox' level='" + node.getData().getType() + "' value='" + node.getData().getId() + "' " + checkedHtml + "/> <a style='text-decoration:none;' href='javascript:void(0);'>" + node.getData().getName() + "</a>");
				for(Node<MenuDto> son : node.getChildren()) {
					generateHtml(sb, son, checkedList);
				}
				sb.append("</dd>");
				sb.append("</dl>");
			}
	}
	
	private void generateHtml(StringBuilder sb, Node<MenuDto> node) {
	
		if(!node.hasChild()) {
			sb.append("<dl><dt> <input type='checkbox' checked disabled><a style='text-decoration:none;' href='javascript:void(0);'>" + node.getData().getName() + "</a></dt></dl>");
		} else if(node.hasChild()) {
			sb.append("<dl>");
			sb.append("<dd> <input type='checkbox' checked disabled><a style='text-decoration:none;' href='javascript:void(0);'>" + node.getData().getName() + "</a>");
			for(Node<MenuDto> son : node.getChildren()) {
				generateHtml(sb, son);
			}
			sb.append("</dd>");
			sb.append("</dl>");
		}
	}
	
	private Node<MenuDto> createOperTree(Set<PmMngRoleOper> pairs) {
		Node<MenuDto> root = new Node<MenuDto>(null);
		Node<MenuDto> parent = null;
		Node<MenuDto> node = null;
		MenuDto md = null;
		
		for(PmMngRoleOper e : pairs) {
			Stack<MenuDto> stack = new Stack<MenuDto>();
			PmMngOper oper = operMap.get( e.getPmMngOper().getPid() );
			
			md = new MenuDto();
			md.setMemo( oper.getVcMemo() );
			md.setName( oper.getVcOperName() );
			md.setId( oper.getPid() );
			md.setType(1);
			stack.push(md);
			
			PmMngMenu menu = menuMap.get( oper.getPmMngMenu().getPid() );
			
			while(menu != null) {
				
				md = new MenuDto();
				md.setId( menu.getPid() );
				md.setMemo( menu.getVcMeno() );
				md.setName( menu.getVcMenuName() );
				md.setType(2);
				
				stack.push(md);
				
				if(menu.getParent() == null || menu.getParent().getPid() == 0 || menu.getParent().getPid() == -1) {
					menu = null;
				} else {
					menu = menuMap.get( menu.getParent().getPid() );
				}
			}
			
			parent = root;
			while(!stack.isEmpty()) {
				md =stack.pop();
				if(parent.containsTheChild(md)) {
					node = parent.getTheChild(md);
				} else {
					node = new Node<MenuDto>(md, parent);
				}
				parent = node;
			}
		}
		
		return root;
	}
	
	
	private boolean checkRoleNameUnique() {
		PmMngRoleInfo dbRole = roleService.findByRoleName(role.getVcRoleName());
		boolean result = true;
		
		if(role.getPid() == null) { //insert
			if(dbRole != null) {
				result = false;
			}
		} else { //update
			if(dbRole != null && !dbRole.getPid().equals(role.getPid())) {
				result = false;
			}
		} 
		
		return result;
	}
	
	private void logDestory(PmMngRoleInfo role) {
		StringBuilder sb = new StringBuilder();
		sb.append("删除角色 " + role.getVcRoleName() + ": ");
		sb.append(BizEntity.ID_KEY + ": " + role.getPid());
		
		persistServerLog(LogConst.LOG_TYPE_UPDATE, sb.toString(), role.getPid());
	}
	
	private String makeLogUpdate(PmMngRoleInfo before, PmMngRoleInfo after) {
		String title = "修改角色 " + before.getVcRoleName() + ": ";
		StringBuilder sb = new StringBuilder();
		sb.append(title);
		sb.append(LogUtils.generateLogMsg(before, after));
		if(sb.toString().endsWith(title)) {
			sb.append("没有任何更新");
		}
		
		return sb.toString();
	}
	
	private void logUpdate(String logMsg, Long beOperaterid) {
		persistServerLog(LogConst.LOG_TYPE_UPDATE, logMsg, beOperaterid);
	}
	
	private void logRoleOperUpdate(PmMngRoleInfo role, List<Long> operIdList) {
		OperLogDto dto = new OperLogDto();
		for(PmMngRoleOper e : role.getPmMngRoleOpers()) {
			dto.addBeforeOper(e.getPid());
		}
		for(Long e : operIdList) {
			dto.addBeforeOper(e);
		}
		
		List<PmMngOper> operList = operService.findBy("vcDeleteFlag", BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL);
		Map<Long, PmMngOper> operMap = new HashMap<Long, PmMngOper>();
		for(PmMngOper e : operList) {
			operMap.put(e.getPid(), e);
		}
		
		dto.setOperName(operMap);
		
		String title = "修改角色(" + role.getVcRoleName() + ")的权限: ";
		
		StringBuilder sb = new StringBuilder();
		sb.append(title);
		sb.append(dto.getChangeDesc());
		
		if(sb.toString().endsWith(title)) {
			sb.append("没有任何更新");
		}
		
		persistServerLog(LogConst.LOG_TYPE_UPDATE, sb.toString(), role.getPid());
	} 
	
	private Map<Long, PmMngOper> getOperMaps() {
		List<PmMngOper> list = operService.findBy("vcDeleteFlag", BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL);
		Map<Long, PmMngOper> map = new HashMap<Long, PmMngOper>();
		for(PmMngOper e : list) {
			map.put(e.getPid(), e);
		}
		
		return map;
	}
	
	private Map<Long,PmMngMenu> getMenuMaps() {
		List<PmMngMenu> list = menuService.findBy("vcDeleteFlag", BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL);
		Map<Long, PmMngMenu> map = new HashMap<Long, PmMngMenu>();
		for(PmMngMenu e : list) {
			map.put(e.getPid(), e);
		}
		
		return map;
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

	//////////////////////////set get method
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	
	
	public PmMngRoleInfo getSearch() {
		return search;
	}

	public void setSearch(PmMngRoleInfo search) {
		this.search = search;
	}

	public PmMngRoleInfo getRole() {
		return role;
	}

	public void setRole(PmMngRoleInfo role) {
		this.role = role;
	}

	public String getOperIds() {
		return operIds;
	}

	public void setOperIds(String operIds) {
		this.operIds = operIds;
	}
}
