package com.sankuai;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    private class MockDto{
        String id;
        String name;

        @Override
        public String toString() {
            return "MockDto{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private class MockService{
        public MockDto getDataByMockIds(String id){
            MockDto mockDto = new MockDto();
            mockDto.id = id;
            mockDto.name ="zlx";
            return mockDto;
        }
    }

    @Test
    public void completableFuturetest1(){
        MockService mockService = new MockService();
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        List<MockDto> results = new ArrayList<>();

        ids.forEach(id ->{
            CompletableFuture<Void> res = CompletableFuture.supplyAsync(()->{
                    return mockService.getDataByMockIds(id);
                    }
                    ).thenAccept(results::add).exceptionally(ex->{
                throw new RuntimeException(ex.getMessage() + "异步数据获取执行异常");
            });
            futures.add(res);
        });
        futures.forEach(CompletableFuture::join);
        results.forEach(System.out::println);
        System.out.println("-------------------------");
        results.forEach(result->System.out.println(result.id));
    }
}
