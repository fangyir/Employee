package com.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.employee.dao.DepartmentDao;
import com.employee.entity.Department;
/**部门管理的Dao层的实现类
 * */
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

	@Override
	public int findCount() {
		String hql = "select count(*) from Department";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list.size() > 0) {
			return list.get(0).intValue();//转成整型返回
		}
		return 0;
	}

	@Override
	/**
	 * 分页查询部门
	 * */
	public List<Department> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		 List<Department> list = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);//查询并返回List集合
		return list;
	}

	@Override
	/**
	 * Dao中保存部分的方法
	 * */
	public void save(Department department) {
		this.getHibernateTemplate().save(department);
	}

	@Override
	/**
	 * Dao中根据部门的ID查询部门的方法
	 * */
	public Department findById(Integer did) {
		return this.getHibernateTemplate().get(Department.class, did);
	}

	@Override
	//Dao中修改部门的方法
	public void update(Department department) {
		this.getHibernateTemplate().update(department);
	}

	@Override
	//Dao中删除部门的方法
	public void delete(Department department) {
		this.getHibernateTemplate().delete(department);
	}

	@Override
	//Dao中查询所有部门的方法
	public List<Department> findAll() {
		return this.getHibernateTemplate().find("from Department");
	}

	

}
