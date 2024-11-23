package com.yupi.yuoj;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
class MainApplicationTests {

    // @Resource
    // private WxOpenConfig wxOpenConfig;
    //
    // @Test
    // void contextLoads() {
    //     System.out.println(wxOpenConfig);
    // }
    //
    private static int i = 10;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            i = 30;
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(i);
    }

}
