package com.coral.support;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在action调用完毕时做些事情
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
    /*@Autowired
    private ILogService logService;*/

    private NamedThreadLocal<Long> threadLocal = new NamedThreadLocal<Long>("requestStartTime");

    /**
     * 预处理回调方法，实现处理器的预处理
     *
     * @param request
     * @param response
     * @param handler
     * @return 返回值
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        threadLocal.set(beginTime);

        String sessionId = request.getSession().getId();

        StringBuilder builder = new StringBuilder();

        builder.append("Url: ").append(request.getRequestURL());
        builder.append("Params: ");
        for (Object key : request.getParameterMap().keySet()) {
            String keyStr = key.toString();
            builder.append("name:").append(keyStr);
            builder.append(", value:").append(request.getParameter(keyStr)).append(", ");
        }
        //logService.info(sessionId, "WEB ACCESS START", builder.toString());

        return super.preHandle(request, response, handler);
    }

    /**
     * 后处理的回调方法，实现处理器的后处理
     *
     * @param request
     * @param response
     * @param handler
     * @param mav
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
        super.postHandle(request, response, handler, mav);
    }

    /**
     * 整个请求处理完毕后回调方法，即在请求完毕后回调
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        long endTime = System.currentTimeMillis();
        long beginTime = threadLocal.get();
        long consumeTime = endTime - beginTime;//消耗时间

        String sessionId = request.getSession().getId();

        //logService.info(sessionId, "WEB ACCESS COMPLETE", "Consume Time:" + consumeTime);

        threadLocal.set(null);
    }
}
