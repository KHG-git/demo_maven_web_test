package springtestdemo.sample;


import com.example.demo_maven_web.DemoMavenWebApplication;
import com.example.demo_maven_web.sample.SampleController;
import com.example.demo_maven_web.sample.SampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@SpringBootTest(classes= DemoMavenWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@SpringBootTest(classes= DemoMavenWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@JsonTest
//@SpringBootTest(classes= DemoMavenWebApplication.class)
@WebMvcTest(SampleController.class)
@ContextConfiguration(classes= DemoMavenWebApplication.class)
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

//    @Autowired
//    TestRestTemplate testRestTemplate;

//    @Autowired
//    WebTestClient webTestClient;  // 콜백윽을 실행할 수 있다

    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello() throws Exception {

        when(mockSampleService.getName()).thenReturn("hyungoo");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello hyungoo"))
                //.andDo(print());
        ;





//        webTestClient.get().uri("/hello").exchange().expectStatus().isOk()
//                .expectBody(String.class).isEqualTo("hello hyungoo");

        //String result = testRestTemplate.getForObject("/hello", String.class);
        //assertThat(result).isEqualTo("hello hyungoo");
    }


}
