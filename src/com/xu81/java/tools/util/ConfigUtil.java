package com.xu81.java.tools.util;

import com.xu81.java.tools.model.ConfigBean;
import com.xu81.java.tools.model.ConnectionBean;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Arthur.Tsu
 */
public class ConfigUtil {
    
//    private static String configPath = null;
    private static Document doc = null;
    
    static{
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(ConfigUtil.class.getResourceAsStream("/config.xml"));
        } catch (DocumentException ex) {
            Logger.getLogger(ConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Map<String,ConnectionBean> getConns(){
        Node root = doc.selectSingleNode("/root");
        Node connNode = root.selectSingleNode("connections");
        Iterator<Element> connIter = connNode.selectNodes("connection").iterator();
        Map<String,ConnectionBean> conns = new HashMap<String, ConnectionBean>();
        while(connIter.hasNext()){
            Element conn = connIter.next();
            ConnectionBean cb = new ConnectionBean(
                        StringUtils.trim(conn.selectSingleNode("name").getText()),
                        StringUtils.trim(conn.selectSingleNode("server").getText()),
                        StringUtils.trim(conn.selectSingleNode("port").getText()),
                        StringUtils.trim(conn.selectSingleNode("collection").getText())
                    );
            conns.put(cb.getName(), cb);
        }
        return conns;
    }
    
    public static ConfigBean getCfg(){
        Node root = doc.selectSingleNode("/root");
        Node cfgNode = root.selectSingleNode("config");
        
        return null;
    }
    
    public static void add(String qname,List<Attribute> attrs,String text){
        
    }
    
    private static void save(){
        try {
            doc.write(new FileWriter(new File(doc.getPath())));
        } catch (IOException ex) {
            Logger.getLogger(ConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
