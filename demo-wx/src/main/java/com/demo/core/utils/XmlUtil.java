package com.demo.core.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 胡超云 on 2016/11/28.
 */
public class XmlUtil {

    //解析xml
    public static Map<String, String> parseXml(HttpServletRequest request){
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = null;
        try {
            //从request中取得输入流
            inputStream = request.getInputStream();
            //读取输入流
            SAXReader reader = new SAXReader();
            Document doc = reader.read(inputStream);

            //得到xml根元素
            Element root = doc.getRootElement();
            //得到根节点下所有子节点
            List<Element> elementList = root.elements();
            //遍历子节点
            elementList.forEach(e -> {
                map.put(e.getName(),e.getText());
            });

            // 释放资源
            inputStream.close();
            inputStream = null;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return map;
    }

    //扩展xstream，使其支持CDATA块
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    //过滤createTime节点
                    if("CreateTime".equals(name)){
                        cdata = false;
                    }else {
                        cdata = true;
                    }

                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
