package com.sunraysoft.hr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.dao.StaffDao;
import com.sunraysoft.hr.dao.StaffJobChgDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscJobChange;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.service.StaffJobChgService;
import com.sunraysoft.hr.service.base.AbstractService;

@Service("staffJobChgService")
public class StaffJobChgServiceImpl extends AbstractService<PmBscJobChange, Long> implements StaffJobChgService  {

	@Resource StaffJobChgDao staffJobChgDao;
	@Resource StaffDao staffDao;
	@Override
	public BaseDao<PmBscJobChange, Long> getCurrentDao() {
		return staffJobChgDao;
	}
	
	/**
	 * 新增
	 */
	@Override
	public void createJobChange(PmBscJobChange staffJob) throws Exception{
		staffJob.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		staffJob.setVcIfedit(Long.valueOf(BizConstant.STAFF_CHANGE_IFEIDT_YES));
		Long staffId=staffJob.getPmBscStaffInfo().getPid();
		//新增（在一个事务中，新增还没有保存到数据库，最新的一条就是commit之后的第二条）
		List<PmBscJobChange> ranklist = staffJobChgDao.getMaxResult(new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId});
		if(ranklist!=null&&ranklist.size()>0){
			PmBscJobChange rank = ranklist.get(0);
			if(rank!=null){
				rank.setVcEndDate(staffJob.getVcChgDate());
				staffJobChgDao.merge(rank);
			}
		}
		staffJobChgDao.save(staffJob);
		updateFlag(staffJob);
		updateStaff(staffJob);
		
		/**
		 * 只有最后一次变动才能修改staff，所有只有最后一次变动才能修改
		 */
		
	}

	/**
	 * 修改
	 */
	@Override
	public void updateJobChange(PmBscJobChange staffJob) throws Exception {
		staffJob.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		staffJob.setVcIfedit(Long.valueOf(BizConstant.STAFF_CHANGE_IFEIDT_YES));
		staffJobChgDao.merge(staffJob);
		Long staffId=staffJob.getPmBscStaffInfo().getPid();
		Long second = staffJobChgDao.getSecondPid(new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffId});
		if(second!=0L){
			PmBscJobChange rank = staffJobChgDao.find(second);
			if(rank!=null){
				rank.setVcEndDate(staffJob.getVcChgDate());
				staffJobChgDao.saveOrUpdate(rank);
			}
		}
		updateFlag(staffJob);
		updateStaff(staffJob);
	}
	
	/**
	 * 更新staff
	 * @param staffRank
	 */
	private void updateStaff(PmBscJobChange staffJob){
		Long staffId=staffJob.getPmBscStaffInfo().getPid();
		PmBscStaffInfo stf = staffDao.find(Long.valueOf(staffId));
		stf.setVcWorkDivision(staffJob.getVcChgJob());
		stf.setVcWorkDate(staffJob.getVcChgDate());
		stf.setVcExtend1(staffJob.getVcExtend2());
		staffDao.saveOrUpdate(stf);
	}
	
	/**
	 * 更新非最新数据标志
	 * @param staffRank
	 */
	private void updateFlag(PmBscJobChange staffJob){
		//修改标志位
		Long staffId=staffJob.getPmBscStaffInfo().getPid();
		staffJobChgDao.batchExecute("update PmBscJobChange set vcIfedit=1 where pid<? and vc_staff_id=?", new Object[]{staffJob.getPid(),staffId});
	}
}
