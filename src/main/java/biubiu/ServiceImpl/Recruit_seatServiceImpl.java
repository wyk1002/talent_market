package biubiu.ServiceImpl;


import biubiu.Dao.Recruit_seatMapper;
import biubiu.Model.Recruit_seat;
import biubiu.Service.Recruit_seatService;
import biubiu.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Recruit_seatServiceImpl implements Recruit_seatService {
	@Autowired
	Recruit_seatMapper Recruit_seatMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Recruit_seat"+id);
		return Recruit_seatMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Recruit_seat record)
	{
		String key="Recruit_seat"+record.getId();
		redisService.set(key,record);
		return Recruit_seatMapper.insert(record);
	}

	@Override
	public int insertSelective(Recruit_seat record)
	{
		String key="Recruit_seat"+record.getId();
		redisService.set(key,record);
		return Recruit_seatMapper.insertSelective(record);
	}

	@Override
	public Recruit_seat selectByPrimaryKey(Integer id)
	{
		String key = "Recruit_seat" + id;
		if (!redisService.hasKey(key)) {
			Recruit_seat Recruit_seat = Recruit_seatMapper.selectByPrimaryKey(id);
			redisService.set(key,Recruit_seat);
		}
		Recruit_seat result = (Recruit_seat)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Recruit_seat record)
	{
		redisService.delete("Recruit_seat"+record.getId());
		redisService.set("Recruit_seat"+record.getId(),record);
		return Recruit_seatMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Recruit_seat record)
	{
		redisService.delete("Recruit_seat"+record.getId());
		redisService.set("Recruit_seat"+record.getId(),record);
		return Recruit_seatMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Recruit_seat> selectAll() {
		return Recruit_seatMapper.selectAll();
	}

	@Override
	public List<Recruit_seat> selectByAreaId(Integer recruit_area_id) {
		return Recruit_seatMapper.selectByAreaId(recruit_area_id);
	}

	@Override
	public void updateByFirmId(String firmid) {
		Recruit_seatMapper.updateByFirmId(firmid);
	}
}
