package com.sunraysoft.hr.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.sunraysoft.hr.util.ReflectionUtils;



/**
 * 实现简单DAO功能
 * @author defier.lai
 * 2010-3-24 上午02:56:35
 * @version 1.0
 */
public abstract class SimpleHibernateDaoSupport<T, PK extends Serializable> extends HibernateDaoSupport {
	
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	
	protected Class<T> entityClass;
	
	//public abstract SessionFactory getSessionFactory();
	
	/**
	 * 获取当前的session
	 * @return
	 */
	//public Session getSession() {
	//	return getSessionFactory().getCurrentSession();
	//}
	
	public SimpleHibernateDaoSupport() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public SimpleHibernateDaoSupport(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@SuppressWarnings("unchecked")
	public T find(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().load(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public T get(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().get(entityClass, id);
	}
	
	/**
	 * 保存新增或修改的对象.
	 */
	public void save(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		setBaseProperties(entity);
		getSession().saveOrUpdate(entity);
		LOG.debug("save entity: {}", entity);
	}

	private void setBaseProperties(final T entity) {
		//
	}
	
	public void merge(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		setBaseProperties(entity);
		getSession().merge(entity);
		LOG.debug("merge entity: {}", entity);
	}
	
	/**
	 * 更新保存
	 * @param entity
	 */
	public void saveOrUpdate(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		setBaseProperties(entity);
		getSession().saveOrUpdate(entity);
		LOG.debug("saveOrUpdate entity: {}", entity);
	}
	
	public void update(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		setBaseProperties(entity);
		getSession().update(entity);
		LOG.debug("update entity: {}", entity);
	}
	
	/**
	 * 删除对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	public void destory(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		LOG.debug("delete entity: {}", entity);
	}

	/**
	 * 按id删除对象.
	 */
	public void destory(final PK id) {
		Assert.notNull(id, "id不能为空");
		destory(find(id));
		LOG.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}
	
	/**
	 * 删除所有
	 * @param entities
	 */
	public void destoryAll(final Collection<?> entities) {
		for(Object entity : entities) {
			getSession().delete(entity);
		}
	}
	
	
	/**
	 *	获取全部对象.
	 */
	public List<T> findAll() {
		return find();
	}

	/**
	 * 获取全部对象,支持排序.
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(String orderBy, boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return c.list();
	}
	
	/**
	 * 获取全部对象,支持排序.
	 * @param orderBy 
	 * @param isAsc
	 * @param propertyName 查询给哪个属性加条件
	 * @param value 属性值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(String orderBy, boolean isAsc, 
			final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		Criteria c = createCriteria(criterion);
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return c.list();
	}
	
	/**
	 * 获取全部对象,支持排序.
	 * @param OjbectName 查询关联的对象
	 * @param propertyName 查询给关联对象的哪个属性加条件
	 * @param value 属性值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entity,final String OjbectName,final String propertyName, final Object value) {
		Criteria c = getSession().createCriteria(entity);
		c.createAlias(OjbectName, OjbectName).add(
				Restrictions.eq(OjbectName + "." + propertyName, value));
		return c.list();
	}
	
	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}
	
	/**
	 * 按属性数组查找对象列表,匹配方式为相等.
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List<T> findByValues(final String[] propertyNames, final Object[] values) {
		//Assert.hasText(propertyName, "propertyName不能为空");
		Criterion[] criterions = null;
		if(propertyNames.length==values.length){
			criterions = new Criterion[propertyNames.length];
			for(int i=0;i<propertyNames.length;i++){
				criterions[i] = Restrictions.eq(propertyNames[i],values[i]);
			}
		}else{
			return null;
		}
		return find(criterions);
	}
	
	/**
	 * 按属性数组查找对象列表,匹配方式为相等,并排序.
	 * @param orderBy
	 * @param isAsc
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByValuesOrder(String orderBy, boolean isAsc,final String[] propertyNames, final Object[] values) {
		//Assert.hasText(propertyName, "propertyName不能为空");
		Criterion[] criterions = null;
		if(propertyNames.length==values.length){
			criterions = new Criterion[propertyNames.length];
			for(int i=0;i<propertyNames.length;i++){
				criterions[i] = Restrictions.eq(propertyNames[i],values[i]);
			}
		}else{
			return null;
		}
		Criteria c = createCriteria(criterions);
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return c.list();
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	/**
	 * 按id列表获取对象.
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(List<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	/**
	 * 按HQL查询对象列表.
	 * @param values 数量可变的参数,按顺序绑定.
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * @param values 命名参数,按名称绑定.
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param values 数量可变的参数,按顺序绑定.
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param values 命名参数,按名称绑定.
	 * @param <X>
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @param hql
	 * @param values
	 * @return
	 */
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数
	 * @param hql
	 * @param values
	 * @return
	 */
	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * @param values 命名参数,按名称绑定.
	 * @param queryString
	 * @param values
	 * @return
	 */
	public Query createQuery(final String queryString, final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * 根据查询SQL与参数列表创建Query对象.
	 * @param queryString
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List createSQLQuery(final String queryString, final Map<String, Object> map) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createSQLQuery(queryString);
		if(map != null&&!map.isEmpty()){
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.list();
	}
	
	/**
	 * 按Criteria查询对象列表.
	 * @param criterions 数量可变的Criterion.
	 * @param criterions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 按Criteria查询唯一对象.
	 * @param criterions 数量可变的Criterion.
	 * @param criterions
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 初始化对象.
	 * 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
	 * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性.
	 * 如需初始化关联属性,可实现新的函数,执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public void initEntity(T entity) {
		Hibernate.initialize(entity);
	}

	/**
	 * 
	 * @param entityList
	 */
	public void initEntity(List<T> entityList) {
		for (T entity : entityList) {
			Hibernate.initialize(entity);
		}
	}

	/**
	 * 为Query添加distinct transformer.
	 * @param query
	 * @return
	 */
	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	/**
	 * 为Criteria添加distinct transformer.
	 * @param criteria
	 * @return
	 */
	public Criteria distinct(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	/**
	 * 取得对象的主键名.
	 * @return
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}
}
