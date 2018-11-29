package com.employee.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.employee.dao.EmployeeDao;
import com.employee.entity.Employee;
/**
 * 员工管理的Dao的实现类
 * */
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

	/**
	 * Dao中根据用户名和密码查询用户的方法
	 * */
	@Override
	public Employee findByUsernameAndPassword(Employee employee) {
		String hql = "from Employee where username = ? and password = ?";
		List<Employee> list = this.getHibernateTemplate().find(hql, employee.getUsername(), employee.getPassword());
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	/**
	 * 分页查询员工
	 * */
	public List<Employee> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
		List<Employee> list = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	@Override
	public int findCount() {
		String hql = "select count(*) from Employee";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list.size() > 0) {
			return list.get(0).intValue();//转成整型返回
		}
		return 0;
	}

	@Override
	/**
	 * Dao中保存部分的方法
	 * */
	public void save(Employee employee) {
		this.getHibernateTemplate().save(employee);
		
	}

	@Override
	//Dao中按照ID查询员工的方法
	public Employee findById(Integer eid) {
		return this.getHibernateTemplate().get(Employee.class, eid);
	}

}
