package com.roger.manager;

import com.roger.SpringBaseTestSuit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestRedisCacheManager extends SpringBaseTestSuit {

    @Autowired(required = false)
    private RedisCacheManager redisCacheManager;

    @Test
    public void testRedisCofig(){
        Assert.assertTrue(redisCacheManager != null);
    }

    @Test
    public void testGet(){
        Object name = redisCacheManager.get("name");
        Assert.assertNull(name);

        redisCacheManager.set("name","roger");
        name = redisCacheManager.get("name");
        Assert.assertNotNull(name);

        long delCount = redisCacheManager.del("name");
        Assert.assertTrue( delCount == 1);
    }

    @Test
    public void testSetNX(){
        boolean ifAbsent = redisCacheManager.setNX("name","roger");
        Assert.assertTrue(ifAbsent);

        ifAbsent = redisCacheManager.setNX("name","Mary");
        Assert.assertFalse(ifAbsent);

        Object name = redisCacheManager.get("name");
        Assert.assertTrue("roger".equals(name));

        long delCount = redisCacheManager.del("name");
        Assert.assertTrue(delCount == 1);

    }
}
