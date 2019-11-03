package biubiu.ServiceImpl;


import biubiu.Dao.Recruit_fairMapper;
import biubiu.Model.Recruit_fair;
import biubiu.Service.Recruit_fairService;
import biubiu.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Recruit_fairServiceImpl implements Recruit_fairService {
	@Autowired
	Recruit_fairMapper Recruit_fairMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Recruit_fair"+id);
		return Recruit_fairMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Recruit_fair record)
	{
		String key="Recruit_fair"+record.getId();
		redisService.set(key,record);
		return Recruit_fairMapper.insert(record);
	}

	@Override
	public int insertSelective(Recruit_fair record)
	{
		String key="Recruit_fair"+record.getId();
		redisService.set(key,record);
		return Recruit_fairMapper.insertSelective(record);
	}

	@Override
	public Recruit_fair selectByPrimaryKey(Integer id)
	{
		String key = "Recruit_fair" + id;
		if (!redisService.hasKey(key)) {
			Recruit_fair Recruit_fair = Recruit_fairMapper.selectByPrimaryKey(id);
			redisService.set(key,Recruit_fair);
		}
		Recruit_fair result = (Recruit_fair)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Recruit_fair record)
	{
		redisService.delete("Recruit_fair"+record.getId());
		redisService.set("Recruit_fair"+record.getId(),record);
		return Recruit_fairMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Recruit_fair record)
	{
		redisService.delete("Recruit_fair"+record.getId());
		redisService.set("Recruit_fair"+record.getId(),record);
		return Recruit_fairMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Recruit_fair> selectAll() {
		return Recruit_fairMapper.selectAll();
	}
}
