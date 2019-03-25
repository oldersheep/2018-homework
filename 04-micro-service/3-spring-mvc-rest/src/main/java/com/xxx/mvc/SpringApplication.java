package com.xxx.mvc;

import com.xxx.mvc.service.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionStatus;

@ComponentScan(basePackages = "com.xxx.mvc.service")
@EnableTransactionManagement
public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(SpringApplication.class);

        context.refresh(); // 启动

        context.getBeansOfType(EchoService.class).forEach((beanName, bean) ->{
            System.err.println("Bean Name : " + beanName + ",Bean : " + bean);
            bean.echo("报错给我！");
            // No qualifying bean of type 'org.springframework.transaction.PlatformTransactionManager' available
        });

        context.close(); // 关闭
    }

    @Component("myTxName")
    // 自定义写一个PlatformTransactionManager
    public static class MyPlatformTransactionManager implements PlatformTransactionManager {

        @Override
        public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
            return new DefaultTransactionStatus(null, true,
                    true, transactionDefinition.isReadOnly(), true,
                    null);
        }

        @Override
        public void commit(TransactionStatus transactionStatus) throws TransactionException {
            System.out.println("Commit...");
        }

        @Override
        public void rollback(TransactionStatus transactionStatus) throws TransactionException {
            System.out.println("RollBack...");
        }
    }
}
