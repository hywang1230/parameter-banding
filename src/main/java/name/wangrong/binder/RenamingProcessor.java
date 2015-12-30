package name.wangrong.binder;

import name.wangrong.annotation.ParamName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangrong on 15/12/24.
 */
public class RenamingProcessor extends ServletModelAttributeMethodProcessor {
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private final Map<Class<?>, Map<String, String>> replaceMap = new ConcurrentHashMap<Class<?>, Map<String, String>>();

    public RenamingProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        Object target = binder.getTarget();
        Class<?> targetClass = target.getClass();

        if (! replaceMap.containsKey(targetClass)) {
            Map<String, String> renameMap = new HashMap<String, String>();
            analyzeClass(targetClass, renameMap);
            replaceMap.put(targetClass, renameMap);
        }

        Map<String, String> mapping = replaceMap.get(targetClass);
        ParamNameDataBinder paramNameDataBinder = new ParamNameDataBinder(target, binder.getObjectName(), mapping);
        requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(paramNameDataBinder, request);
        super.bindRequestParameters(paramNameDataBinder, request);
    }

    private void analyzeClass(Class<?> targetClass, Map<String, String> renameMap) {
        Field[] fields = targetClass.getDeclaredFields();

        for (Field field : fields) {
            ParamName paramNameAnnotation = field.getAnnotation(ParamName.class);
            if (paramNameAnnotation != null && !paramNameAnnotation.value().isEmpty()) {
                renameMap.put(paramNameAnnotation.value(), field.getName());
            }
        }

        Class<?> superClass = targetClass.getSuperclass();
        if (superClass == null) {
            return;
        } else {
            analyzeClass(superClass, renameMap);
        }

    }
}
