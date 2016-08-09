package com.sunraysoft.hr.web.action.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.refworld.narcotics.base.Security;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.constant.Constant;
import com.sunraysoft.hr.constant.LogConst;
import com.sunraysoft.hr.domain.dto.MenuDto;
import com.sunraysoft.hr.domain.dto.Node;
import com.sunraysoft.hr.domain.dto.UserDto;
import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscLoginInfo;
import com.sunraysoft.hr.domain.entity.manage.PmMngMenu;
import com.sunraysoft.hr.domain.entity.manage.PmMngOper;
import com.sunraysoft.hr.domain.entity.manage.PmMngRoleInfo;
import com.sunraysoft.hr.domain.entity.manage.PmMngRoleOper;
import com.sunraysoft.hr.service.AreainfoService;
import com.sunraysoft.hr.service.LoginInfoService;
import com.sunraysoft.hr.service.MenuService;
import com.sunraysoft.hr.service.OperService;
import com.sunraysoft.hr.service.RoleService;
import com.sunraysoft.hr.service.department.deptinfo.PmBscDeptInfoService;
import com.sunraysoft.hr.web.action.base.BaseAction;
import com.sunraysoft.hr.web.data.dto.JsonWrapper;
import com.sunraysoft.hr.web.data.dto.Page;

@Controller
@Scope("prototype")
@Namespace(value = "/common")
@Results({
	@Result(name="redirectPageQuery", type="redirect", location="pageQuery.action?success=${success}&msg=${toUtf8Encode(msg)}&search.vcName=${toUtf8Encode2(search.vcName)}&pageNum=${pageNum}&search.pmBscAreaInfo.pid=${search.pmBscAreaInfo.pid}&search.pmBscAreaInfo.vcDepartmentCode=${search.pmBscAreaInfo.vcDepartmentCode}")
})
public class LoginAction extends BaseAction {
	
	private static final Log LOG = LogFactory.getLog(LoginAction.class);
	
	private Map<Long, PmMngMenu> menuMap;
	private Map<Long, PmMngOper> operMap;
	
	private Long loginId;
	private PmBscLoginInfo pmbsclogininfo;
	private PmBscLoginInfo search;
	
	@Resource private LoginInfoService loginInfoService;
	@Resource private MenuService menuService;
	@Resource private OperService operService;
	@Resource private RoleService roleService;
	@Resource private AreainfoService areaService;
	@Resource private PmBscDeptInfoService deptservice;

	List<PmMngRoleInfo> rolelist;
	List ruleDeptList;//账号查看时 管辖机构
	boolean ifalert=false;//判断是否显示需要维护机构信息
	
	private String openFlag;//用于停用启用标志
	
	@Action("loginPage")
	public String index() {
		return "login";
	}
	
	
	@Action("ajaxLogin")
	public String login(){
		
		if(getAuthCode() == null || !getAuthCode().equals(pmbsclogininfo.getAuthCode())) { //验证码错误
			setJsonText( makeLoginResult(false, null) );
		} else {
			pmbsclogininfo = loginInfoService.findLoginInfo(pmbsclogininfo.getVcLoginName(), Security.encrypt(pmbsclogininfo.getVcLoginPwd()));
			setJsonText( makeLoginResult(true, pmbsclogininfo) );
			if(pmbsclogininfo != null) {
				loadData();
				loadLoginInfo();
				logLogin();
			}
		}
		
		return JSON_PAGE;
	}
	
	@Action("mainPage")
	public String mainPage() {
		setJsonText( createMenuHtml() );
		loadDeptInfos();
		return "main";
	}
	
	@Action("logout")
	public String logout() {
		logLogout();
		getHttpServletRequest().getSession(true).invalidate();
		
		//TODO logout
		return "loginPage";
	}
	
	@Action("navigation")
	public String navigation() {
		return "navigation";
	}
	@Action("showuser")
	public String showuser() {
		return "welcome";
	}
	
