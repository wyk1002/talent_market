package biubiu.ServiceImpl;


import biubiu.Dao.FirmMapper;
import biubiu.Model.Firm;
import biubiu.Service.FirmService;
import biubiu.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmServiceImpl implements FirmService
{
	@Autowired
	FirmMapper FirmMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Firm"+id);
		return FirmMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Firm record)
	{
		String key="Firm"+record.getId();
		redisService.set(key,record);
		return FirmMapper.insert(record);
	}

	@Override
	public int insertSelective(Firm record)
	{
		String key="Firm"+record.getId();
		redisService.set(key,record);
		return FirmMapper.insertSelective(record);
	}

	@Override
	public Firm selectByPrimaryKey(Integer id)
	{
		String key = "Firm" + id;
		if (!redisService.hasKey(key)) {
			Firm Firm = FirmMapper.selectByPrimaryKey(id);
			redisService.set(key,Firm);
		}
		Firm result = (Firm)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Firm record)
	{
		redisService.delete("Firm"+record.getId());
		redisService.set("Firm"+record.getId(),record);
		return FirmMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Firm record)
	{
		redisService.delete("Firm"+record.getId());
		redisService.set("Firm"+record.getId(),record);
		return FirmMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Firm> selectAll() {
		return FirmMapper.selectAll();
	}
}
