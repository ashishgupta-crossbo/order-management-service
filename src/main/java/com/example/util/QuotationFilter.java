package com.example.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.exceptions.CustomException;
import com.example.common.ErrorCode;
import com.example.exceptions.ServerException;
import io.micronaut.core.annotation.Order;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.micronaut.http.filter.ServerFilterPhase;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.slf4j.LoggerFactory;
import java.util.UUID;

@Singleton
@Slf4j
@Order(1)
@Filter(Filter.MATCH_ALL_PATTERN)
public class QuotationFilter implements HttpServerFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QuotationFilter.class);

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        logger.info("CustomFilter is processing the request.");
        HttpHeaders headers = request.getHeaders();
        String authorizationHeader = headers.get(AUTHORIZATION_HEADER);
        if (authorizationHeader==null){
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
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
          logger.info("Getting error in extractUserIdFromToken function {}",e.getMessage());
            return null;
        }
    }

    public static String randomRequestId(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();

    }
}