	@Action(PAGE_QUERY)
	//@OperCode(code=OpcodeConstant.OPCODE_060301)
	public String pageQuery() {
		if(search == null) {
			search = new PmBscLoginInfo();
			PmBscAreaInfo area = areaService.find(Long.parseLong(getUser().getAreaId()));
			search.setPmBscAreaInfo(area);
			search.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		}else{
			PmBscAreaInfo area = areaService.find(search.getPmBscAreaInfo().getPid());
			search.setPmBscAreaInfo(area);
//			search.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		}
		
		Page<PmBscLoginInfo> pg = new Page<PmBscLoginInfo>();
		pg.setCurrentNum(getPageNum());
		pg.setPerPageRecordCount(getPageSize());
		pg.setOrderBy("pid");
		pg.setOrder(Page.DESC);
		page = loginInfoService.findPage(pg, search);
		setPage(page);
		return LIST;
	}
	
	@Action(ADD)
	public String add() {
		rolelist = roleService.findAll();
		return ADD;
	}
	
	@Action(CREATE)
	public String create() throws Exception {
		PmBscAreaInfo area = areaService.findByValues(new String[]{"pid","vcDeleteFlag"},new Object[]{pmbsclogininfo.getPmBscAreaInfo().getPid(),BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL}).get(0);
		pmbsclogininfo.setPmBscAreaInfo(area);
		pmbsclogininfo.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		pmbsclogininfo.setVcLoginPwd(Security.encrypt(pmbsclogininfo.getVcLoginPwd()));
		loginInfoService.saveOrUpdate(pmbsclogininfo);
		setSuccessMsg( "新建成功" );
		//logCreate(role);
		
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action(EDIT)
	public String edit() throws Exception {
		if(loginId != null) {
			pmbsclogininfo = loginInfoService.find(loginId);
			if(pmbsclogininfo == null) {
				setFailMsg( "待修改的账号不存在" );
				return REDIRECT_PAGE_QUERY;
			}
		}
		rolelist = roleService.findAll();
		return EDIT;
	}
	@Action(UPDATE)
	public String update() throws Exception {
		PmBscAreaInfo area = areaService.findByValues(new String[]{"pid","vcDeleteFlag"},new Object[]{pmbsclogininfo.getPmBscAreaInfo().getPid(),BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL}).get(0);
		pmbsclogininfo.setPmBscAreaInfo(area);
		PmBscLoginInfo dblogin = loginInfoService.find(pmbsclogininfo.getPid());
		if(dblogin == null) {
			setFailMsg( "待修改的账号不存在" );
		} else {
			// 修改账号信息页面去掉修改密码框之后导致密码会变空的情况
			//pmbsclogininfo.setVcLoginPwd(Security.encrypt(dblogin.getVcLoginPwd()));
			loginInfoService.merge(pmbsclogininfo);
			setSuccessMsg( "修改成功" );
			//logUpdate(logMsg);
		}
		
		return REDIRECT_PAGE_QUERY;
	}
	
	/**
	 * 查看
	 * @return
	 */
	@Action(SHOW)
	public String show() {
		if(loginId != null) {
			pmbsclogininfo = loginInfoService.find(loginId);
			if(pmbsclogininfo == null) {
				setFailMsg( "待查看记录不存在" );
				return REDIRECT_PAGE_QUERY;
			}
		}
//		String ruleDept = pmbsclogininfo.getVcRuleDept()==null?"":pmbsclogininfo.getVcRuleDept();
//		String[] ruleDepts = ruleDept.split(";");
//		if(ruleDepts!=null&&ruleDepts.length>0){
//			ruleDeptList = new ArrayList<Long>();
//			for(int i=0;i<ruleDepts.length;i++){
//				ruleDeptList.add(ruleDepts[i]);
//			}
//		}
		return SHOW;
	}
	
	@Action(DESTORY)
	public String destory() throws Exception {
		PmBscLoginInfo dblogin = loginInfoService.find(loginId);
		if(dblogin == null) {
			setFailMsg( "待删除的用户不存在" );
		} else if(loginId < Constant.SEQUENCE_START_WITH) {
			setFailMsg( "选中的记录不允许被删除" );
		} else {
			if(getUser().getLoginName()!=null&&dblogin.getVcLoginName()!=null&&getUser().getLoginName().equals(dblogin.getVcLoginName())){
				setFailMsg( "不能删除当前登陆账号！" );
			}else{
				dblogin.setVcDeleteFlag(Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_INVALID));
				loginInfoService.saveOrUpdate(dblogin);
				setSuccessMsg( "删除成功" );
				//logDestory(dbRole);
			}

		}
		
		return REDIRECT_PAGE_QUERY;
	}
	/**
	 * 账号启用停用
	 * @return
	 * @throws Exception
	 */
	@Action("stopOpen")
	public String stopOropen() throws Exception {
		PmBscLoginInfo dblogin = loginInfoService.find(loginId);
		if(dblogin == null) {
			setFailMsg( "待操作的账号不存在" );
		} else {
			if(getUser().getLoginName()!=null&&dblogin.getVcLoginName()!=null&&getUser().getLoginName().equals(dblogin.getVcLoginName())){
				setFailMsg( "不能操作当前登陆账号！" );
			}else{
				if(openFlag.equals(BizConstant.LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL+"")){//启用
					dblogin.setVcUserStatus(Long.valueOf(BizConstant.LOGIN_ACCOUNT_INFO_STATUS_KEY_NORMAL));
					loginInfoService.saveOrUpdate(dblogin);
					setSuccessMsg( "启用成功" );
				}else if(openFlag.equals(BizConstant.LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE+"")){//停用
					dblogin.setVcUserStatus(Long.valueOf(BizConstant.LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE));
					loginInfoService.saveOrUpdate(dblogin);
					setSuccessMsg( "停用成功" );
				}
				//logDestory(dbRole);
			}
			
		}
		
		return REDIRECT_PAGE_QUERY;
	}
	
	@Action("passwordEdit")
	//@OperCode(code=OpcodeConstant.OPCODE_010105)
	public String passwordEdit() {
		if(loginId != null) {
			pmbsclogininfo = loginInfoService.find(loginId);
			if(pmbsclogininfo == null) {
				setFailMsg( "待修改的记录不存在" );
				return REDIRECT_PAGE_QUERY;
			}
		}
		return "password_edit";
	}
	
	@SuppressWarnings("unchecked")
	@Action("ajaxModifyPassword")
	public String ajaxModifyPassword() {
		PmBscLoginInfo pmbsclogininfo1 = loginInfoService.find(pmbsclogininfo.getPid());
		JsonWrapper wrapper = new JsonWrapper();
		if(pmbsclogininfo1 == null) {
			setMsg("用户不存在");
		} else {
			pmbsclogininfo1.setVcLoginPwd(Security.encrypt(pmbsclogininfo.getVcLoginPwd()));
			loginInfoService.saveOrUpdate(pmbsclogininfo1);
			wrapper.setFlag(true);
			wrapper.setMsg("密码修改成功");
		}
		
		JSONObject json = JSONObject.fromObject(wrapper);
		setJsonText( json.toString() );
		return JSON_PAGE;
	}
	
	@SuppressWarnings("unchecked")
	@Action("ajaxCheckLoginNameUnique")
	public String ajaxCheckLoginNameUnique() {
		boolean result ;
		List<PmBscLoginInfo> deptlists = loginInfoService.findByValues(new String[]{"vcLoginName","vcDeleteFlag"}, new Object[]{pmbsclogininfo.getVcLoginName(), BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL});
		if(deptlists!=null && deptlists.size()>0){
			if(pmbsclogininfo.getPid() == null){
				result = false ;
			}else{
				boolean a = deptlists.get(0).getPid().equals(pmbsclogininfo.getPid());
				result = a;
			}
		}else{result = true;}
		JsonWrapper wrapper = new JsonWrapper();
		wrapper.setFlag(result);
		wrapper.setMsg(result ? "" : "账号不能重复");
		JSONObject jsonObj = JSONObject.fromObject(wrapper);
		setJsonText( jsonObj.toString() );
		return JSON_PAGE;
	}
	
	private void logLogin() {
		persistServerLog(LogConst.LOG_TYPE_LOGIN, getUser().getLoginName() + "登录成功", getUser().getId());
		
	}
	
	private void logLogout() {
		if(getUser() != null) {
			persistServerLog(LogConst.LOG_TYPE_LOGIN, getUser().getLoginName() + "退出成功", getUser().getId());
		}
	}
	
	/**
	 * 查询当前账号是否没有维护机构信息
	 */
	public void loadDeptInfos(){
		PmBscDeptInfo dept = new PmBscDeptInfo();
		PmBscAreaInfo area = areaService.find(Long.parseLong(getUser().getAreaId()));
		area.setVcExtend1("loadDeptInfos");//这里不需要查询下级，所以加了这么一个标志位。在PmBscDeptInfoDaoImpl.setEntityParameter()用到
		dept.setPmBscAreaInfo(area);
		dept.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		Page<PmBscDeptInfo> pg = new Page<PmBscDeptInfo>();
		pg.setWhetherpage(false);
		Page page = deptservice.findPage(pg, dept);
		List<PmBscDeptInfo> deptlist = page.getResultSet();//deptservice.findBy("vcDeleteFlag", BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL);
		if(deptlist==null||deptlist.size()<1){
			ifalert = true;
		}
	}
	/**
	 * 加载菜单信息和操作信息
	 */
	private void loadData() {
		List<PmMngMenu> menus = menuService.findAll();
		menuMap = new HashMap<Long, PmMngMenu>();
		
		for(PmMngMenu e : menus) {
			menuMap.put(e.getPid(), e);
		}
		
		List<PmMngOper> opers = operService.findAll();
		operMap = new HashMap<Long, PmMngOper>();
		
		for(PmMngOper e : opers) {
			operMap.put(e.getPid(), e);
		}
	}
	
	/**load Staff基本信息*/
	private void loadLoginInfo() {
		UserDto user = new UserDto();
		setUser(user);
		
		user.setId( pmbsclogininfo.getPid() );
		user.setLoginName(pmbsclogininfo.getVcLoginName() );
		user.setRealName( pmbsclogininfo.getVcName());
		
		user.setRoleName( pmbsclogininfo.getPmMngRoleInfo().getVcRoleName());
		user.setIp( getHttpServletRequest().getRemoteAddr() );
		user.setUserstatus(pmbsclogininfo.getVcUserStatus());
		user.setAreaName(pmbsclogininfo.getPmBscAreaInfo().getVcAreaName());
		PmBscAreaInfo temp = areaService.find(pmbsclogininfo.getPmBscAreaInfo().getPid());
		
		user.setAreaId(""+temp.getPid());
		user.setAreaCode(""+temp.getVcDepartmentCode());
		user.setDepartmentype(pmbsclogininfo.getPmBscAreaInfo().getVcDepartmentType());
		
		PmMngRoleInfo role = pmbsclogininfo.getPmMngRoleInfo();
		Set<Long> sonMenus = new HashSet<Long>();
		
		if(role != null && role.getPmMngRoleOpers() != null) {
			for(PmMngRoleOper rela : role.getPmMngRoleOpers()) { 
				String operCode = operMap.get(rela.getPmMngOper().getPid()).getVcOperAction();
				user.addOperAction( operCode );
				
				sonMenus.add(rela.getPmMngOper().getPmMngMenu().getPid());
			}
			setStaffMenu( createStaffMenuTree(sonMenus)  ); //根据Staff的Oper来创建Staff自己的菜单
		}
	}
	
	/**创建Staff的菜单*/
	private Node<MenuDto> createStaffMenuTree(Set<Long> sonMenus) {
		Map<String, Node<MenuDto>> allMenuMap = new HashMap<String, Node<MenuDto>>();
		Node<MenuDto> root = new Node<MenuDto>(null);
		Node<MenuDto> parent = null;
		Node<MenuDto> node = null;
		
		setMenuMap(allMenuMap);
		
		for(Long e : sonMenus) {
			PmMngMenu menu = menuMap.get(e);
			Stack<MenuDto> stack = new Stack<MenuDto>();
			while(menu != null) {
				
				MenuDto md = new MenuDto();
				md.setId( menu.getPid() );
				md.setMemo( menu.getVcMeno());
				md.setName( menu.getVcMenuName() );
				md.setUrl( menu.getVcMenuUrl() );
				md.setPosition( menu.getVcPosition() );
				
				stack.push(md);
				
				if(menu.getParent() == null) {
					menu = null;
				} else {
					menu = menuMap.get( menu.getParent().getPid() );
				}
			}
			
			parent = root;
			int type = 2;
			while(!stack.isEmpty()) {
				MenuDto md =stack.pop();
				md.setType(++type);
				if(parent.containsTheChild(md)) {
					node = parent.getTheChild(md);
				} else {
					node = new Node<MenuDto>(md, parent, MenuDto.getMenuPositionComparator());
					allMenuMap.put(md.getMemo(), node);
				}
				parent = node;
			}
		}
		
		setUrl();
		
		return root;
	}
	
	private String createMenuHtml() {
		Node<MenuDto> root = getStaffMenu();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < root.getChildren().size(); i++) {
			Node<MenuDto> node = root.getChildren().get(i);
			//String selectStr = i == 0 ? "class='select'" : ""; 
			String selectStr = "";
			sb.append("<h3 " + selectStr + "><span><img src='" + getContextPath() + "/theme/" + getTheme() + "/images/" + getImageName(node.getData()) + "' alt='" + node.getData().getName() + "' /></span>" + node.getData().getName() + "</h3>");
			sb.append("<div class='list_menu'>");
				for(Node<MenuDto> son : node.getChildren()) {
					createMenuHtml(sb, son);
				}
			sb.append("</div>");
		}
		
		return sb.toString();
	}
	
