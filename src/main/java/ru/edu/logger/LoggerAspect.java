package ru.edu.logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

  // указываем срез - набор точек соединения
  // * - возвращаемые значения
  // ru.edu.UsersRestController.*(..) - все методы UsersRestController с любыми значениями
  @Pointcut("execution(* ru.edu.UsersRestController.*(..))")
  public void userRestControllerPointcut() {

  }

  // Объявляем advice (совет)
  // перед всеми методами, которые попадают под срез userRestControllerPointcut
  // JoinPoint - точка присоединения. Из него можно взять всю информацию по вызову
  @Before("userRestControllerPointcut()")
  public void beforeMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println("Method: " + methodName + " | args: " + getArgs(joinPoint) + " | start");
  }

  // после успешного выполнения методов, которые попадают под срез userRestControllerPointcut
  @AfterReturning("userRestControllerPointcut()")
  public void afterMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println("Method: " + methodName + " | args: " + getArgs(joinPoint) + " | end");
  }

  // после ошибки в методах, которые попадают под срез userRestControllerPointcut
  // throwing = "ex" - поместить ошибку в переменную ex
  @AfterThrowing(value = "userRestControllerPointcut()", throwing = "ex")
  public void afterException(JoinPoint joinPoint, Throwable ex) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println("ERROR Method: " + methodName + " | args: " + getArgs(joinPoint) + " | ex=" + ex.getMessage());
  }

  private List<String> getArgs(JoinPoint joinPoint) {
    return Arrays.stream(joinPoint.getArgs())
        .map(Object::toString).collect(Collectors.toList());
  }

}
