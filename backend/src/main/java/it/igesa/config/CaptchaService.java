package it.igesa.config;
import it.igesa.payload.response.RecaptchaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
/**
 *
 * @author Tarchoun Abir
 *
 */
    @PropertySource("classpath:application.properties")
    @Service
    public class CaptchaService {

        private final RestTemplate restTemplate;

        public CaptchaService(RestTemplateBuilder restTemplateBuilder) {
            this.restTemplate = restTemplateBuilder.build();
        }

        @Value("${google.recaptcha.secret.key}")
        public String recaptchaSecret;
        @Value("${google.recaptcha.verify.url}")
        public String recaptchaVerifyUrl;

        public boolean verify(String response) {
            MultiValueMap param= new LinkedMultiValueMap<>();
            param.add("secret", recaptchaSecret);
            param.add("response", response);

            RecaptchaResponse recaptchaResponse = null;
            try {
                recaptchaResponse = this.restTemplate.postForObject(recaptchaVerifyUrl, param, RecaptchaResponse.class);
            }catch(RestClientException e){
                System.out.print(e.getMessage());
            }
            if(recaptchaResponse.isSuccess()){
                return true;
            }else {
                return false;
            }
        }
    }