	private void createMenuHtml(StringBuilder sb, Node<MenuDto> root) {
		MenuDto dto = root.getData();
		if(dto.getType() < 7) {
			if(dto.getUrl() == null) {
				sb.append("<h" + dto.getType() + ">" + dto.getName() + "</h" + dto.getType() + ">");
			} else {
				if(dto.getType() < 7) {
					sb.append("<h" + dto.getType() + "><a target='mainframe' href='" + getContextPath() + dto.getUrl() + "'>" + dto.getName() + "</a></h" + dto.getType() + ">");
				} else {
					sb.append("<div class='shortcut_sub'><a target='mainframe' href='" + getContextPath() + dto.getUrl() + "'>" + dto.getName() + "</a></div>");
				}
			}
			
			for(Node<MenuDto> node : root.getChildren()) {
				createMenuHtml(sb, node);
			}
		}
	}
	
	private String getImageName(MenuDto menu) {
		
		if(Constant.MENU_TEXT_1.equals(menu.getName())) {
			return "trade.png";
		} else if(Constant.MENU_TEXT_2.equals(menu.getName())) {
			return "storage.png";
		} else if(Constant.MENU_TEXT_3.equals(menu.getName())) {
			return "delivery.png";
		} else if(Constant.MENU_TEXT_4.equals(menu.getName())) {
			return "power.png";
		} else if(Constant.MENU_TEXT_5.equals(menu.getName())) {
			return "account.png";
		} else if(Constant.MENU_TEXT_6.equals(menu.getName())) {
			return "control.png";
		} else {
			return "trade.png";
		}
	}
	
