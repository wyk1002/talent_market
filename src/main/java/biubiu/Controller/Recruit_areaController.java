package biubiu.Controller;

import biubiu.Model.Recruit_area;
import biubiu.Service.Recruit_areaService;
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
public class Recruit_areaController {
	@Autowired
	Recruit_areaService recruit_areaService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllRecruit_area", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoRecruit_area() {
		return "recruit_area/AllRecruit_area";
	}

	@RequestMapping(value = "/gotoAddRecruit_area", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddRecruit_area() {
		return "recruit_area/addRecruit_area";
	}

	@RequestMapping(value = "/gotoUpdateRecruit_area", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateRecruit_area() {
		return "recruit_area/updateRecruit_area";
	}

	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllRecruit_area")
	public JSONArray getAllRecruit_area(@Param("page") int page, @Param("limit") int limit) {
		List<Recruit_area> alldata = recruit_areaService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchRecruit_area")//查询
	public JSONArray searchRecruit_area(@Param("searchid") String searchid) {
		List<Recruit_area> alldata = recruit_areaService.selectAll();
		if (searchid.compareTo("") == 0) {
			return JSONArray.parseArray(JSON.toJSONString(alldata));
		} else {
			List<Recruit_area> result = new ArrayList<>();
//			System.out.println("searchID:" + searchid);
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
	@RequestMapping("/delRecruit_area")//删除
	public JSONObject delRecruit_area(@Param("id") String id) {
		recruit_areaService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delRecruit_areas")//批量删除
	public JSONObject delRecruit_areas(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			recruit_areaService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addRecruit_area")//新添
	public JSONObject addRecruit_area(@Param("name") String name) {
		recruit_areaService.insert(new Recruit_area(name));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateRecruit_area")//更新
	public JSONObject updateRecruit_area(@Param("id") String id, @Param("name") String name) {
		recruit_areaService.updateByPrimaryKey(new Recruit_area(Integer.valueOf(id), name));
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
