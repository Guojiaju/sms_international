package com.sms.international.admin.mongodb;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.sms.international.admin.utils.ConstantSys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;


public class MongoConnection {
    public static final Logger logger = LoggerFactory.getLogger(MongoConnection.class);

    private MongoClient mongo = null;

    public MongoConnection() {
    	init();
	}

    /**
     * 根据名称获取DB，相当于是连接
     * @return
     */
    public  DB getDB() {
            // 初始化
            init();
        return mongo.getDB(ConstantSys.MONGODB_NAME);
    }

    /**
     * 初始化连接池，设置mango链接参数。
     */
    private void init() {
    	logger.info(ConstantSys.MONGODB_HOST+"--------------->"+ConstantSys.MONGODB_PORT);
        try {
            mongo = new MongoClient(ConstantSys.MONGODB_HOST, ConstantSys.MONGODB_PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void close(){
    	mongo.close();
    	mongo = null;
    }


}
