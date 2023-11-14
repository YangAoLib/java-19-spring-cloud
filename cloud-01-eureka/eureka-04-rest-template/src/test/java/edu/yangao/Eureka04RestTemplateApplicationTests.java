package edu.yangao;

import edu.yangao.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class Eureka04RestTemplateApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGet() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/test-get?name=ya";
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
//        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
    }

    @Test
    public void testPostJson() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/test-post-json";
        User user = new User("杨奥", 18, 10000D);
        // rest template 会使用 jackson 将user进行json序列化
        String result = restTemplate.postForObject(url, user, String.class);
        System.out.println(result);
    }

    @Test
    public void testPostForm() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/test-post-form";
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", "杨奥2");
        map.add("age", 18);
        map.add("price", 8000D);
        String result = restTemplate.postForObject(url, map, String.class);
        System.out.println(result);
    }

}
