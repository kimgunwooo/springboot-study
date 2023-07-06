package com.springboot.myproject;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll(){
        System.out.println("## BeforeAll Annotation 호출 ##\n");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("## AfterAll Annotation 호출 ##\n");
    }
    @BeforeEach
    void beforeEach(){
        System.out.println("## BeforeEach Annotation 호출 ##\n");
    }
    @AfterEach
    void afterEach(){
        System.out.println("## AfterEach Annotation 호출 ##\n");
    }
    @Test
    void test1(){
        System.out.println("## test1 시작 ##\n");
    }
    @Test
    @DisplayName("Test Case 2!!")
    void test2(){
        System.out.println("## test2 시작 ##\n");
    }
    @Test
    @Disabled
    void test3(){
        System.out.println("## test3 시작 ##\n");
    }
}
