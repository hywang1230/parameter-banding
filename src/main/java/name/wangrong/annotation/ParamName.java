package name.wangrong.annotation;

import java.lang.annotation.*;

/**
 * Created by wangrong on 15/12/24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamName {
    /**
     * The name of the request parameter to dind to
     */
    String value();
}
