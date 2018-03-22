package com.wangjun.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjun.mapper.JobInfMapper;
import com.wangjun.pojo.JobInf;
import com.wangjun.pojo.JobInfExample;
import com.wangjun.service.JobService;
import com.wangjun.util.tag.PageModel;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobInfMapper jobMapper;

	@Override
	public List<JobInf> findAllJob() {
		// TODO Auto-generated method stub
		JobInfExample example = new JobInfExample();
		return jobMapper.selectByExample(example);
	}

	@Override
	public List<JobInf> findJob(JobInf job, PageModel pageModel) {
		// TODO Auto-generated method stub
		JobInfExample example = new JobInfExample();
		List<JobInf> list = jobMapper.selectByExample(example);
		int recordCount = list.size();
		pageModel.setRecordCount(recordCount);
		if (recordCount != 0) {
			List<JobInf> jobs = new ArrayList<>();
			if (pageModel.getPageSize() * pageModel.getPageIndex() < list.size()) {
				for (int i = pageModel.getFirstLimitParam(); i < pageModel.getPageSize()
						* pageModel.getPageIndex(); i++) {
					jobs.add(list.get(i));
				}
			} else {
				for (int i = pageModel.getFirstLimitParam(); i < list.size(); i++) {
					jobs.add(list.get(i));
				}
			}
			return jobs;
		} else {
			return null;
		}
	}

	@Override
	public void removeJobById(Integer id) {
		// TODO Auto-generated method stub
		jobMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void addJob(JobInf job) {
		// TODO Auto-generated method stub
		jobMapper.insert(job);
	}

	@Override
	public JobInf findJobById(Integer id) {
		// TODO Auto-generated method stub
		return jobMapper.selectByPrimaryKey(id);
	}

	@Override
	public void modifyJob(JobInf job) {
		// TODO Auto-generated method stub
		jobMapper.updateByPrimaryKeySelective(job);
	}

}
