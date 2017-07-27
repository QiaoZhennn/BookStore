package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qiaoz on 2017/6/20.
 */
public class Page<T> implements Serializable{
    private static final long serialVersionUID = -7209251230625396094L;
    // 每页查询出来的记录组成的集合。
    private List<T> list;
    public static final int PAGE_SIZE=4;
    private int pageNo;
    private int totalPageNo;
    private int totalRecord;

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getList() {
        return list;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public int getPageNo() {
        if(pageNo<1){
            return 1;
        }else if(pageNo>getTotalPageNo()){
            return getTotalPageNo();
        }
        return pageNo;
    }

    public int getTotalPageNo() {
        if(totalRecord%PAGE_SIZE==0){
            return totalRecord/PAGE_SIZE;
        }else
        return totalRecord/PAGE_SIZE+1;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    @Override
    public String toString() {
        return "Page{" +
                "list=" + list +
                ", pageNo=" + pageNo +
                ", totalPageNo=" + totalPageNo +
                ", totalRecord=" + totalRecord +
                '}';
    }
}
