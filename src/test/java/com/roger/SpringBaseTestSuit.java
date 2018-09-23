package com.roger;

import com.roger.config.RedisConfig;
import com.roger.config.SpringBeanConfig;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringBeanConfig.class,RedisConfig.class})
public class SpringBaseTestSuit {
}
