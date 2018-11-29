package com.employee.action;

import java.util.List;

import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.entity.PageBean;
import com.employee.service.DepartmentService;
import com.employee.service.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 员工管理的Action类
 * */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{

	//模型驱动使用的对象
	private Employee employee = new Employee();
	@Override
	public Employee getModel() {
		return employee;
	}
	//注入业务层的类
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	//接收当前页数
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	/**
	 * 登录执行的方法
	 * @return
	 * */
	public String login() {
		System.out.println("login执行了。。。");
		//调用业务层的类
		Employee existEmployee = employeeService.login(employee);
		if(existEmployee == null) {
			//登录失败
			this.addActionError("用户名或者密码错误！");
			return INPUT;
		}else {
			//登录成功
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
		
	}

	//分页查询员工的执行方法
	public String findAll() {
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		//将pageBean存入到值栈中
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
		
	}
	//跳转到员工添加页面的方法
	public String saveUI() {
		//查询所有部门
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
		
	}
	//保存员工的执行方法
	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}
	
	
	//编辑员工的执行方法
	public String edit() {
		//模型驱动不需要保存
		//根据员工ID查询员工
		employee = employeeService.findById(employee.getEid());
		//查询所有的部门
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().setValue("list", list);
		return "editSuccess";
	}
}
