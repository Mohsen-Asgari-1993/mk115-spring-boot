package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.service.MySimpleService;

public class MySimpleServiceProxy implements MySimpleService {

    private final MySimpleService mySimpleService = new MySimpleServiceImpl();

    @Override
    public void print(String text) {
//        check @Transactional
//        if (propagation == 'NEVER' && transaction.isActive) {
//        throw new RuntimeException();
//        }
        System.out.println("before MySimpleServiceImpl");
        mySimpleService.print(text);
        System.out.println("after MySimpleServiceImpl");
//        check @Transaction
    }

    @Override
    public void test() {
        System.out.println("before MySimpleServiceImpl test");
        mySimpleService.test();
        System.out.println("test MySimpleServiceImpl test");
    }

    public static void main(String[] args) {
        MySimpleService service = new MySimpleServiceProxy();
        service.print("mohsen");
        service.test();
    }
}
