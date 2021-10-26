package;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

/**
 * @apiNote RESTful API 유틸
 * @author 이재열
 * @since 2020-09-10
 * */
public class RestUtils {
    /* 기본 타임아웃 시간 */
    private static final int DEFAULT_TIME_OUT = 10000;

    /**
     * ****************************************
     * @apiNote HTTP Method : GET
     * ****************************************
     * */

    /**
     * @param url 요청 URL
     * @return ResponseEntity<String> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static ResponseEntity<String> get(final String url) {
        return get(url, null);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @return ResponseEntity<String> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static ResponseEntity<String> get(
            final String url,
            MultiValueMap<String, String> requestParams) {
        return get(url, requestParams, String.class);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> get(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType) {
        return get(url, requestParams, responseType, null);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @param header RESTful 통신 시 전달할 헤더 정보
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> get(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType,
            HttpHeaders header) {
        return get(url, requestParams, responseType, header, DEFAULT_TIME_OUT);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @param header RESTful 통신 시 전달할 헤더 정보
     * @param timeout 타임아웃 설정 시간
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> get(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType,
            HttpHeaders header,
            final int timeout) {
        return sendRESTful(url, requestParams, responseType, header, timeout, HttpMethod.GET);
    }


    /**
     * ****************************************
     * @apiNote HTTP Method : POST
     * ****************************************
     * */

    /**
     * @param url 요청 URL
     * @return ResponseEntity<String> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static ResponseEntity<String> post(final String url) {
        return post(url, null);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @return ResponseEntity<String> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static ResponseEntity<String> post(
            final String url,
            MultiValueMap<String, String> requestParams) {
        return post(url, requestParams, String.class);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> post(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType) {
        return post(url, requestParams, responseType, null);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @param header RESTful 통신 시 전달할 헤더 정보
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> post(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType,
            HttpHeaders header) {
        return post(url, requestParams, responseType, header, DEFAULT_TIME_OUT);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @param header RESTful 통신 시 전달할 헤더 정보
     * @param timeout 타임아웃 설정 시간
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> post(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType,
            HttpHeaders header,
            final int timeout) {
        return sendRESTful(url, requestParams, responseType, header, timeout, HttpMethod.POST);
    }


    /**
     * ****************************************
     * @apiNote HTTP Method : PUT
     * ****************************************
     * */

    /**
     * @param url 요청 URL
     * @return ResponseEntity<String> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static ResponseEntity<String> put(final String url) {
        return put(url, null);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @return ResponseEntity<String> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static ResponseEntity<String> put(
            final String url,
            MultiValueMap<String, String> requestParams) {
        return put(url, requestParams, String.class);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> put(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType) {
        return put(url, requestParams, responseType, null);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @param header RESTful 통신 시 전달할 헤더 정보
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> put(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType,
            HttpHeaders header) {
        return put(url, requestParams, responseType, header, DEFAULT_TIME_OUT);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @param header RESTful 통신 시 전달할 헤더 정보
     * @param timeout 타임아웃 설정 시간
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> put(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType,
            HttpHeaders header,
            final int timeout) {
        return sendRESTful(url, requestParams, responseType, header, timeout, HttpMethod.PUT);
    }


    /**
     * ****************************************
     * @apiNote HTTP Method : DELETE
     * ****************************************
     * */

    /**
     * @param url 요청 URL
     * @return ResponseEntity<String> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static ResponseEntity<String> delete(final String url) {
        return delete(url, null);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @return ResponseEntity<String> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static ResponseEntity<String> delete(
            final String url,
            MultiValueMap<String, String> requestParams) {
        return delete(url, requestParams, String.class);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> delete(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType) {
        return delete(url, requestParams, responseType, null);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @param header RESTful 통신 시 전달할 헤더 정보
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> delete(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType,
            HttpHeaders header) {
        return delete(url, requestParams, responseType, header, DEFAULT_TIME_OUT);
    }

    /**
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @param header RESTful 통신 시 전달할 헤더 정보
     * @param timeout 타임아웃 설정 시간
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    public static <T> ResponseEntity<T> delete(
            final String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType,
            HttpHeaders header,
            final int timeout) {
        return sendRESTful(url, requestParams, responseType, header, timeout, HttpMethod.DELETE);
    }


    /**
     * @implNote 공통 호출 로직
     * @param url 요청 URL
     * @param requestParams 요청 파라미터
     * @param responseType 응답 타입 (json, xml - Map.class | html, text - String.class)
     * @param header RESTful 통신 시 전달할 헤더 정보
     * @param timeout 타임아웃 설정 시간
     * @param httpMethod HTTP 메소드 (GET, POST, PUT, DELETE)
     * @return ResponseEntity<T> - 응답 객체, 헤더 정보와 바디 정보가 리턴됨
     */
    private static <T> ResponseEntity<T> sendRESTful(
            String url,
            MultiValueMap<String, String> requestParams,
            Class<T> responseType,
            HttpHeaders header,
            final int timeout,
            final HttpMethod httpMethod) {
        HttpEntity<?> entity;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(getClientHttpRequestFactory(timeout)));

        if (!url.contains("http://")) url = "http://" + url;
        if (header == null) {
            header = new HttpHeaders();
            if (httpMethod != HttpMethod.GET)
                header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }
        if (requestParams != null) {
            if (httpMethod == HttpMethod.GET) {
                url = UriComponentsBuilder.fromHttpUrl(url).queryParams(requestParams).toUriString();
                entity = new HttpEntity<>(null, header);
            } else entity = new HttpEntity<>(requestParams, header);
        } else entity = new HttpEntity<>(null, header);

        return restTemplate.exchange(url, httpMethod, entity, responseType);
    }

    /**
     * @implNote 타임아웃 설정
     */
    private static HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory(final int timeout) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(timeout > 0 ? timeout : DEFAULT_TIME_OUT);
        factory.setConnectTimeout(timeout > 0 ? timeout : DEFAULT_TIME_OUT);

        return factory;
    }
}