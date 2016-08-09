package com.sunraysoft.hr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunraysoft.hr.constant.BizConstant;
import com.sunraysoft.hr.dao.StaffDao;
import com.sunraysoft.hr.dao.StaffRankChgDao;
import com.sunraysoft.hr.dao.base.BaseDao;
import com.sunraysoft.hr.domain.entity.basic.PmBscRankChange;
import com.sunraysoft.hr.domain.entity.basic.PmBscStaffInfo;
import com.sunraysoft.hr.service.StaffRankChgService;
import com.sunraysoft.hr.service.base.AbstractService;

/**
 * @msg 职级、职务、编制新增修改
 * @author chenjh
 * @version 2012-08-29
 */
@Service("staffRankChgService")
public class StaffRankChgServiceImpl extends AbstractService<PmBscRankChange, Long> implements StaffRankChgService {

	@Resource StaffRankChgDao staffRankChgDao;
	@Resource StaffDao staffDao;
	@Override
	public BaseDao<PmBscRankChange, Long> getCurrentDao() {
		return staffRankChgDao;
	}

	/**
	 * 新增
	 */
	@Override
	public void createRankChange(PmBscRankChange staffRank) throws Exception{
		staffRank.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		staffRank.setVcIfedit(Long.valueOf(BizConstant.STAFF_CHANGE_IFEIDT_YES));
		Long staffId=staffRank.getPmBscStaffInfo().getPid();
		//新增（在一个事务中，新增还没有保存到数据库，最新的一条就是commit之后的第二条）
		List<PmBscRankChange> ranklist = staffRankChgDao.getMaxResult(new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffRank.getVcType(),staffId});
		if(ranklist!=null&&ranklist.size()>0){
			PmBscRankChange rank = ranklist.get(0);
			if(rank!=null){
				rank.setVcEndDate(staffRank.getVcChgDate());
				staffRankChgDao.merge(rank);
			}
		}
		staffRankChgDao.save(staffRank);
		updateFlag(staffRank);
		updateStaff(staffRank);
		
		/**
		 * 只有最后一次变动才能修改staff，所有只有最后一次变动才能修改
		 */
		
	}

	/**
	 * 修改
	 */
	@Override
	public void updateRankChange(PmBscRankChange staffRank) throws Exception {
		staffRank.setVcDeleteFlag( Long.valueOf(BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL) );
		staffRank.setVcIfedit(Long.valueOf(BizConstant.STAFF_CHANGE_IFEIDT_YES));
		staffRankChgDao.merge(staffRank);
		Long staffId=staffRank.getPmBscStaffInfo().getPid();
		Long second = staffRankChgDao.getSecondPid(new Object[]{BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL,staffRank.getVcType(),staffId});
		if(second!=0L){
			PmBscRankChange rank = staffRankChgDao.find(second);
			if(rank!=null){
				rank.setVcEndDate(staffRank.getVcChgDate());
				staffRankChgDao.saveOrUpdate(rank);
			}
		}
		updateFlag(staffRank);
		updateStaff(staffRank);
	}
	
	/**
	 * 更新staff
	 * @param staffRank
	 */
	private void updateStaff(PmBscRankChange staffRank){
		Long staffId=staffRank.getPmBscStaffInfo().getPid();
		PmBscStaffInfo stf = staffDao.find(Long.valueOf(staffId));
		if(staffRank.getVcType()==BizConstant.STAFF_ATTR_TYPE_KEY_1){
			stf.setDictRanks(staffRank.getDictChgRanks());
			stf.setVcRankDate(staffRank.getVcChgDate());
		}else if(staffRank.getVcType()==BizConstant.STAFF_ATTR_TYPE_KEY_2){
			stf.setDictJobs(staffRank.getDictChgRanks());
			stf.setVcJobDate(staffRank.getVcChgDate());
		}else if(staffRank.getVcType()==BizConstant.STAFF_ATTR_TYPE_KEY_3){
			stf.setDictFormations(staffRank.getDictChgRanks());
			stf.setVcFormationDate(staffRank.getVcChgDate());
		}
		staffDao.saveOrUpdate(stf);
	}
	
	/**
	 * 更新非最新数据标志
	 * @param staffRank
	 */
	private void updateFlag(PmBscRankChange staffRank){
		//修改标志位
		Long staffId=staffRank.getPmBscStaffInfo().getPid();
		staffRankChgDao.batchExecute("update PmBscRankChange set vcIfedit=1 where pid<? and vcType=? and vc_staff_id=?", new Object[]{staffRank.getPid(),staffRank.getVcType(),staffId});
	}
}
