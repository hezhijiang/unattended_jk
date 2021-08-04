package com.gez.woodware.service.yund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.gez.woodware.util.*;
import com.gez.woodware.entity.*;
import com.gez.woodware.entity.basics.*;
import com.gez.woodware.entity.yund.*;
import com.gez.woodware.mapper.yund.*;

/**
 *  运单管理业务类.
 */
@Service
public class YundglService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private YundglMapper mapper;

	/**		分页表格预制件			
	 * 								
	 * @return list返回对象，里面包含返回的具体数据和分页信息								
	 * @explain   								
	 *								
	 */								
	public ListResult select(PageArgs param){								
									
		HashMap<String, Object> params =ParamsUtil.getUserParam(request,param);
		PageResult page = new PageResult();							
		page.setRecordCount(mapper.count(params));							
									
		//当有选中的记录的时候，需要返回选中行所在的页码数和在改页码的序号							
		if(param.getId()!=null && param.getId().length()==36){							
		  							
			param.setSelectionNumber(mapper.number(params));						
			page.setSelectionNumber(param.getSelectionNumber());						
	 		params.put("param", param);
		}							
									
		page.setPage(param.getPage());							
		page.setPageSize(param.getPageSize());							
		page.setStartNumber(param.getStartNumber());							
	 									
									
									
	 									
		ListResult retListResult = new ListResult();							
		retListResult.setPageDetail(page);							
									
		retListResult.setData(mapper.select(params));							
		return retListResult;							
	}								
									
									
 
}

