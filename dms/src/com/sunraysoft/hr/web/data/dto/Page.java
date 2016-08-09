package com.sunraysoft.hr.web.data.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 分页dto: 处理具体的分页逻辑
 *
 */
public class Page<T> {

	private Integer upPageNum; // 上一页页码

	private Integer downPageNum; // 下一页页码

	private Integer firstPageNum; // 首页

	private Integer endPageNum; // 尾页

	private Integer perPageRecordCount; // 每页显示记录数

	private Integer currentNum; // 当前页码

	private Integer pageCount; // 总页数

	private Integer recordCount; // 总记录数

	private Integer beginRecordIndex; // 当前页开始显示的记录数 从0开始
	
	private Integer offset;
	
	private List<T> resultSet;
	
	protected boolean autoCount = true;
	
	private boolean whetherpage = true;
	
	//-- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	protected String orderBy = null;
	protected String order = null;

	public Page() {
		beginRecordIndex = 1;
	}
	
	public Page(int currentNum, int recordCount, int perPageRecordCount) {
		if(currentNum <= 0) currentNum = 1;
		if(perPageRecordCount <= 0) perPageRecordCount = 10;
		
		this.currentNum = new Integer(currentNum);
		this.perPageRecordCount = new Integer(perPageRecordCount);
		this.recordCount = new Integer(recordCount);
		setValue();
	}

	public final void setValue() {

		int _page = -1;
		int _perPageRecordCount = -1;
		int _recordCount = -1;
		int _pageCount = -1;
		int _beginIndex = -1;
		int _upPageNum = -1;
		int _downPageNum = -1;
		int _firstPageNum = -1;

		List<String> _pages = new ArrayList<String>();
		_page = this.currentNum.intValue();
		_perPageRecordCount = this.perPageRecordCount.intValue();
		_recordCount = this.recordCount.intValue();
		_pageCount = _recordCount / _perPageRecordCount
				+ (_recordCount % _perPageRecordCount == 0 ? 0 : 1); // 计算出总的页数

		_beginIndex = (_page - 1) * _perPageRecordCount;
		
		if (_pageCount != -1 && _page != 1) {
			_firstPageNum = 1;
		}

		if (_page > 1) {
			_upPageNum = _page - 1;
		}

		if (_page + 1 <= _pageCount) {
			_downPageNum = _page + 1;
		}

		if (_pageCount != -1) {
			this.pageCount = new Integer(_pageCount);
		}

		if (_beginIndex != -1) {
			this.beginRecordIndex = new Integer(_beginIndex);
		}

		if (_upPageNum != -1) {
			this.upPageNum = new Integer(_upPageNum);
		}

		if (_downPageNum != -1) {
			this.downPageNum = new Integer(_downPageNum);
		}

		if (_firstPageNum != -1) {
			this.firstPageNum = new Integer(_firstPageNum);
		}

		if (_pageCount != -1 && _page != _pageCount) {
			this.endPageNum = new Integer(_pageCount);
		}
		
		offset = (beginRecordIndex + perPageRecordCount > recordCount) ? (recordCount % perPageRecordCount) : perPageRecordCount;
	}

	public Integer getBeginRecordIndex() {
		return beginRecordIndex;
	}

	public void setBeginRecordIndex(Integer beginRecordIndex) {
		this.beginRecordIndex = beginRecordIndex;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	//在HibernateDao设置setRecordCount时需 调用setValue()
	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
		if(currentNum>0&&perPageRecordCount>0){
			setValue();
		}
	}

	public Integer getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}

	public Integer getDownPageNum() {
		return downPageNum;
	}

	public void setDownPageNum(Integer downPageNum) {
		this.downPageNum = downPageNum;
	}

	public Integer getPerPageRecordCount() {
		return perPageRecordCount;
	}

	public void setPerPageRecordCount(Integer perPageRecordCount) {
		this.perPageRecordCount = perPageRecordCount;
	}

	public Integer getUpPageNum() {
		return upPageNum;
	}

	public void setUpPageNum(Integer upPageNum) {
		this.upPageNum = upPageNum;
	}

	public Integer getFirstPageNum() {
		return firstPageNum;
	}

	public void setFirstPageNum(Integer firstPageNum) {
		this.firstPageNum = firstPageNum;
	}

	public Integer getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(Integer endPageNum) {
		this.endPageNum = endPageNum;
	}
	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public List<T> getResultSet() {
		return resultSet;
	}

	public void setResultSet(List<T> resultSet) {
		this.resultSet = resultSet;
	}
	
	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * 获得排序方向.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		//检查order字符串的合法值
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.order = StringUtils.lowerCase(order);
	}

	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	
	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	public boolean isWhetherpage() {
		return whetherpage;
	}

	public void setWhetherpage(boolean whetherpage) {
		this.whetherpage = whetherpage;
	}
	
}
