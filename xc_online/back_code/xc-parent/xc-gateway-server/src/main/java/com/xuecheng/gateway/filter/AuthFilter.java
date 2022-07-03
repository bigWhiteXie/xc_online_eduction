package com.xuecheng.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    //过滤器执行的具体业务逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        HttpHeaders headers = request.getHeaders();
        String jsonToken = headers.getFirst("Authorization");
        if(!StringUtils.isEmpty(jsonToken) ){
            if(jsonToken.startsWith("Bearer ")){
                jsonToken = jsonToken.substring(7);
                request.getHeaders().writableHttpHeaders(headers).add("jsonToken",jsonToken);
            }else{
                throw new RuntimeException("token is not as expected");
            }
        }
        return chain.filter(exchange);
    }

    //过滤器执行优先级，值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}