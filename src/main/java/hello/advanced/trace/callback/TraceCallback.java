package hello.advanced.trace.callback;

/**
 *
 * 콜백을 전달하는 인터페이스이다.
 * `<T>` 제네릭을 사용했다. 콜백의 반환 타입을 정의한다.
 */
public interface TraceCallback <T>{
    T call();
}
