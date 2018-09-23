package com.roger;

import com.roger.config.RedisClusterConfig;
import com.roger.config.SpringBeanConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringBeanConfig.class,RedisClusterConfig.class})
public class SpringBaseTestSuit {
}
