package com.employee.service;

import com.employee.entity.Employee;
import com.employee.entity.PageBean;

/**
 * 员工管理的业务层的接口
 * */
public interface EmployeeService {

	Employee login(Employee employee);

	PageBean<Employee> findByPage(Integer currPage);

	void save(Employee employee);

	Employee findById(Integer eid);


}
