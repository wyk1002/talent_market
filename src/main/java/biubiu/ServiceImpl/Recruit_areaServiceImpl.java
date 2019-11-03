package biubiu.ServiceImpl;

import biubiu.Dao.Recruit_areaMapper;
import biubiu.Model.Recruit_area;
import biubiu.Service.Recruit_areaService;
import biubiu.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Recruit_areaServiceImpl implements Recruit_areaService {
	@Autowired
	Recruit_areaMapper Recruit_areaMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Recruit_area"+id);
		return Recruit_areaMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Recruit_area record)
	{
		String key="Recruit_area"+record.getId();
		redisService.set(key,record);
		return Recruit_areaMapper.insert(record);
	}

	@Override
	public int insertSelective(Recruit_area record)
	{
		String key="Recruit_area"+record.getId();
		redisService.set(key,record);
		return Recruit_areaMapper.insertSelective(record);
	}

	@Override
	public Recruit_area selectByPrimaryKey(Integer id)
	{
		String key = "Recruit_area" + id;
		if (!redisService.hasKey(key)) {
			Recruit_area Recruit_area = Recruit_areaMapper.selectByPrimaryKey(id);
			redisService.set(key,Recruit_area);
		}
		Recruit_area result = (Recruit_area)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Recruit_area record)
	{
		redisService.delete("Recruit_area"+record.getId());
		redisService.set("Recruit_area"+record.getId(),record);
		return Recruit_areaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Recruit_area record)
	{
		redisService.delete("Recruit_area"+record.getId());
		redisService.set("Recruit_area"+record.getId(),record);
		return Recruit_areaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Recruit_area> selectAll() {
		return Recruit_areaMapper.selectAll();
	}
}
