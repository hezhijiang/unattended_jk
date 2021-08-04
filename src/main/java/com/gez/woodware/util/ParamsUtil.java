package com.gez.woodware.util;


import com.gez.woodware.entity.basics.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ParamsUtil {


    public static HashMap<String, Object> getUserParam(HttpServletRequest request, Object param) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("user", (User) request.getSession().getAttribute("user"));
        map.put("param", param);

        return map;
    }

    public static User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }


    public static String getRandomFour() {
        return String.valueOf(Math.round((Math.random() + 1) * 1000));
    }


}
