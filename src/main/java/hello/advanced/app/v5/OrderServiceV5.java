package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepostory;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepostory, LogTrace trace){
        this.orderRepostory = orderRepostory;
        this.template = new TraceTemplate(trace);
    }

    /**
     * template.execute(.., new TraceCallback(){..}) : 템플릿을 실행하면서 콜백을 전달한다.
     * 여기서는 콜백으로 람다를 전달했다.
     */
    public void orderItem(String itemId){
        template.execute("OrderServiceV.orderItem()", () -> {
            orderRepostory.save(itemId);
            return null;
        });
    }
}
