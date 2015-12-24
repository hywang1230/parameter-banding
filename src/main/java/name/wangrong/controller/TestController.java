package name.wangrong.controller;

import name.wangrong.pojo.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangrong on 15/12/24.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/test_param_binding")
    public @ResponseBody String testParamBinding(Request request) {
        return request.getApiName();
    }

    @RequestMapping("/test_common_param")
    public @ResponseBody String testCommonParam(String apiName) {
        return apiName;
    }
}
