package biubiu.Controller;

import biubiu.Model.Advertising_seat;
import biubiu.Service.Advertising_seatService;
import biubiu.Service.FirmService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
public class Advertising_seatController {
	@Autowired
	Advertising_seatService advertising_seatService;
	@Autowired
	FirmService firmService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllAdvertising_seat", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAdvertising_seat() {
		return "advertising_seat/AllAdvertising_seat";
	}

	@RequestMapping(value = "/gotoAddAdvertising_seat", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddAdvertising_seat(HttpSession session) {
		session.setAttribute("firms",firmService.selectAll());
		return "advertising_seat/addAdvertising_seat";
	}

	@RequestMapping(value = "/gotoUpdateAdvertising_seat", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateAdvertising_seat(HttpSession session) {
		session.setAttribute("firms",firmService.selectAll());
		return "advertising_seat/updateAdvertising_seat";
	}



	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllAdvertising_seat")
	public JSONArray getAllAdvertising_seat(@Param("page") int page, @Param("limit") int limit) {
		List<Advertising_seat> alldata = advertising_seatService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchAdvertising_seat")//查询
	public JSONArray searchAdvertising_seat(@Param("searchid") String searchid) {
		List<Advertising_seat> alldata = advertising_seatService.selectAll();
		if (searchid.compareTo("") == 0) {
			return JSONArray.parseArray(JSON.toJSONString(alldata));
		} else {
			List<Advertising_seat> result = new ArrayList<>();
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
	@RequestMapping("/delAdvertising_seat")//删除
	public JSONObject delAdvertising_seat(@Param("id") String id) {
		advertising_seatService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delAdvertising_seats")//批量删除
	public JSONObject delAdvertising_seats(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			advertising_seatService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addAdvertising_seat")//新添
	public JSONObject addAdvertising_seat(@Param("advertising_seat_num") String advertising_seat_num, @Param("firm_id") String firm_id, @Param("remain_time") String remain_time, @Param("state") String state, @Param("price") String price) {
//		System.out.println("code:"+code+"\nareaid:"+areaid+"\nfirmid:"+firmid+"\nstate:"+state+"\nprice:"+price);
		advertising_seatService.insert(new Advertising_seat(advertising_seat_num,firm_id,Integer.valueOf(remain_time),Integer.valueOf(price),state));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateAdvertising_seat")//更新
	public JSONObject updateAdvertising_seat(@Param("id") String id, @Param("advertising_seat_num") String advertising_seat_num, @Param("firm_id") String firm_id, @Param("remain_time") String remain_time, @Param("state") String state, @Param("price") String price) {
		advertising_seatService.updateByPrimaryKey(new Advertising_seat(Integer.valueOf(id), advertising_seat_num,firm_id,Integer.valueOf(remain_time), Integer.valueOf(price),state));
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
