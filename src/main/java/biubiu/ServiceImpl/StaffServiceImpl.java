package biubiu.ServiceImpl;

import biubiu.Dao.StaffMapper;
import biubiu.Model.Staff;
import biubiu.Service.RedisService;
import biubiu.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	StaffMapper StaffMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Staff"+id);
		return StaffMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Staff record)
	{
		String key="Staff"+record.getId();
		redisService.set(key,record);
		return StaffMapper.insert(record);
	}

	@Override
	public int insertSelective(Staff record)
	{
		String key="Staff"+record.getId();
		redisService.set(key,record);
		return StaffMapper.insertSelective(record);
	}

	@Override
	public Staff selectByPrimaryKey(Integer id)
	{
		String key = "Staff" + id;
		if (!redisService.hasKey(key)) {
			Staff Staff = StaffMapper.selectByPrimaryKey(id);
			redisService.set(key,Staff);
		}
		Staff result = (Staff)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Staff record)
	{
		redisService.delete("Staff"+record.getId());
		redisService.set("Staff"+record.getId(),record);
		return StaffMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Staff record)
	{
		redisService.delete("Staff"+record.getId());
		redisService.set("Staff"+record.getId(),record);
		return StaffMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Staff> selectAll() {
		return StaffMapper.selectAll();
	}
}
