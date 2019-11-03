package biubiu.Controller;

import biubiu.Model.Recruit_area;
import biubiu.Model.Recruit_seat;
import biubiu.Service.FirmService;
import biubiu.Service.Recruit_areaService;
import biubiu.Service.Recruit_seatService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Recruit_seatController {
	@Autowired
	Recruit_seatService recruit_seatService;
	@Autowired
	Recruit_areaService recruit_areaService;
	@Autowired
	FirmService firmService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllRecruit_seat", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoRecruit_seat() {
		return "recruit_seat/AllRecruit_seat";
	}

	@RequestMapping(value = "/gotoSearchseat", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoSearchseat(HttpServletRequest request, HttpSession session) {
		session.setAttribute("areaid",request.getParameter("areaid"));
		return "recruit_seat/AllRecruit_seat";
	}

	@RequestMapping(value = "/gotoAddRecruit_seat", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddRecruit_seat(HttpSession session) {
		session.setAttribute("areas",recruit_areaService.selectAll());
		session.setAttribute("firms",firmService.selectAll());
		return "recruit_seat/addRecruit_seat";
	}

	@RequestMapping(value = "/gotoUpdateRecruit_seat", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateRecruit_seat(HttpSession session) {
		session.setAttribute("areas",recruit_areaService.selectAll());
		session.setAttribute("firms",firmService.selectAll());
		return "recruit_seat/updateRecruit_seat";
	}



	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllRecruit_seat")
	public JSONArray getAllRecruit_seat(@Param("page") int page, @Param("limit") int limit) {
		List<Recruit_seat> alldata = recruit_seatService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchRecruit_seat")//查询
	public JSONArray searchRecruit_seat(@Param("searchid") String searchid) {
		List<Recruit_seat> alldata = recruit_seatService.selectAll();
		if (searchid.compareTo("") == 0) {
			return JSONArray.parseArray(JSON.toJSONString(alldata));
		} else {
			List<Recruit_seat> result = new ArrayList<>();
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
	@RequestMapping("/delRecruit_seat")//删除
	public JSONObject delRecruit_seat(@Param("id") String id) {
		recruit_seatService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delRecruit_seats")//批量删除
	public JSONObject delRecruit_seats(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			recruit_seatService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addRecruit_seat")//新添
	public JSONObject addRecruit_seat(@Param("code") String code, @Param("areaid") String areaid, @Param("firmid") String firmid, @Param("state") String state, @Param("price") String price) {
//		System.out.println("code:"+code+"\nareaid:"+areaid+"\nfirmid:"+firmid+"\nstate:"+state+"\nprice:"+price);
		recruit_seatService.insert(new Recruit_seat(code,Integer.valueOf(areaid),firmid,state,Integer.valueOf(price)));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateRecruit_seat")//更新
	public JSONObject updateRecruit_seat(@Param("id") String id, @Param("code") String code, @Param("areaid") String areaid, @Param("firmid") String firmid, @Param("state") String state, @Param("price") String price) {
			recruit_seatService.updateByPrimaryKey(new Recruit_seat(Integer.valueOf(id),code,Integer.valueOf(areaid),firmid,state,Integer.valueOf(price)));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}


	@ResponseBody
	@RequestMapping("/searchSeat")//根据招聘区域id查找所属招聘位
	public JSONArray searchSeat(@Param("areaid") String areaid) {
		System.out.println("按招聘区域id查询时："+areaid+"数据条数："+recruit_seatService.selectByAreaId(Integer.valueOf(areaid)).size());
		return JSONArray.parseArray(JSON.toJSONString(recruit_seatService.selectByAreaId(Integer.valueOf(areaid))));
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
