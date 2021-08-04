package com.gez.woodware.util.baidu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gez.woodware.util.DzHttpRequest;
import com.gez.woodware.util.Point;

/**
 * 集成百度路径规划
 * 
 * 地址：http://lbsyun.baidu.com/index.php?title=webapi/direction-api-v2
 * 
 * @author 何志江
 * @version 1.0
 *
 */
public class RoutePlanning {

	private static String ak = "FHN7bmTIPZPYkW0HC54GfAm3sWQjieZS";

	
	
	private static String getPoint(String qid) {
		
		String[] qids = qid.split(",");
		return  qids[1]+","+qids[0];
	}

	public static RetRoutePlanning ridingjwd(String origin, String destination) {
		String qid =getPoint(origin);
		String zongd =getPoint(destination);
		
		return riding(qid,zongd);
	}
	
	/**
	 * 起点，终点  纬度 经度  注意位置
	 * 
	 * 何志江
	 * 2020年6月5日
	 * @param origin
	 * @param destination
	 * @return
	 */
	public static RetRoutePlanning riding(String origin, String destination) {
//		骑行路线规划
//		http://api.map.baidu.com/direction/v2/riding?origin=40.01116,116.339303&destination=39.936404,116.452562&ak=您的AK   //GET请求

		String url = "http://api.map.baidu.com/direction/v2/riding";

		Map<String, String> params = new HashMap<String, String>();
		params.put("ak", ak);
		params.put("origin", origin);
		params.put("destination", destination);		
		params.put("coord_type", "gcj02");
		params.put("ret_coordtype", "gcj02");
		params.put("riding_type", "1"); // 默认0：0-普通  1-电动车
		

		String ret = DzHttpRequest.sendGetParam(url, params);

		return ridingRead(ret);

	}

	private static RetRoutePlanning ridingRead(String res) {

		RetRoutePlanning ret = new RetRoutePlanning();
		List<String> steps = new ArrayList<String>();
		List<Object> stepsOri = new ArrayList<Object>();
		BaiduPoint p = null;
		String point = "123";
		if (JSON.parseObject(res).getString("status").equals("0")) {
			// 接口调通了，判断状态state，获取列表

			/*
			 * 返回状态state说明： 0：在途，即货物处于运输过程中； 1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息； 2：疑难，货物寄送过程出了问题；
			 * 3：签收，收件人已签收； 4：退签或异常签收，即货物由于用户拒签、超区等原因退回，而且发件人已经签收； 5：派件，即快递正在进行同城派件；
			 * 6：退回，货物正处于退回发件人的途中；
			 */
			String result = JSON.parseObject(res).getString("result");
			String routes = JSON.parseObject(result).getString("routes");

			List<Routes> routesList = JSON.parseArray(routes, Routes.class);

			for (Routes route : routesList) {

				ret.setDistance(route.getDistance());
				ret.setDuration(route.getDuration());

				List<Steps> stepList = JSON.parseArray(route.getSteps(), Steps.class);
				for (Steps step : stepList) {
					String[] paths = step.getPath().split(";");
//					stepsOri.add(    );
					  p = (BaiduPoint)JSONObject.parseObject(step.getStepOriginLocation().toString(), BaiduPoint.class); 
					stepsOri.add(p.getPoint());
					p=null;
					  p = (BaiduPoint)JSONObject.parseObject(step.getStepDestinationLocation().toString(), BaiduPoint.class); 
						stepsOri.add(p.getPoint());
						p=null;
					for (int i = 0; i < paths.length; i++) {

						if (!point.equals(paths[i])) {
							steps.add(paths[i]);
						}
						point = paths[i];

					}
				}

			}
//		 
			ret.setStepsOri(stepsOri);
			ret.setStepsList(steps);

		}
		return ret;

	}

	 
}
