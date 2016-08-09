/**
 * Copyright (c) 2012 by sunraytech.cn.
 * All right reserved.
 */
package com.sunraysoft.hr.util;

import java.util.Date;

import org.junit.Test;

import com.sunraysoft.hr.domain.entity.basic.PmBscAreaInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptInfo;
import com.sunraysoft.hr.domain.entity.basic.PmBscDeptType;
import com.sunraysoft.hr.domain.entity.basic.PmBscDict;
import com.sunraysoft.hr.domain.entity.basic.PmBscObserType;
import com.sunraysoft.hr.domain.entity.basic.PmBscObservingStaff;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;

/**
 * TODO
 * @author Jerry Zhang
 * 2012-8-22 下午02:28:09
 */
public class ExportUtilsTest {
	
//	@Test
	public void TestPmBscDeptInfo() {
		PmBscDeptInfo p = new PmBscDeptInfo();
		p.setVcDeptNo("12345667890");
		p.setVcDeptName("我的机关名称");
		p.setVcChargeName("我的机构负责人");
		p.setVcPhone("13888888888");
		
		PmBscAreaInfo area = new PmBscAreaInfo();
		area.setVcAreaName("我的地区名称");
		p.setPmBscAreaInfo(area);
		PmBscDeptType type = new PmBscDeptType();
		type.setVcTypeName("我的类型名称");
		p.setPmBscDeptType(type);
		
		
		PmBscDict dict = new PmBscDict();
		dict.setVcName("处级");
		p.setPmBscDict(dict);
		
		String[] rt = ExportUtils.createHeader(p.getClass());
		if(rt != null)
		for (int i = 0; i < rt.length; i++) {
			System.err.println(rt[i]);
		}
		
		Integer[] rt2 = ExportUtils.createHeaderWidth(p.getClass());
		for (int i = 0; i < rt2.length; i++) {
			System.err.println(rt2[i]);
		}
		
		Object[] rt3 = ExportUtils.createSingleContext(p);
		for (int i = 0; i < rt3.length; i++) {
			System.err.println(rt3[i]);
		}
	}
	
	@Test
	public void TestPmBscObservingStaff() {
		PmBscObservingStaff p = new PmBscObservingStaff();
		PmBscObserType pmBscObserType = new PmBscObserType();
		pmBscObserType.setVcName("个人一等功");
		p.setPmBscObserType(pmBscObserType);
		PmBscStaffInfo pmBscStaffInfo = new PmBscStaffInfo();
		pmBscStaffInfo.setVcSex(1L);
		pmBscStaffInfo.setVcRealName("表彰人姓名");
		p.setPmBscStaffInfo(pmBscStaffInfo);
		p.setVcObservTime(new Date());
		p.setVcRemark("因写这个破代码，被授于最搞的方法奖");
		
		
		
		String[] rt = ExportUtils.createHeader(p.getClass());
		if(rt != null)
		for (int i = 0; i < rt.length; i++) {
			System.err.println(rt[i]);
		}
		
		Integer[] rt2 = ExportUtils.createHeaderWidth(p.getClass());
		for (int i = 0; i < rt2.length; i++) {
			System.err.println(rt2[i]);
		}
		
		Object[] rt3 = ExportUtils.createSingleContext(p);
		for (int i = 0; i < rt3.length; i++) {
			System.err.println(rt3[i]);
		}
	}
}
