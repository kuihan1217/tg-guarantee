package com.yonyou.guarantee.configs;

import com.alibaba.fastjson.JSON;
import com.yonyou.guarantee.annotation.PassToken;
import com.yonyou.guarantee.common.JWTTokenUtil;
import com.yonyou.guarantee.common.OkHttpUtil;
import com.yonyou.guarantee.constants.ReturnCode;
import com.yonyou.guarantee.vo.RestResultVO;
import io.jsonwebtoken.Claims;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;


public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 执行认证
        if (token == null) {
            writeJson(httpServletResponse, "无token，请重新登录");
            return false;
        }
        Claims claims = JWTTokenUtil.verifyToken(token);
        if (claims == null) {
            writeJson(httpServletResponse, "Token无效，请重新登录");
            return false;
        }
        Date expireDate = claims.getExpiration();
        if (null == expireDate || JWTTokenUtil.isTokenExpired(expireDate)) {
            writeJson(httpServletResponse, "Token过期了，请重新登录");
            return false;
        }
        String userName = claims.getSubject();
        httpServletRequest.setAttribute("userName", userName);
        return true;
    }

    private void writeJson(HttpServletResponse httpServletResponse, String s) {
        RestResultVO<String> vo = new RestResultVO<>();
        vo.setCode(ReturnCode.TOKEN_INVALID.getCode());
        vo.setMsg(s);
        OkHttpUtil.respJsonToClient(httpServletResponse, JSON.toJSONString(vo));
    }
}
