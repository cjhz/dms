package com.sunraysoft.hr.service.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.sunraysoft.hr.domain.entity.base.BizEntity;
import com.sunraysoft.hr.web.data.dto.Page;


public interface BaseService<T extends BizEntity, PK extends Serializable>{
	public T find(final PK id);
	
	public T get(final PK id);
	/**
	 * 保存新增或修改的对象.
	 */
	public void save(final T entity);

	public void merge(final T entity);
	
	/**
	 * 更新保存
	 * @param entity
	 */
	public void saveOrUpdate(final T entity);
	
	public void update(final T entity);
	
	/**
	 * 删除对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	public void remove(final T entity) ;

	/**
	 * 按id删除对象.
	 */
	public void remove(final PK id) ;
	
	/**
	 * 删除所有
	 * @param entities
	 */
	public void removeAll(final Collection<?> entities);
	
	
	/**
	 *	获取全部对象.
	 */
	public List<T> findAll();

	/**
	 * 获取全部对象,支持排序.
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public List<T> findAll(String orderBy, boolean isAsc);
	
	/**
	 * 按属性查找对象列表,匹配方式为相等.加排序
	 * @param orderBy
	 * @param isAsc
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findAll(String orderBy, boolean isAsc,final String propertyName, final Object value);
	
	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findBy(final String propertyName, final Object value) ;
	
	/**
	 * 获取全部对象,支持排序.
	 * @param OjbectName 查询关联的对象
	 * @param propertyName 查询给关联对象的哪个属性加条件
	 * @param value 属性值
	 * @return
	 */
	public List<T> findAll(Class<T> entity, final String OjbectName,final String propertyName, final Object value);
	
	/**
	 * 按属性数组查找对象列表,匹配方式为相等. 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List<T> findByValues(final String[] propertyNames, final Object[] values);
	
	/**
	 * 按属性数组查找对象列表,匹配方式为相等,并排序.
	 * @param orderBy
	 * @param isAsc
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List<T> findByValuesOrder(String orderBy, boolean isAsc,final String[] propertyNames, final Object[] values);

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public T findUniqueBy(final String propertyName, final Object value);

	/**
	 * 按id列表获取对象.
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(List<PK> ids);
	/**
	 * 按HQL查询对象列表.
	 * @param values 数量可变的参数,按顺序绑定.
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 */
	public <X> List<X> find(final String hql, final Object... values);

	/**
	 * 按HQL查询对象列表.
	 * @param values 命名参数,按名称绑定.
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 */
	public <X> List<X> find(final String hql, final Map<String, ?> values) ;

	/**
	 * 按HQL查询唯一对象.
	 * @param values 数量可变的参数,按顺序绑定.
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 */
	public <X> X findUnique(final String hql, final Object... values);

	/**
	 * 按HQL查询唯一对象.
	 * @param values 命名参数,按名称绑定.
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 */
	public <X> X findUnique(final String hql, final Map<String, ?> values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @param hql
	 * @param values
	 * @return
	 */
	public int batchExecute(final String hql, final Object... values) ;

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数
	 * @param hql
	 * @param values
	 * @return
	 */
	public int batchExecute(final String hql, final Map<String, ?> values);
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, final Object... values) ;

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * @param values 命名参数,按名称绑定.
	 * @param queryString
	 * @param values
	 * @return
	 */
	public Query createQuery(final String queryString, final Map<String, ?> values);

	/**
	 * 按Criteria查询对象列表.
	 * @param criterions 数量可变的Criterion.
	 * @param criterions
	 * @return
	 */
	public List<T> find(final Criterion... criterions);
	/**
	 * 按Criteria查询唯一对象.
	 * @param criterions 数量可变的Criterion.
	 * @param criterions
	 * @return
	 */
	public T findUnique(final Criterion... criterions);
	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions);
	
	/**
	 * 通过page和实体分页查询
	 * @param page
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(final Page<T> page, T entity);
}
