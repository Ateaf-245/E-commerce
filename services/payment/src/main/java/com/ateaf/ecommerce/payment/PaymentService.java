package com.ateaf.ecommerce.payment;

import com.ateaf.ecommerce.notification.NotificationProducer;
import com.ateaf.ecommerce.notification.PaymentNotificationRequest;
import com.ateaf.ecommerce.payment.requests.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {

        var payment = repository.save(mapper.toPayment(request));

        log.info("About to send Payment Notification :: {}",request);
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
