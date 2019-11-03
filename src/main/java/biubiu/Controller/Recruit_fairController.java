package biubiu.Controller;

import biubiu.Model.Recruit_fair;
import biubiu.Service.Recruit_fairService;
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
public class Recruit_fairController {
	@Autowired
	Recruit_fairService recruit_fairService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllRecruit_fair", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoRecruit_fair() {
		return "recruit_fair/AllRecruit_fair";
	}

	@RequestMapping(value = "/gotoAddRecruit_fair", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddRecruit_fair() {
		return "recruit_fair/addRecruit_fair";
	}

	@RequestMapping(value = "/gotoUpdateRecruit_fair", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateRecruit_fair() {
		return "recruit_fair/updateRecruit_fair";
	}

	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllRecruit_fair")
	public JSONArray getAllRecruit_fair(@Param("page") int page, @Param("limit") int limit) {
		List<Recruit_fair> alldata = recruit_fairService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchRecruit_fair")//查询
	public JSONArray searchRecruit_fair(@Param("searchid") String searchid) {
		List<Recruit_fair> alldata = recruit_fairService.selectAll();
		if (searchid.compareTo("") == 0) {
			return JSONArray.parseArray(JSON.toJSONString(alldata));
		} else {
			List<Recruit_fair> result = new ArrayList<>();
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
	@RequestMapping("/delRecruit_fair")//删除
	public JSONObject delRecruit_fair(@Param("id") String id) {
		recruit_fairService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delRecruit_fairs")//批量删除
	public JSONObject delRecruit_fairs(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			recruit_fairService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addRecruit_fair")//新添
	public JSONObject addRecruit_fair(@Param("name") String name,@Param("time") String time) {
		recruit_fairService.insert(new Recruit_fair(name,time));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateRecruit_fair")//更新
	public JSONObject updateRecruit_fair(@Param("id") String id, @Param("name") String name,@Param("time") String time) {
//		System.out.println(id+"\n"+name+"\n"+time);
		recruit_fairService.updateByPrimaryKey(new Recruit_fair(Integer.valueOf(id), name,time));
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
