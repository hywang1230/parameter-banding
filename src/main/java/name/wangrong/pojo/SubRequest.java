package name.wangrong.pojo;

import name.wangrong.annotation.ParamName;

import java.util.Date;

/**
 * Created by wangrong on 15/12/30.
 */
public class SubRequest extends Request {
    @ParamName("api_date")
    private Date apiDate;

    public Date getApiDate() {
        return apiDate;
    }

    public void setApiDate(Date apiDate) {
        this.apiDate = apiDate;
    }
}
