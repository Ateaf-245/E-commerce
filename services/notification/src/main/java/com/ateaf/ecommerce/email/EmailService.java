package com.ateaf.ecommerce.email;

import com.ateaf.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public  void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED,UTF_8.name());

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("amount",amount);
        variables.put("orderReference",orderReference);

        Context context = new Context();
        context.setVariables(variables);

        try{
            messageHelper.setFrom("ateafbankapur11@gmail.com");
            messageHelper.setTo(destinationEmail);
            messageHelper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());

            final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();
            String htmlTemplate = templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);

            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s",destinationEmail,templateName));
        }catch (MessagingException e){
            log.warn("WARNING - Cannot send email to {}",destinationEmail);
        }
    }

    @Async
    public  void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED,UTF_8.name());

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("totalAmount",amount);
        variables.put("orderReference",orderReference);
        variables.put("products",products);

        Context context = new Context();
        context.setVariables(variables);

        try{
            messageHelper.setFrom("ateafbankapur11@gmail.com");
            messageHelper.setTo(destinationEmail);
            messageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());

            final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();
            String htmlTemplate = templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);

            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s",destinationEmail,templateName));
        }catch (MessagingException e){
            log.warn("WARNING - Cannot send email to {}",destinationEmail);
        }
    }
}
