package biubiu.ServiceImpl;


import biubiu.Dao.Advertising_seatMapper;
import biubiu.Model.Advertising_seat;
import biubiu.Service.Advertising_seatService;
import biubiu.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Advertising_seatServicerImpl implements Advertising_seatService {
	@Autowired
	Advertising_seatMapper advertising_seatMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Advertising_seat"+id);
		return advertising_seatMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Advertising_seat record)
	{
		String key="Advertising_seat"+record.getId();
		redisService.set(key,record);
		return advertising_seatMapper.insert(record);
	}

	@Override
	public int insertSelective(Advertising_seat record)
	{
		String key="Advertising_seat"+record.getId();
		redisService.set(key,record);
		return advertising_seatMapper.insertSelective(record);
	}

	@Override
	public Advertising_seat selectByPrimaryKey(Integer id)
	{
		String key = "Advertising_seat" + id;
		if (!redisService.hasKey(key)) {
			Advertising_seat advertising_seat = advertising_seatMapper.selectByPrimaryKey(id);
			redisService.set(key,advertising_seat);
		}
		Advertising_seat result = (Advertising_seat)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Advertising_seat record)
	{
		redisService.delete("Advertising_seat"+record.getId());
		redisService.set("Advertising_seat"+record.getId(),record);
		return advertising_seatMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Advertising_seat record)
	{
		redisService.delete("Advertising_seat"+record.getId());
		redisService.set("Advertising_seat"+record.getId(),record);
		return advertising_seatMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Advertising_seat> selectAll() {
		return advertising_seatMapper.selectAll();
	}

	@Override
	public void updateByFirmId(String firmid) {
		advertising_seatMapper.updateByFirmId(firmid);
	}
}
