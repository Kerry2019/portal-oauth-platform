package pers.kerry.portal.uaa.oauth2server.common;


import java.io.Serializable;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/10/11 10:42
 * @history: 1.2019/10/11 created by kerry.wu
 */
public class PageQueryResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected long count;
    protected List<T> result;

    public PageQueryResult() {
    }

    public PageQueryResult(long count,List<T> result){
        this.count=count;
        this.result=result;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}