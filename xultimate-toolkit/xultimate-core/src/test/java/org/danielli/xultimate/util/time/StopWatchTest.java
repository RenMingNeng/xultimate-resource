package org.danielli.xultimate.util.time;

import org.danielli.xultimate.util.time.stopwatch.StopWatch;
import org.danielli.xultimate.util.time.stopwatch.support.AdvancedStopWatchSummary;
import org.danielli.xultimate.util.time.stopwatch.support.DefaultStopWatchSummary;
import org.danielli.xultimate.util.time.stopwatch.support.SimpleStopWatchSummary;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopWatchTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(StopWatchTest.class);
	
	@Test
	public void test() {
		StopWatch stopWatch = new StopWatch("test");
		stopWatch.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
		stopWatch.mark("Task 1");
		stopWatch.suspend();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
		stopWatch.resume();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
		}
		stopWatch.mark("Task 2");
		stopWatch.stop();
		
		SimpleStopWatchSummary simpleStopWatchSummary = new SimpleStopWatchSummary();
		simpleStopWatchSummary.setLogger(LOGGER);
		simpleStopWatchSummary.summarize(stopWatch);
		
		DefaultStopWatchSummary defaultStopWatchSummary = new DefaultStopWatchSummary(true);
		defaultStopWatchSummary.setLogger(LOGGER);
		defaultStopWatchSummary.summarize(stopWatch);
		
		AdvancedStopWatchSummary advancedStopWatchSummary = new AdvancedStopWatchSummary(true);
		advancedStopWatchSummary.setLogger(LOGGER);
		advancedStopWatchSummary.summarize(stopWatch);
	}

}
