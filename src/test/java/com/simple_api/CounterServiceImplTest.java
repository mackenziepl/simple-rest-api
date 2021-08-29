package com.simple_api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.simple_api.entity.Counter;
import com.simple_api.repository.CounterRepository;
import com.simple_api.service.CounterService;
import com.simple_api.service.impl.CounterServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CounterServiceImplTest {

    private CounterRepository repository = Mockito.mock(CounterRepository.class);;
    private CounterService service = new CounterServiceImpl(repository);

    @Test
    public void shouldSaveLogin() {
        //Given
        Counter counter = Counter.builder()
                .login("anyLogin")
                .requestCount(1)
                .build();

        when(repository.save(any())).thenReturn(counter);

        //When
        service.addCounter(counter);

        //Then
        verify(repository).findByLogin(counter.getLogin());
    }
}
