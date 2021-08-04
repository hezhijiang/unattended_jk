package com.gez.woodware.entity.basics;


import lombok.Data;

/**
 * 分页结果.
 *
 */
@Data
public class PageResult extends Object{

    // 当前页码
    private int page;
    private int current;



    // 每页的大小，显示的记录数
    private int pageSize;

    // 总的页数
    private int pageCount;

    // 总的记录数
    private int recordCount;

    // 选中的序号
    private int selectionNumber;

    // 本页开始的序号
    private int startNumber;

    // 本页结束的序号
    private int endNumber;


    public int getCurrent() {
        current = this.page;
        return current;
    }

    public void setPageSize(int pageSize) {

        if (page * pageSize < recordCount) {
            this.endNumber= page * pageSize;
        }else{
            this.endNumber= recordCount;
        }



        this.startNumber=(page - 1) * pageSize + 1;
        this.pageSize = pageSize;

    }

    public int getPageCount() {

        if (recordCount % pageSize == 0) {
            pageCount = recordCount / pageSize;
        } else {
            pageCount = recordCount / pageSize + 1;
        }
        return pageCount;
    }




    public int getEndNumber() {
        if (page * pageSize < recordCount) {
            return page * pageSize;
        }else{
            return recordCount;
        }

    }

}
