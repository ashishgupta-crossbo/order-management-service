package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.exceptions.CustomException;
import com.example.repository.common.ErrorCode;
import io.micronaut.core.annotation.Order;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.micronaut.http.filter.ServerFilterPhase;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.compiler.lir.CompositeValue;
import org.reactivestreams.Publisher;
import org.slf4j.LoggerFactory;
import java.util.Optional;


@Singleton
@Slf4j
@Order(1)
@Filter(Filter.MATCH_ALL_PATTERN)
public class CustomFilter implements HttpServerFilter {

    private final QuoteHelper quoteHelper;

    public static final String TENANT_ID_ATTRIBUTE_NAME = "accessToken";
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    public CustomFilter(QuoteHelper quoteHelper) {
        this.quoteHelper = quoteHelper;
    }

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        logger.info("CustomFilter is processing the request.");
        Optional<String> accessTokenOptional = request.getAttribute(TENANT_ID_ATTRIBUTE_NAME, String.class);

        if (accessTokenOptional.isEmpty()) {
        throw new CustomException(ErrorCode.CUSTOM_EXCEPTION);
        }
        return chain.proceed(request);
    }



    @Override
    public int getOrder() {
        return ServerFilterPhase.SECURITY.order();
    }

    public static String extractUserIdFromToken(String token) {

        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
