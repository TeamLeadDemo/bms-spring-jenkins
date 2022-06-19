package com.demo.bms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
	final static Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

//	@Around("execution(* com.demo.bms..*(..)))")
//	public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//
//		// Get intercepted method details
//		String className = methodSignature.getDeclaringType().getSimpleName();
//		String methodName = methodSignature.getName();
//		LOG.info("Executing " + className + "." + methodName);
////          
////        final StopWatch stopWatch = new StopWatch();
////          
////        //Measure method execution time
////        stopWatch.start();
//		Object result = proceedingJoinPoint.proceed();
////        stopWatch.stop();
////  
////        //Log method execution time
////        LOG.info("Execution time of " + className + "." + methodName + " "
////                  + ":: " + stopWatch.getTotalTimeMillis() + " ms");
////  
//
//		return result;
//	}
	
	@Before("execution(* com.demo.bms..*(..)))")
	public void beforeLogging(JoinPoint joinPoint) {
		
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = joinPoint.getSignature().getDeclaringType().getName();
		LOG.info("Entering " + className + "." + methodName);
	}
	
	@After("execution(* com.demo.bms..*(..)))")
	public void afterLogging(JoinPoint joinPoint) {
		
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = joinPoint.getSignature().getDeclaringType().getName();
		LOG.info("Exiting " + className + "." + methodName);
	}
}