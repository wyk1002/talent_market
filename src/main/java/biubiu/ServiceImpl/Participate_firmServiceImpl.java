package biubiu.ServiceImpl;


import biubiu.Dao.Participate_firmMapper;
import biubiu.Model.Participate_firm;
import biubiu.Service.Participate_firmService;
import biubiu.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Participate_firmServiceImpl implements Participate_firmService {
	@Autowired
	biubiu.Dao.Participate_firmMapper Participate_firmMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Participate_firm"+id);
		return Participate_firmMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Participate_firm record)
	{
		String key="Participate_firm"+record.getId();
		redisService.set(key,record);
		return Participate_firmMapper.insert(record);
	}

	@Override
	public int insertSelective(Participate_firm record)
	{
		String key="Participate_firm"+record.getId();
		redisService.set(key,record);
		return Participate_firmMapper.insertSelective(record);
	}

	@Override
	public Participate_firm selectByPrimaryKey(Integer id)
	{
		String key = "Participate_firm" + id;
		if (!redisService.hasKey(key)) {
			Participate_firm Participate_firm = Participate_firmMapper.selectByPrimaryKey(id);
			redisService.set(key,Participate_firm);
		}
		Participate_firm result = (Participate_firm)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Participate_firm record)
	{
		redisService.delete("Participate_firm"+record.getId());
		redisService.set("Participate_firm"+record.getId(),record);
		return Participate_firmMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Participate_firm record)
	{
		redisService.delete("Participate_firm"+record.getId());
		redisService.set("Participate_firm"+record.getId(),record);
		return Participate_firmMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Participate_firm> selectAll() {
		return Participate_firmMapper.selectAll();
	}
}
