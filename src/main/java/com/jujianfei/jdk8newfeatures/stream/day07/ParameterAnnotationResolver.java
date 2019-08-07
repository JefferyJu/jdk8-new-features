package com.jujianfei.jdk8newfeatures.stream.day07;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/7 9:14
 */
public class ParameterAnnotationResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MyAnnotation.class);
    }

    @Override
    @Nullable
    public String resolveArgument(MethodParameter parameter,
                                  @Nullable ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  @Nullable WebDataBinderFactory binderFactory) throws Exception {
        MyAnnotation myAnnotation = parameter.getParameterAnnotation(MyAnnotation.class);

        return myAnnotation.value();

    }
}
