package com.ttm.pet.config;

import com.ttm.pet.util.JwtUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if(isProtectedUrl(request)) {
                String token = request.getHeader("Authorization");
                //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
                JwtUtil.validateToken(token);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(request, response);
    }


    //对地址 /admin 开头的api检查jwt. 除去login
    private boolean isProtectedUrl(HttpServletRequest request) {
        String url = request.getServletPath();
        System.out.println("url========"+url);
        if(pathMatcher.match("/admin/api/v1/system/*",url)){
            return false;
        }
        if(pathMatcher.match("/admin/api/v1/scenic/scenics",url)){
            return false;
        }
        if(pathMatcher.match("/admin/**",url)){
            return false;
        }
        return false;
    }
}
