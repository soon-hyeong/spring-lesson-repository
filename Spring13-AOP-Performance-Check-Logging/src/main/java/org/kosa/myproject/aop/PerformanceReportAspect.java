package org.kosa.myproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class PerformanceReportAspect {
	private final static Logger logger = LoggerFactory.getLogger(PerformanceReportAspect.class);

	@Around("execution(public * org.kosa.myproject.model..*Service.*(..))")
	public Object checkSpeed(ProceedingJoinPoint point) throws Throwable {
		
		Object pointReturn = null;
		StopWatch stopWatch = new StopWatch();
		
		try {
			stopWatch.start();
			pointReturn = point.proceed();
		} finally {
			stopWatch.stop();
			long time = stopWatch.getTotalTimeMillis();
			if(time >= 1000) {
				logger.error("core class: {} method: {} runtime: {}ms", point.getTarget().getClass(), point.getSignature().getName(), time);
			} else if(stopWatch.getTotalTimeMillis() >= 500) {
				logger.warn("core Class: {} method: {} runtime: {} ms", point.getTarget().getClass(), point.getSignature().getName(), time);
			}
		}
		
		return pointReturn;
	}
}
