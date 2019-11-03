package biubiu.Controller;

import biubiu.Model.Invoice;
import biubiu.Service.FirmService;
import biubiu.Service.Job_seekerService;
import biubiu.Service.InvoiceService;
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
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	FirmService firmService;
	@Autowired
	Job_seekerService jobSeekerService;


	//******************************以下是页面跳转的方法***************************************
	@RequestMapping(value = "/AllInvoice", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoInvoice() {
		return "invoice/AllInvoice";
	}

	@RequestMapping(value = "/gotoAddInvoice", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoAddInvoice(HttpSession session) {
		session.setAttribute("seekers",jobSeekerService.selectAll());
		session.setAttribute("firms",firmService.selectAll());
		return "invoice/addInvoice";
	}

	@RequestMapping(value = "/gotoUpdateInvoice", method = {RequestMethod.POST, RequestMethod.GET})
	public String gotoUpdateInvoice(HttpSession session) {
		session.setAttribute("seekers",jobSeekerService.selectAll());
		session.setAttribute("firms",firmService.selectAll());
		return "invoice/updateInvoice";
	}

	//******************************以下是ajax传参调用的方法************************************
	@ResponseBody
	@RequestMapping("/getAllInvoice")
	public JSONArray getAllInvoice(@Param("page") int page, @Param("limit") int limit) {
		List<Invoice> alldata = invoiceService.selectAll();
		String json = JSON.toJSONString(alldata);
		return JSONArray.parseArray(json);
	}

	@ResponseBody
	@RequestMapping("/searchInvoice")//查询
	public JSONArray searchInvoice(@Param("searchid") String searchid,@Param("searchObject") String searchObject,@Param("objectid") String objectid) {
		List<Invoice> alldata = invoiceService.selectAll();
		List<Invoice> result = new ArrayList<>();
//		System.out.println("searchid:"+searchid+"\nsearchObject:"+searchObject+"\nobjectid:"+objectid);
		if (searchid.compareTo("") == 0) {
			result.addAll(alldata);
		} else {
			for (int i = 0; i < alldata.size(); i++) {
				if (searchid.compareTo("" + alldata.get(i).getId()) == 0) {
					result.add(alldata.get(i));
					break;
				}
			}
		}
		alldata.clear();

		if (searchObject.compareTo("所有")==0){
			alldata.addAll(result);
		}
		else {
			for (int i=0;i<result.size();i++){
				if (result.get(i).getObjectCategory().compareTo(searchObject)==0)
					alldata.add(result.get(i));
			}
		}
		result.clear();

		if (objectid.compareTo("")==0){
			result.addAll(alldata);
		}
		else {
			for (int i=0;i<alldata.size();i++){
				if (alldata.get(i).getObjectId().compareTo(objectid)==0)
					result.add(alldata.get(i));
			}
		}
		return JSONArray.parseArray(JSON.toJSONString(result));
	}

	@ResponseBody
	@RequestMapping("/delInvoice")//删除
	public JSONObject delInvoice(@Param("id") String id) {
		invoiceService.deleteByPrimaryKey(Integer.valueOf(id));
		return JSONObject.parseObject("{code : " + 1 + " }");//传给前台js的一个参数判断删除的状态
	}

	@ResponseBody
	@RequestMapping("/delInvoices")//批量删除
	public JSONObject delInvoices(@Param("datas") String datas) {
		ArrayList<Integer> data = getArray(datas);
		for (int i = 0; i < data.size(); i++) {
			invoiceService.deleteByPrimaryKey(data.get(i));
		}
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/addInvoice")//新添
	public JSONObject addInvoice(@Param("object_id") String object_id,@Param("object_category") String object_category,@Param("bargain_money") String bargain_money,@Param("bargain_explain") String bargain_explain) {
		invoiceService.insert(new Invoice(object_id,Integer.valueOf(bargain_money),bargain_explain,object_category));
		return JSONArray.parseObject("{code : " + 1 + " }");//传给前台js的一个参数状态
	}

	@ResponseBody
	@RequestMapping("/updateInvoice")//更新
	public JSONObject updateInvoice(@Param("id") String id, @Param("object_id") String object_id,@Param("object_category") String object_category,@Param("bargain_money") String bargain_money,@Param("bargain_explain") String bargain_explain) {
//		System.out.println(id+"\n"+object_id+"\n"+object_category+"\n"+remain_money);
		invoiceService.updateByPrimaryKey(new Invoice(Integer.valueOf(id),object_id,Integer.valueOf(bargain_money),bargain_explain,object_category));
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
