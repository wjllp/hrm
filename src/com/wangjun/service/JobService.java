package com.wangjun.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wangjun.pojo.JobInf;
import com.wangjun.util.tag.PageModel;

@Service
public interface JobService {
	/**
	 * 获得所有职位
	 * @return Job对象的List集合
	 * */
	List<JobInf> findAllJob();
	
	List<JobInf> findJob(JobInf job,PageModel pageModel);
	
	/**
	 * 根据id删除职位
	 * @param id
	 * */
	public void removeJobById(Integer id);
	
	/**
	 * 添加职位
	 * @param Job 部门对象
	 * */
	void addJob(JobInf job);
	
	/**
	 * 根据id查询职位
	 * @param id
	 * @return 职位对象
	 * */
	JobInf findJobById(Integer id);
	
	/**
	 * 修改职位
	 * @param dept 部门对象
	 * */
	void modifyJob(JobInf job);
}