	private void setUrl() {
		Map<String, Node<MenuDto>> allMenuMap = getMenuMap();
		for(Node<MenuDto> node : allMenuMap.values()) {
			if(node.isLeaf()) {
				while(node != null && node.getData().getType() > 5 && node.isFirst() 
						&& (node.getParent().getData().getUrl() == null || node.getParent().getData().getUrl().length() == 0)) {
					
					node.getParent().getData().setUrl( node.getData().getUrl() );
					node = node.getParent();
				}
			}
		}
	}
	
	//一些私有方法
	private String makeLoginResult(boolean authCodeResult, PmBscLoginInfo logininfo) {
		StringBuilder sb = null; 
		long status = logininfo==null?0:logininfo.getVcUserStatus()==null?0:logininfo.getVcUserStatus();
		if(!authCodeResult) {
			sb = new StringBuilder();
			sb.append("{\"flag\": false, \"msg\": \"验证码错误\"}");
		} else if(logininfo == null) {
			sb = new StringBuilder();
			sb.append("{\"flag\": false, \"msg\": \"用户名或密码错误\"}");
		} else if(logininfo != null&&status==BizConstant.LOGIN_ACCOUNT_INFO_STATUS_KEY_NOTOPEN) {
			sb = new StringBuilder();
			sb.append("{\"flag\": false, \"msg\": \"账号未开通\"}");
		} else if(logininfo != null&&status==BizConstant.LOGIN_ACCOUNT_INFO_STATUS_KEY_DISABLE) {
			sb = new StringBuilder();
			sb.append("{\"flag\": false, \"msg\": \"账号已停用\"}");
		} else {
			sb = new StringBuilder();
			sb.append("{\"flag\": true, \"msg\": \"登录成功 正在加载数据...\"}");
		}
		
		return sb.toString();
	}

	public void setPmbsclogininfo(PmBscLoginInfo pmbsclogininfo) {
		this.pmbsclogininfo = pmbsclogininfo;
	}

	public PmBscLoginInfo getPmbsclogininfo() {
		return pmbsclogininfo;
	}

	public PmBscLoginInfo getSearch() {
		return search;
	}

	public void setSearch(PmBscLoginInfo search) {
		this.search = search;
	}

	public List<PmMngRoleInfo> getRolelist() {
		return rolelist;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public List getRuleDeptList() {
		return ruleDeptList;
	}

	public boolean isIfalert() {
		return ifalert;
	}

	public void setIfalert(boolean ifalert) {
		this.ifalert = ifalert;
	}
	
}
