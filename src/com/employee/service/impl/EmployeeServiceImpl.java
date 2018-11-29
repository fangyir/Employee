package com.employee.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.employee.dao.DepartmentDao;
import com.employee.dao.EmployeeDao;
import com.employee.entity.Employee;
import com.employee.entity.PageBean;
import com.employee.service.EmployeeService;
/**
 * 员工管理的业务层的实现类
 * */
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	//注入员工管理的Dao
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	/**
	 * 业务层的登录方法：
	 * */
	public Employee login(Employee employee) {
		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}

	@Override
	/**
	 * 业务层的员工分页查询的方法
	 * */
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		//封装当前页数
		pageBean.setCurrPage(currPage);
		//封装每页显示的记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		
		//封装总记录数
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;//转换为double型
		Double num = Math.ceil(tc / pageSize); //向上取整
		pageBean.setTotalPage(num.intValue());//转换为整数值
		//封装每页显示的数据
		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin , pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	//业务层保存员工方法
	public void save(Employee employee) {
		employeeDao.save(employee);
		
	}

	@Override
	//Dao中按照ID查询员工的方法
	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}
	
}
