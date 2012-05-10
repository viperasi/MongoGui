package com.xu81.java.tools.util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.xu81.java.tools.model.ConnectionBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Arthur.Tsu
 */
public class DBEngine {
    
    private static final Map<String,DBEngine> map = new HashMap<String, DBEngine>();
    private Mongo mongo;
    private DB db;
    
    public static DBEngine getInstance(ConnectionBean cb){
        DBEngine engine = map.get(cb.getName());
        if(engine!=null)
            return engine;
        synchronized(DBEngine.class){
            engine = new DBEngine();
            try {
                engine.mongo = new Mongo(cb.getServer(),Integer.valueOf(cb.getPort()));
                if(StringUtils.isEmpty(cb.getDbName())){
                    engine.db = engine.mongo.getDB(cb.getDbName());
                }
            } catch (Exception ex) {
                Logger.getLogger(DBEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            map.put(cb.getName(), engine);
            return engine;
        }
    }
    
    public List<String> showDbs(){
        return this.mongo.getDatabaseNames();
    }
    
    public Set<String> showCollections(String db){
        this.db = this.mongo.getDB(db);
        return this.db.getCollectionNames();
    }
    
    public List<Map<String,Object>> findAll(String db,String coll){
        this.db = this.mongo.getDB(db);
        DBCollection collection = this.db.getCollection(coll);
        DBCursor cur = collection.find();
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        while(cur.hasNext()){
            list.add(cur.next().toMap());
        }
        return list;
    }
    
    public boolean testConn(){
        boolean bool = false;
        try{
            List<String> dbs = this.mongo.getDatabaseNames();
            bool = true;
        }catch(Exception ex){
            bool = false;
        }
        return bool;
    }
}
