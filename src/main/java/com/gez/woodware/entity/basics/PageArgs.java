package com.gez.woodware.entity.basics;



public class PageArgs{


    private String id;

    private String searchKey;

    private int page = 1;

    private int pageSize = 10;

    // 本页开始的序号
    private int startNumber = -1;

    // 选中行数
    private int selectionNumber;

    /**
     * 排序列.
     */
    private String column;

    /**
     * 是否顺序.
     */
    private boolean asc;

    /**
     * 表达式
     */
    private String expr = "";

    /**
     * 构造函数，设置pagesize
     *
     * @param pageSize
     */
    public PageArgs(int pageSize) {
        this.pageSize = pageSize;

    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public PageArgs() {
        this.pageSize = 10;

    }

    public int getSelectionNumber() {
        return selectionNumber;
    }

    public void setSelectionNumber(int selectionNumber) {

        int tiSelectIndex = 1;
        int tiSelectPage = 1;

        if (selectionNumber % pageSize == 0) {
            tiSelectPage = selectionNumber / pageSize;
            tiSelectIndex = pageSize;
        } else {
            tiSelectPage = selectionNumber / pageSize + 1;
            tiSelectIndex = selectionNumber % pageSize;
        }

        this.page = tiSelectPage;
        this.selectionNumber = tiSelectIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String getExpr() {
        if (column != null && !"".equals(column)) {
            expr = column + (asc ? " asc" : " desc");
        }

        return expr;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 1) {
            page = 1;
        }

        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartNumber() {
        this.startNumber = (page - 1) * pageSize;
        return startNumber;

    }
}
