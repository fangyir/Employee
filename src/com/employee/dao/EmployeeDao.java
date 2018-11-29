package com.employee.dao;

import java.util.List;

import com.employee.entity.Employee;

/**
 * 员工管理的Dao的接口
 * */
public interface EmployeeDao {

	Employee findByUsernameAndPassword(Employee employee);

	List<Employee> findByPage(int begin, int pageSize);

	int findCount();

	void save(Employee employee);

	Employee findById(Integer eid);

}
