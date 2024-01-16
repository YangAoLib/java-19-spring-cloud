package edu.yangao;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ServiceOrderApplicationTest {

    @Mock
    private List<String> mockList;

    @Test
    public void mockTest() {
        mockList.add("one");
        mockList.clear();

        verify(mockList).add("one");
        verify(mockList).clear();
    }
}
