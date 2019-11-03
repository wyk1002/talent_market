package biubiu.ServiceImpl;


import biubiu.Dao.Job_seekerMapper;
import biubiu.Model.Job_seeker;
import biubiu.Service.Job_seekerService;
import biubiu.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Job_seekerServiceImpl implements Job_seekerService {
	@Autowired
	Job_seekerMapper job_seekerMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Job_seeker"+id);
		return job_seekerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Job_seeker record)
	{
		String key="Job_seeker"+record.getId();
		redisService.set(key,record);
		return job_seekerMapper.insert(record);
	}

	@Override
	public int insertSelective(Job_seeker record)
	{
		String key="Job_seeker"+record.getId();
		redisService.set(key,record);
		return job_seekerMapper.insertSelective(record);
	}

	@Override
	public Job_seeker selectByPrimaryKey(Integer id)
	{
		String key = "Job_seeker" + id;
		if (!redisService.hasKey(key)) {
			Job_seeker Job_seeker = job_seekerMapper.selectByPrimaryKey(id);
			redisService.set(key,Job_seeker);
		}
		Job_seeker result = (Job_seeker)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Job_seeker record)
	{
		redisService.delete("Job_seeker"+record.getId());
		redisService.set("Job_seeker"+record.getId(),record);
		return job_seekerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Job_seeker record)
	{
		redisService.delete("Job_seeker"+record.getId());
		redisService.set("Job_seeker"+record.getId(),record);
		return job_seekerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Job_seeker> selectAll() {
		return job_seekerMapper.selectAll();
	}
}
