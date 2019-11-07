package com.pactera.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author simonMeng
 * @version 1.0
 * @date 2019/10/23
 **/
@Component
public class XML2JSONFormatRoute extends RouteBuilder {
    @Autowired
    private XmlJsonDataFormat xmlJsonDataFormat;
    @Override
    public void configure() throws Exception {
        //xml to json
        from("direct:xmlToJsonFormatData").marshal().xmljson().to("direct:audit").to("log:?level=INFO&showBody=true");
        //json to xml
        from("direct:jsonToXmlFormatData").unmarshal(xmlJsonDataFormat).to("log:?level=INFO&showBody=true");
    }
}
