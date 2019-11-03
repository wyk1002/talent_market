package biubiu.Controller;

import biubiu.Model.Job_seeker;
import biubiu.Service.Advertising_seatService;
import biubiu.Service.Job_seekerService;
import biubiu.Service.Recruit_areaService;
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
public class Job_seekerController {
	@Autowired
	Job_seekerService job_seekerService;
	@Autowired
	Recruit_cardService recruit_cardService;
	@Autowired
	Recruit_areaService recruit_areaService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllJob_seeker", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoJob_seeker() {
		return "job_seeker/AllJob_seeker";
	}

	@RequestMapping(value = "/gotoAddJob_seeker", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddJob_seeker(HttpSession session) {
		session.setAttribute("seekers",recruit_areaService.selectAll());
		return "job_seeker/addJob_seeker";
	}

	@RequestMapping(value = "/gotoUpdateJob_seeker", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateJob_seeker(HttpSession session) {
		session.setAttribute("seekers",recruit_areaService.selectAll());
		return "job_seeker/updateJob_seeker";
	}

	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllJob_seeker")
	public JSONArray getAllJob_seeker(@Param("page") int page, @Param("limit") int limit) {
		List<Job_seeker> alldata = job_seekerService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchJob_seeker")//查询
	public JSONArray searchJob_seeker(@Param("searchid") String searchid) {
		List<Job_seeker> alldata = job_seekerService.selectAll();
		if (searchid.compareTo("") == 0) {
			return JSONArray.parseArray(JSON.toJSONString(alldata));
		} else {
			List<Job_seeker> result = new ArrayList<>();
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
	@RequestMapping("/delJob_seeker")//删除
	public JSONObject delJob_seeker(@Param("id") String id) {
		List<Integer> tempcardid=recruit_cardService.selectByObject("个人",""+id);
		for (int i=0;i<tempcardid.size();i++){
			recruit_cardService.deleteByPrimaryKey(tempcardid.get(i));
		}
		job_seekerService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delJob_seekers")//批量删除
	public JSONObject delJob_seekers(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			List<Integer> tempcardid=recruit_cardService.selectByObject("个人",""+data.get(i));
			for (int j=0;j<tempcardid.size();j++){
				recruit_cardService.deleteByPrimaryKey(tempcardid.get(j));
			}
			job_seekerService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addJob_seeker")//新添
	public JSONObject addJob_seeker(@Param("name") String name,@Param("phone") String phone,@Param("job_purpose") String job_purpose) {
		job_seekerService.insert(new Job_seeker(name,phone,Integer.valueOf(job_purpose)));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateJob_seeker")//更新
	public JSONObject updateJob_seeker(@Param("id") String id, @Param("name") String name,@Param("phone") String phone,@Param("job_purpose") String job_purpose) {
//		System.out.println(id+"\n"+name+"\n"+time);
		job_seekerService.updateByPrimaryKey(new Job_seeker(Integer.valueOf(id), name,phone,Integer.valueOf(job_purpose)));
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
