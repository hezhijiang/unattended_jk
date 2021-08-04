package com.gez.woodware.service.basics;


import com.gez.woodware.entity.basics.CodeValue;
import com.gez.woodware.entity.basics.CodeValueDuolie;
import com.gez.woodware.mapper.basics.InitDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 初始化数据业务类.
 */
@Service
public class InitDataService {

    @Autowired
    private InitDataMapper initDataMapper;

    /**
     * 通用编码查询
     */
    public List<CodeValue> getCommonCodingList(String table) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("table", table);

        List<CodeValue> list = initDataMapper.getCommonCodingList(params);

        return list;
    }



    /**
     * 通用多列选择器查询
     */
    public List<CodeValueDuolie> getDuolieCodingList(String table) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("table", table);

        List<CodeValueDuolie> list = initDataMapper.getDuolieCodingList(params);

        return list;
    }



    /**
     * 待候选键
     */
    public List<CodeValue> getCommonCountList(String table, String field) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("table", table);
        params.put("field", field);

        List<CodeValue> list = initDataMapper.getCommonCountList(params);

        return list;
    }

    /**
     * 用户账号查询
     */
    public List<CodeValue> getUserAccountList() {
        List<CodeValue> list = initDataMapper.getUserAccountList();

        return list;
    }

    /**
     * 用户姓名查询
     */
    public List<CodeValue> getUserNameList() {
        List<CodeValue> list = initDataMapper.getUserNameList();

        return list;
    }

    /**
     * 部门查询
     */
    public List<CodeValue> getDepartmentNameList() {
        List<CodeValue> list = initDataMapper.getDepartmentNameList();

        return list;
    }

    /**
     * 角色查询
     */
    public List<CodeValue> getPartNameList() {
        List<CodeValue> list = initDataMapper.getPartNameList();

        return list;
    }
}
