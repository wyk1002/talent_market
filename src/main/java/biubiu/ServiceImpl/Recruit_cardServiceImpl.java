package biubiu.ServiceImpl;


import biubiu.Dao.Recruit_cardMapper;
import biubiu.Model.Recruit_card;
import biubiu.Service.Recruit_cardService;
import biubiu.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Recruit_cardServiceImpl implements Recruit_cardService {
	@Autowired
	Recruit_cardMapper Recruit_cardMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Recruit_card"+id);
		return Recruit_cardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Recruit_card record)
	{
		String key="Recruit_card"+record.getId();
		redisService.set(key,record);
		return Recruit_cardMapper.insert(record);
	}

	@Override
	public int insertSelective(Recruit_card record)
	{
		String key="Recruit_card"+record.getId();
		redisService.set(key,record);
		return Recruit_cardMapper.insertSelective(record);
	}

	@Override
	public Recruit_card selectByPrimaryKey(Integer id)
	{
		String key = "Recruit_card" + id;
		if (!redisService.hasKey(key)) {
			Recruit_card Recruit_card = Recruit_cardMapper.selectByPrimaryKey(id);
			redisService.set(key,Recruit_card);
		}
		Recruit_card result = (Recruit_card)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Recruit_card record)
	{
		redisService.delete("Recruit_card"+record.getId());
		redisService.set("Recruit_card"+record.getId(),record);
		return Recruit_cardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Recruit_card record)
	{
		redisService.delete("Recruit_card"+record.getId());
		redisService.set("Recruit_card"+record.getId(),record);
		return Recruit_cardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Recruit_card> selectAll() {
		return Recruit_cardMapper.selectAll();
	}

	@Override
	public List<Integer> selectByObject(String objectcategory, String objectid) {
		 return Recruit_cardMapper.selectByObject(objectcategory,objectid);
	}
}
