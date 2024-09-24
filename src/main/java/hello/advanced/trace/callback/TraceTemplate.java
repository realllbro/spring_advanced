package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

/**
 * TraceTemplate` 는 템플릿 역할을 한다.
 * `execute(..)` 를 보면 `message` 데이터와 콜백인 `TraceCallback callback` 을 전달 받는다.
 * `<T>` 제네릭을 사용했다. 반환 타입을 정의한다.
 */
public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace trace){
        this.trace = trace;
    }

    public <T> T execute(String message, TraceCallback<T> callback){
        TraceStatus status = null;
        try{
            status = trace.begin(message);

            //로직 호출
            T result = callback.call();

            trace.end(status);
            return result;
        } catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }

}
