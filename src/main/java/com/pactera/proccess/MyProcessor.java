package com.pactera.proccess;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Map;

/***
 * 数据格式转换的格式
 */
public class MyProcessor {
	public String process(@Body String body) {
		return (null!=body?body:null);
	}
}
