package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.service.MySimpleService;


public class MySimpleServiceImpl implements MySimpleService {

    @Override
    public void print(String text) {
        System.out.println("in MySimpleServiceImpl print");
        System.out.println(text);
        test();
    }

    @Override
    public void test() {
        System.out.println("in MySimpleServiceImpl test");
    }

}
