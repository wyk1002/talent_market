package biubiu.Controller;

import biubiu.Model.Participate_firm;
import biubiu.Service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Participate_firmController {
	@Autowired
	Participate_firmService participate_firmService;
	@Autowired
	FirmService firmService;
	@Autowired
	Recruit_fairService recruit_fairService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllParticipate_firm", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoParticipate_firm() {
		return "participate_firm/AllParticipate_firm";
	}

	@RequestMapping(value = "/gotoAddParticipate_firm", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddParticipate_firm(HttpSession session) {
		session.setAttribute("firms",firmService.selectAll());
		session.setAttribute("fairs",recruit_fairService.selectAll());
		return "participate_firm/addParticipate_firm";
	}

	@RequestMapping(value = "/gotoUpdateParticipate_firm", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateParticipate_firm(HttpSession session) {
		session.setAttribute("firms",firmService.selectAll());
		session.setAttribute("fairs",recruit_fairService.selectAll());
		return "participate_firm/updateParticipate_firm";
	}

	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllParticipate_firm")
	public JSONArray getAllParticipate_firm(@Param("page") int page, @Param("limit") int limit) {
		List<Participate_firm> alldata = participate_firmService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchParticipate_firm")//查询
	public JSONArray searchParticipate_firm(@Param("searchfirmid") String searchfirmid,@Param("searchfairid") String searchfairid) {
		List<Participate_firm> alldata = participate_firmService.selectAll();
		List<Participate_firm> result=new ArrayList<Participate_firm>();
		if (searchfirmid.compareTo("") == 0) {
			result.addAll(alldata);
		}
		else{
			for (int i=0;i<alldata.size();i++){
				if (searchfirmid.compareTo(alldata.get(i).getFirmId()+"")==0)
					result.add(alldata.get(i));
			}
		}
		alldata.clear();
		if (searchfairid.compareTo("") == 0) {
			alldata.addAll(result);
		}
		else{
			for (int i=0;i<result.size();i++){
				if (searchfairid.compareTo(result.get(i).getRecruitFairId()+"")==0)
					alldata.add(result.get(i));
			}
		}
		return JSONArray.parseArray(JSON.toJSONString(alldata));
	}

	@ResponseBody
	@RequestMapping("/delParticipate_firm")//删除
	public JSONObject delParticipate_firm(@Param("id") String id) {
		participate_firmService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delParticipate_firms")//批量删除
	public JSONObject delParticipate_firms(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			participate_firmService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addParticipate_firm")//新添
	public JSONObject addParticipate_firm(@Param("firm_id") String firm_id,@Param("recruit_fair_id") String recruit_fair_id) {
		participate_firmService.insert(new Participate_firm(Integer.valueOf(firm_id),Integer.valueOf(recruit_fair_id)));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateParticipate_firm")//更新
	public JSONObject updateParticipate_firm(@Param("id") String id, @Param("firm_id") String firm_id,@Param("recruit_fair_id") String recruit_fair_id) {
//		System.out.println(id+"\n"+name+"\n"+time);
		participate_firmService.updateByPrimaryKey(new Participate_firm(Integer.valueOf(id), Integer.valueOf(firm_id),Integer.valueOf(recruit_fair_id)));
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
