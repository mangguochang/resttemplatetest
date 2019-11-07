package com.pactera.proccess;

import com.pactera.common.TemplateUitls;
import com.pactera.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * 请求参数进来需要转换的操作。
 * @author simonMeng
 * @version 1.0
 * @date 2019/10/18
 **/
@Component("camelRequestProccess")
public class CamelRequestProccess implements Processor {
    private Logger logger = LoggerFactory.getLogger(CamelRequestProccess.class);

    @Autowired
    public UserService userService;
    @Value("${spring.application.name}")
    public String appId;
    @Value("${camel.request.url}")
    public String camelRequestUrl;

    @Override
    public void process(Exchange exchange) throws Exception {
        long startTime=new Date().getTime();
        Object object=exchange.getIn().getBody();
        Map<String,Object> headers=exchange.getIn().getHeaders();
        String token=null!=headers.get(TemplateUitls.Token_Key)?headers.get(TemplateUitls.Token_Key).toString():
                null;
        int tokenStatus=0;
        if(null!=token&&token.length()>0){
            boolean isOk=userService.checkUserAuthority(token,appId);
            if(isOk){
                if(null!=headers.get("Content-Type")
                        &&headers.get("Content-Type").toString().equalsIgnoreCase("application/json")){//
                    HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                            .getRequest();
                    String queryStr=request.getQueryString();
                    headers.put("queryStr",queryStr);
                    if(object instanceof Map){
                        JSONObject jsonObject=JSONObject.fromObject(object);
                        exchange.getIn().setBody(jsonObject);
                    }else{
                        exchange.getIn().setBody(object);
                    }
                }
                tokenStatus= TemplateUitls.Token_Success_Status_Ok;
            }else{
                logger.info("App-{}:Token is no authority or no valid!",appId);
                tokenStatus= TemplateUitls.Token_Fail_Status_Power_OR_Invalid;//无权访问或者
            }
        }else{
            logger.info("App-{}:Token is null!",appId);
            tokenStatus=TemplateUitls.Token_Fail_Status_Null;//无效Token
        }
        headers.put("tokenStatus",tokenStatus);
        headers.put("startTime",startTime);
        exchange.getIn().setHeaders(headers);
    }
}
