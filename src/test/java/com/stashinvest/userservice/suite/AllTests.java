/**
 * 
 */
package com.stashinvest.userservice.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.stashinvest.userservice.controller.UserServiceControllerTest;
import com.stashinvest.userservice.scheduler.ScheduledTasksTest;
import com.stashinvest.userservice.service.UserServiceImplTest;

/**
 * @author abhimanyu
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
  UserServiceControllerTest.class,
  ScheduledTasksTest.class,
  UserServiceImplTest.class
})
public class AllTests {
	//Empty class to hold above annotations
}
