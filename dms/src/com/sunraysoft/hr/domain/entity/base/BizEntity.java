package com.sunraysoft.hr.domain.entity.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.sunraysoft.hr.annotation.OperLog;
import com.sunraysoft.hr.constant.BizConstant;

/**
 * 
 *
 */
@MappedSuperclass
public abstract class BizEntity implements BizEntityId,Cloneable {
	
	public static final String ID_KEY = "ID";
	public static final String DELETE_FLAG_KEY = "删除标记";
	public static final String EXTEND1_KEY = "扩展字段1";
	public static final String EXTEND2_KEY = "扩展字段2";
	public static final String EXTEND3_KEY = "扩展字段3";
	public static final String EXTEND4_KEY = "扩展字段4";
	public static final String MEMO_KEY = "备注";
	public static final String BG_COLOUR_KEY = "背景颜色";
	public static final String DELETE_FLAG_NORMAL_KEY = "正常";
	public static final String DELETE_FLAG_INVALID_KEY = "删除";
	
	public BizEntity clone() {
		BizEntity o = null;
		try {
			o = (BizEntity) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return o;
	}
	
	
	@OperLog(i18nKey=DELETE_FLAG_KEY, 
			constKeys={BizConstant.SYSTEM_DATA_DELETE_FLAG_NORMAL, BizConstant.SYSTEM_DATA_DELETE_FLAG_INVALID}, 
			constTexts={DELETE_FLAG_NORMAL_KEY, DELETE_FLAG_INVALID_KEY})
	private Long vcDeleteFlag = 0L; //是否删除 1 已删除 0正常
	
	@OperLog(i18nKey=EXTEND1_KEY)
	private String vcExtend1;
	
	@OperLog(i18nKey=EXTEND2_KEY)
	private String vcExtend2;
	
	@OperLog(i18nKey=EXTEND3_KEY)
	private String vcExtend3;
	
	@OperLog(i18nKey=EXTEND4_KEY)
	private String vcExtend4;


	@Column(name="VC_EXTEND1", columnDefinition="VARCHAR2(128)")
	public String getVcExtend1() {
		return vcExtend1;
	}

	public void setVcExtend1(String vcExtend1) {
		this.vcExtend1 = vcExtend1;
	}

	@Column(name="VC_EXTEND2", columnDefinition="VARCHAR2(256)")
	public String getVcExtend2() {
		return vcExtend2;
	}

	public void setVcExtend2(String vcExtend2) {
		this.vcExtend2 = vcExtend2;
	}

	@Column(name="VC_EXTEND3", columnDefinition="VARCHAR2(512)")
	public String getVcExtend3() {
		return vcExtend3;
	}

	public void setVcExtend3(String vcExtend3) {
		this.vcExtend3 = vcExtend3;
	}

	@Column(name="VC_EXTEND4", columnDefinition="VARCHAR2(1024)")
	public String getVcExtend4() {
		return vcExtend4;
	}

	public void setVcExtend4(String vcExtend4) {
		this.vcExtend4 = vcExtend4;
	}

	@Column(name="VC_DELETE_FLAG", columnDefinition="NUMBER(2)", nullable=false)
	public Long getVcDeleteFlag() {
		return vcDeleteFlag;
	}

	public void setVcDeleteFlag(Long vcDeleteFlag) {
		this.vcDeleteFlag = vcDeleteFlag;
	}
}
