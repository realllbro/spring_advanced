package hello.advanced.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepostory;

    public void orderItem(String itemId){
        orderRepostory.save(itemId);
    }
}
