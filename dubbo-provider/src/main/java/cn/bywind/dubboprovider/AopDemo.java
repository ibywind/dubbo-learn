package cn.bywind.dubboprovider;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AopDemo {

    @Pointcut("@annotation(cn.bywind.dubboprovider.Say)")
    public void aspect(){

    }

    @Around("aspect()")
    public Object arount(ProceedingJoinPoint point){
        System.out.println(point);
        return 1;
    }
}
