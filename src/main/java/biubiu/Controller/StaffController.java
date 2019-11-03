package biubiu.Controller;

import biubiu.Model.Staff;
import biubiu.Service.StaffService;
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
public class StaffController {
	@Autowired
	StaffService staffService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllStaff", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoStaff() {
		return "staff/AllStaff";
	}

	@RequestMapping(value = "/gotoAddStaff", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddStaff() {
		return "staff/addStaff";
	}

	@RequestMapping(value = "/gotoUpdateStaff", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateStaff() {
		return "staff/updateStaff";
	}

	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllStaff")
	public JSONArray getAllStaff(@Param("page") int page, @Param("limit") int limit) {
		List<Staff> alldata = staffService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchStaff")//查询
	public JSONArray searchStaff(@Param("searchid") String searchid) {
		List<Staff> alldata = staffService.selectAll();
		if (searchid.compareTo("") == 0) {
			return JSONArray.parseArray(JSON.toJSONString(alldata));
		} else {
			List<Staff> result = new ArrayList<>();
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
	@RequestMapping("/delStaff")//删除
	public JSONObject delStaff(@Param("id") String id) {
		staffService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delStaffs")//批量删除
	public JSONObject delStaffs(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			staffService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addStaff")//新添
	public JSONObject addStaff(@Param("name") String name,@Param("phone") String phone,@Param("position") String position) {
		staffService.insert(new Staff(name,phone,position));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateStaff")//更新
	public JSONObject updateStaff(@Param("id") String id, @Param("name") String name,@Param("phone") String phone,@Param("position") String position) {
//		System.out.println(id+"\n"+name+"\n"+time);
		staffService.updateByPrimaryKey(new Staff(Integer.valueOf(id), name,phone,position));
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
