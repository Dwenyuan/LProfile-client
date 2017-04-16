package com.liu.LProfile.client;

import java.io.UnsupportedEncodingException;

import com.liu.lprofile.util.ZIPUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ProbeTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ProbeTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ProbeTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void Test() throws UnsupportedEncodingException{
    	byte[] compress = ZIPUtil.encoder("lojure里面的每个操作被实现成以下三种形式的一种: 操作被实现成以下三种形式的一种函数(function), 宏(macro)或者special form. 几乎所有的函数和宏都是用Clojure代码实现的，它们的主要区别我们会在后面解释。Special forms不是用clojure代码实现的，而且被clojure的编译器识别出来. special forms的个数是很少的， 而且现在也不能再实现新的special forms了. 它们包括: catch , def , do , dot (‘.’), finally , fn , if , let , loop , monitor-enter , monitor-exit , new , quote , recur , set! , throw , try 和 var .+".getBytes());
    	System.out.println(compress.length);
    	byte[] decoder = ZIPUtil.decoder(compress);
    	System.out.println(new String(decoder,"utf-8"));
    }
}
