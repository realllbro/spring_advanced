package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    /**
     * this.template = new TraceTemplate(trace) : trace 의존관계 주입을 받으면서 필요한
     * TraceTemplate 템플릿을 생성한다.
     * 참고로 TraceTemplate 를 처음부터 스프링 빈으로 등록하고 주입받아도 된다. 이 부분은 선택이다.
     */
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace){
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }


    /**
     * template.execute(.., new TraceCallback(){..}) : 템플릿을 실행하면서 콜백을 전달한다.
     * 여기서는 콜백으로 익명 내부 클래스를 사용했다.
     */
    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.execute("OrderController.request()", new TraceCallback<String>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        });
    }
}
