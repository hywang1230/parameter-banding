package name.wangrong.pojo;

import name.wangrong.annotation.ParamName;

/**
 * Created by wangrong on 15/12/24.
 */
public class Request {
    @ParamName("api_name")
    private String apiName;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
