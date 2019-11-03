package biubiu.Controller;

import biubiu.Model.Firm;
import biubiu.Service.Advertising_seatService;
import biubiu.Service.FirmService;
import biubiu.Service.Recruit_cardService;
import biubiu.Service.Recruit_seatService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FirmController {
	@Autowired
	FirmService firmService;
	@Autowired
	Recruit_cardService recruit_cardService;
	@Autowired
	Advertising_seatService advertising_seatService;
	@Autowired
	Recruit_seatService recruit_seatService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllFirm", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoFirm() {
		return "firm/AllFirm";
	}

	@RequestMapping(value = "/gotoAddFirm", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddFirm() {
		return "firm/addFirm";
	}

	@RequestMapping(value = "/gotoUpdateFirm", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateFirm() {
		return "firm/updateFirm";
	}

	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllFirm")
	public JSONArray getAllFirm(@Param("page") int page, @Param("limit") int limit) {
		List<Firm> alldata = firmService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchFirm")//查询
	public JSONArray searchFirm(@Param("searchid") String searchid) {
		List<Firm> alldata = firmService.selectAll();
		if (searchid.compareTo("") == 0) {
			return JSONArray.parseArray(JSON.toJSONString(alldata));
		} else {
			List<Firm> result = new ArrayList<>();
			for (int i = 0; i < alldata.size(); i++) {
				if (searchid.compareTo("" + alldata.get(i).getId()) == 0) {
					result.add(alldata.get(i));
					break;
				}
			}
			return JSONArray.parseArray(JSON.toJSONString(result));
		}
	}

	@ResponseBody
	@RequestMapping("/delFirm")//删除
	public JSONObject delFirm(@Param("id") String id) {
		advertising_seatService.updateByFirmId(id);//将广告位设置为空
		recruit_seatService.updateByFirmId(id);//将招聘位设置为空
		List<Integer> tempcardid=recruit_cardService.selectByObject("企业",""+id);
		for (int i=0;i<tempcardid.size();i++){
			recruit_cardService.deleteByPrimaryKey(tempcardid.get(i));//将招聘卡删除
		}
		firmService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delFirms")//批量删除
	public JSONObject delFirms(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			advertising_seatService.updateByFirmId(data.get(i)+"");//删除广告位
			recruit_seatService.updateByFirmId(data.get(i)+"");//删除招聘位
			List<Integer> tempcardid=recruit_cardService.selectByObject("企业",""+data.get(i));
			for (int j=0;j<tempcardid.size();j++){
				recruit_cardService.deleteByPrimaryKey(tempcardid.get(j));//删除招聘卡
			}
			firmService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addFirm")//新添
	public JSONObject addFirm(@Param("name") String name,@Param("principal_name") String principal_name,@Param("principal_phone") String principal_phone) {
		firmService.insert(new Firm(name,principal_name,principal_phone));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateFirm")//更新
	public JSONObject updateFirm(@Param("id") String id, @Param("name") String name,@Param("principal_name") String principal_name,@Param("principal_phone") String principal_phone) {
//		System.out.println(id+"\n"+name+"\n"+time);
		firmService.updateByPrimaryKey(new Firm(Integer.valueOf(id), name,principal_name,principal_phone));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	//**********************************************工具方法****************************************
	public ArrayList<Integer> getArray(String datas) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		String temp = "";
		for (int i = 0; i < datas.length(); i++) {
			if (datas.charAt(i) != ',')
				temp += datas.charAt(i);
			else {
				result.add(Integer.valueOf(temp));
				temp = "";
			}
		}
		return result;
	}

}
