package com.wangjun.service;

import java.util.List;


import com.wangjun.pojo.EmployeeInf;
import com.wangjun.util.tag.PageModel;

public interface EmployeeService {
	/**
	 * 获得所有员工
	 * @param employee 查询条件
	 * @param pageModel 分页对象
	 * @return Dept对象的List集合
	 * */
	List<EmployeeInf> findEmployee(EmployeeInf employee,PageModel pageModel);
	
	/**
	 * 根据id删除员工
	 * @param id
	 * */
	void removeEmployeeById(Integer id);
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return 员工对象
	 * */
	EmployeeInf findEmployeeById(Integer id);
	
	/**
	 * 添加员工
	 * @param employee 员工对象
	 * */
	void addEmployee(EmployeeInf employee);
	
	/**
	 * 修改员工
	 * @param employee 员工对象
	 * */
	void modifyEmployee(EmployeeInf employee);
}
