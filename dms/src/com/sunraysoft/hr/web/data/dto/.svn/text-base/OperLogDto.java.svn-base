package com.sunraysoft.hr.web.data.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.sunraysoft.hr.domain.entity.manage.PmMngOper;

public class OperLogDto {
	
	List<PmMngOper> beforeList = new ArrayList<PmMngOper>();
	List<PmMngOper> afterList = new ArrayList<PmMngOper>();
	
	public void addBeforeOper(Long operId) {
		PmMngOper oper = new PmMngOper();
		oper.setPid(operId);
		beforeList.add(oper);
	}
	
	public void addAfterOper(Long operId) {
		PmMngOper oper = new PmMngOper();
		oper.setPid(operId);
		afterList.add(oper);
	}
	
	public void setOperName(Map<Long, PmMngOper> operMap) {
		PmMngOper oper = null;
		for(PmMngOper e : beforeList) {
			oper = operMap.get( e.getPid() );
			if(oper != null) {
				e.setVcOperName( oper.getVcOperName() );
			}
 		}
		for(PmMngOper e : afterList) {
			oper = operMap.get( e.getPid() );
			if(oper != null) {
				e.setVcOperName( oper.getVcOperName() );
			}
		}
	}
	
	public String getChangeDesc() {
		OperComparator comparator = new OperComparator();
		PmMngOper[] before = new PmMngOper[beforeList.size()]; 
		PmMngOper[] after = new PmMngOper[afterList.size()];
		Arrays.sort(before, comparator);
		Arrays.sort(after, comparator);
		
		StringBuilder sb = new StringBuilder();
		
		if(!isEquals(before, after)) {
			sb.append("操作:[");
			for(int i = 0; i < before.length; i++) {
				sb.append(before[i].getVcOperName());
				if(i + 1 != before.length) {
					sb.append(", ");
				}
			}
			sb.append("]");
			sb.append(" --> ");
			sb.append("操作:[");
			for(int i = 0; i < after.length; i++) {
				sb.append(after[i].getVcOperName());
				if(i + 1 != after.length) {
					sb.append(", ");
				}
			}
			sb.append("]");
		}
		
		return sb.toString();
	}
	
	private boolean isEquals(PmMngOper[] x, PmMngOper[] y) {
		if(x.length != y.length) {
			return false;
		}
		for(int i = 0; i < x.length; i++) {
			if(!x[i].getPid().equals(y[i].getPid())) {
				return false;
			}
		}
		
		return true;
	}
	
	static class OperComparator implements Comparator<PmMngOper> {

		@Override
		public int compare(PmMngOper x, PmMngOper y) {
			return x.getPid().compareTo(y.getPid());
		}
	}
}
