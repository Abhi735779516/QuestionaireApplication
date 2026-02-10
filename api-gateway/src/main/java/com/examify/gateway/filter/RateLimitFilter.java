package com.examify.gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter implements GlobalFilter {

    private final ConcurrentHashMap<String, Integer> requests = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        String ip =
                exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        requests.put(ip, requests.getOrDefault(ip, 0) + 1);

        if (requests.get(ip) > 100) {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
