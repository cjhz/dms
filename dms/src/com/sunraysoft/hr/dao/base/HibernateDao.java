
package com.sunraysoft.hr.dao.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.sunraysoft.hr.util.ReflectionUtils;
import com.sunraysoft.hr.web.data.dto.Page;



/**
 * Hibernat DAO泛型基类.
 * 
 * 扩展功能包括分页查询,按属性过滤条件列表查询.
 * 可在Service层直接使用,也可以扩展泛型DAO子类使用,见两个构造函数的注释.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 */
public abstract class HibernateDao<T, PK extends Serializable> extends SimpleHibernateDaoSupport<T, PK> {
	
	/**
	 * 用于Dao层子类使用的构造函数.
	 * 通过子类的泛型定义取得对象类型Class.
	 * eg.
	 * public class UserDao extends HibernateDao<User, Long>{
	 * }
	 */
	public HibernateDao() {
		super();
	}

	public HibernateDao(Class<T> cls) {
		super(cls);
	}

	//-- 分页查询函数 --//
	/**
	 * 分页获取全部对象.
	 */
	public Page<T> findAll(final Page<T> page) {
		return findPage(page);
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数.不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final String hql, final Object... values) {
		Assert.notNull(page, "page不能为空");

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setRecordCount((int)totalCount);
		}

		setPageParameter(q, page);
		List result = q.list();
		page.setResultSet(result);
		return page;
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数.
	 * @param hql hql语句.
	 * @param values 命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final String hql, final Map<String, ?> values) {
		Assert.notNull(page, "page不能为空");

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setRecordCount((int)totalCount);
		}

		setPageParameter(q, page);

		List result = q.list();
		page.setResultSet(result);
		return page;
	}

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page 分页参数.
	 * @param criterions 数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final Criterion... criterions) {
		Assert.notNull(page, "page不能为空");

		Criteria c = createCriteria(criterions);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setRecordCount((int)totalCount);
		}

		setPageParameter(c, page);
		List result = c.list();
		page.setResultSet(result);
		return page;
	}
	
	public Page<T> findPage(final Page<T> page, T entity) {
		Criteria c = getSession().createCriteria(entityClass);
		return findPage(page, c, entity);
	}
	
	
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final Criteria criteria) {
		Assert.notNull(page, "page不能为空");

		setPageParameter(criteria, page);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(criteria);
			page.setRecordCount((int)totalCount);
		}

		setPageParameter(criteria,page);

		
		List<T> result = criteria.list();
		page.setResultSet(result);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public Page<T> findPage(final Page<T> page, final Criteria criteria, T entity) {
		Assert.notNull(page, "page不能为空");

		Criteria c = setEntityParameter(criteria,page, entity);

		if(page.isWhetherpage()){
			if (page.isAutoCount()) {
				long totalCount = countCriteriaResult(criteria);
				page.setRecordCount((int)totalCount);
			}
			c = setPageParameter(criteria,page);
		}
		
		List<T> result = c.list();
		page.setResultSet(result);
		return page;
	}
	

	/**
	 * 用于子类覆盖(添加条件查询)
	 * @param criteria
	 * @param page
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Criteria setEntityParameter(Criteria criteria,Page page, T entity){
		return setPageParameter(criteria, page);
	}
	
	@SuppressWarnings("unchecked")
	public Page<T> findPage(Criteria criteria, int currentPage, int pageSize) {
		Page<T> page = new Page<T>();
		page.setCurrentNum(currentPage);
		page.setPerPageRecordCount(pageSize);

		setPageParameter(criteria, page);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(criteria);
			page.setRecordCount((int)totalCount);
		}
		
		setPageParameter(criteria, page);
		
		List<T> result = criteria.list();
		page.setResultSet(result);
		return page;
	}
	
	public Page<T> findPageBy(String propertyName, Object value, int currentPage, int pageSize) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq(propertyName, value));
		return findPage(criteria, currentPage, pageSize);
	}
	

	/**
	 * 设置分页参数到Query对象,辅助函数.
	 */
	protected Query setPageParameter(final Query q, final Page<T> page) {
		//hibernate的firstResult的序号从0开始
		q.setFirstResult(page.getBeginRecordIndex());
		q.setMaxResults(page.getPerPageRecordCount());
		return q;
	}

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameter(final Criteria c, final Page<T> page) {
		//hibernate的firstResult的序号从0开始
		if(page.isWhetherpage()){
			c.setFirstResult(page.getBeginRecordIndex());
			c.setMaxResults(page.getPerPageRecordCount());
		}
		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');
			
			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");
			
			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		
		return c;
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Object... values) {
		String fromHql = hql;
		//select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Map<String, ?> values) {
		String fromHql = hql;
		//select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 */
	@SuppressWarnings("unchecked")
	protected long countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		int start = impl.getFirstResult();
		impl.setFirstResult(0);
		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			LOG.error("不可能抛出的异常:{}", e.getMessage());
		}

		// 执行Count查询
		Integer totalCount = (Integer)c.setProjection(Projections.rowCount()).uniqueResult();

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);
		c.setFirstResult(start);
		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			LOG.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}

	//-- 属性过滤条件(PropertyFilter)查询函数 --//

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType 匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
	 */
	public List<T> findBy(final String propertyName, final Object value, final MatchType matchType) {
		Criterion criterion = buildPropertyFilterCriterion(propertyName, value, matchType);
		return find(criterion);
	}

	/**

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	protected Criterion buildPropertyFilterCriterion(final String propertyName, final Object propertyValue,
			final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = null;
		try {

			//根据MatchType构造criterion
			if (MatchType.EQ.equals(matchType)) {
				criterion = Restrictions.eq(propertyName, propertyValue);
			} else if (MatchType.LIKE.equals(matchType)) {
				criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			} else if (MatchType.LE.equals(matchType)) {
				criterion = Restrictions.le(propertyName, propertyValue);
			} else if (MatchType.LT.equals(matchType)) {
				criterion = Restrictions.lt(propertyName, propertyValue);
			} else if (MatchType.GE.equals(matchType)) {
				criterion = Restrictions.ge(propertyName, propertyValue);
			} else if (MatchType.GT.equals(matchType)) {
				criterion = Restrictions.gt(propertyName, propertyValue);
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
		return criterion;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}
}
