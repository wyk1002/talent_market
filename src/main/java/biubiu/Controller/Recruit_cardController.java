package biubiu.Controller;

import biubiu.Model.Recruit_card;
import biubiu.Service.FirmService;
import biubiu.Service.Job_seekerService;
import biubiu.Service.Recruit_cardService;
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
public class Recruit_cardController {
	@Autowired
	Recruit_cardService recruit_cardService;
	@Autowired
	FirmService firmService;
	@Autowired
	Job_seekerService jobSeekerService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllRecruit_card", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoRecruit_card() {
		return "recruit_card/AllRecruit_card";
	}

	@RequestMapping(value = "/gotoAddRecruit_card", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddRecruit_card(HttpSession session) {
		session.setAttribute("seekers",jobSeekerService.selectAll());
		session.setAttribute("firms",firmService.selectAll());
		return "recruit_card/addRecruit_card";
	}

	@RequestMapping(value = "/gotoUpdateRecruit_card", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateRecruit_card(HttpSession session) {
		session.setAttribute("seekers",jobSeekerService.selectAll());
		session.setAttribute("firms",firmService.selectAll());
		return "recruit_card/updateRecruit_card";
	}

	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllRecruit_card")
	public JSONArray getAllRecruit_card(@Param("page") int page, @Param("limit") int limit) {
		List<Recruit_card> alldata = recruit_cardService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchRecruit_card")//查询
	public JSONArray searchRecruit_card(@Param("searchid") String searchid) {
		List<Recruit_card> alldata = recruit_cardService.selectAll();
		if (searchid.compareTo("") == 0) {
			return JSONArray.parseArray(JSON.toJSONString(alldata));
		} else {
			List<Recruit_card> result = new ArrayList<>();
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
	@RequestMapping("/delRecruit_card")//删除
	public JSONObject delRecruit_card(@Param("id") String id) {
		recruit_cardService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delRecruit_cards")//批量删除
	public JSONObject delRecruit_cards(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			recruit_cardService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addRecruit_card")//新添
	public JSONObject addRecruit_card(@Param("object_id") String object_id,@Param("object_category") String object_category,@Param("remain_money") String remain_money) {
		recruit_cardService.insert(new Recruit_card(object_id,object_category,Integer.valueOf(remain_money)));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateRecruit_card")//更新
	public JSONObject updateRecruit_card(@Param("id") String id, @Param("object_id") String object_id,@Param("object_category") String object_category,@Param("remain_money") String remain_money) {
//		System.out.println(id+"\n"+object_id+"\n"+object_category+"\n"+remain_money);
		recruit_cardService.updateByPrimaryKey(new Recruit_card(Integer.valueOf(id),object_id,object_category,Integer.valueOf(remain_money)));
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
