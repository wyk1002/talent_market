package biubiu.ServiceImpl;


import biubiu.Dao.InvoiceMapper;
import biubiu.Model.Invoice;
import biubiu.Service.InvoiceService;
import biubiu.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	InvoiceMapper InvoiceMapper;
	@Autowired
	RedisService redisService;

	@Override
	public int deleteByPrimaryKey(Integer id)
	{
		redisService.delete("Invoice"+id);
		return InvoiceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Invoice record)
	{
		String key="Invoice"+record.getId();
		redisService.set(key,record);
		return InvoiceMapper.insert(record);
	}

	@Override
	public int insertSelective(Invoice record)
	{
		String key="Invoice"+record.getId();
		redisService.set(key,record);
		return InvoiceMapper.insertSelective(record);
	}

	@Override
	public Invoice selectByPrimaryKey(Integer id)
	{
		String key = "Invoice" + id;
		if (!redisService.hasKey(key)) {
			Invoice Invoice = InvoiceMapper.selectByPrimaryKey(id);
			redisService.set(key,Invoice);
		}
		Invoice result = (Invoice)redisService.get(key);
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(Invoice record)
	{
		redisService.delete("Invoice"+record.getId());
		redisService.set("Invoice"+record.getId(),record);
		return InvoiceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Invoice record)
	{
		redisService.delete("Invoice"+record.getId());
		redisService.set("Invoice"+record.getId(),record);
		return InvoiceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Invoice> selectAll() {
		return InvoiceMapper.selectAll();
	}
}
