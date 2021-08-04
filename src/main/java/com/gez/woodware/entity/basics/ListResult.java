package com.gez.woodware.entity.basics;

public class ListResult {
    private PageResult pageDetail;

    private Object Data;

    public PageResult getPageDetail() {
        return pageDetail;
    }

    public void setPageDetail(PageResult pageDetail) {
        this.pageDetail = pageDetail;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}
