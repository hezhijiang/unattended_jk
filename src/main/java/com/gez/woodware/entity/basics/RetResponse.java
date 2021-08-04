package com.gez.woodware.entity.basics;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class RetResponse implements Serializable {


    private static final long serialVersionUID = -4505655308965878999L;


    private boolean retcode=true;
    private Object retval;

    public RetResponse(){
        retval="成功";
    }
    public RetResponse(Object retVal){
        this.retval = retVal;

    }

    public  RetResponse(boolean retCode,Object retVal){
        this.retcode=retCode;

        if(retVal==null){
            this.retval = "失败";
        }else{
            this.retval = retVal;
        }


    }


}
