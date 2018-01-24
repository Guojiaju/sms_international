package com.sms.international.admin.mongodb;

import com.mongodb.*;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import java.util.List;

public class MongoDBUtil {
	private final static Logger logger = Logger.getLogger(MongoDBUtil.class);

	private MongoConnection mongoConnection = new MongoConnection();

	private DB db;

	public MongoDBUtil() {
		init();
	}

	public void init() {
		db = mongoConnection.getDB();
	}

	public void close(){
		mongoConnection.close();
	}

	/**
	 *
	 * 获取集合（表�?
	 *
	 * @param collection
	 */

	public DBCollection getCollection(String collection) {

		return db.getCollection(collection);

	}

	/**
	 *
	 * ----------------------------------分割�?----------------------------------
	 * ---
	 */

	/**
	 *
	 * 插入
	 *
	 * @param collection
	 *
	 * @param map
	 */

	public void insert(String collection, DBObject map) {

		try {

			DBObject dbObject = (map);

			getCollection(collection).insert(dbObject);

		} catch (MongoException e) {

			e.printStackTrace();

		}

	}

	/**
	 *
	 * 批量插入
	 *
	 * @param collection
	 *
	 * @param list
	 */

	public void insertBatch(String collection, List<DBObject> list) {

		if (list == null || list.isEmpty()) {

			return;

		}

		try {
			getCollection(collection).insert(list);

		} catch (MongoException e) {

			e.printStackTrace();

		}

	}

	/**
	 * $inc 用来增加已有键的值
	 *
	 * @param name
	 * @param set
	 * @param where
	 */
	 public void update3(String name, DBObject set, DBObject where) throws Exception {
		getCollection(name).update(where, new BasicDBObject("$inc", set), true, true);
	 }

	/**
	 * $inc 用来增加已有键的值或者$set更新，可以同时存在
	 *
	 * @param name
	 * @param set
	 * @param where
	 */
	 public WriteResult update4(String name, DBObject set, DBObject where) throws Exception {
		return getCollection(name).update(where, set, false, true);
	 }


	/**
	 *
	 * 删除
	 *
	 * @param collection
	 *
	 * @param map
	 */

	public void delete(String collection, DBObject map) {

		DBObject obj = (map);

		getCollection(collection).remove(obj);

	}

	/**
	 *
	 * 删除全部
	 *
	 * @param collection
     *
	 */

	public void deleteAll(String collection) {

		List<DBObject> rs = findAll(collection);

		if (rs != null && !rs.isEmpty()) {

			for (int i = 0; i < rs.size(); i++) {

				getCollection(collection).remove(rs.get(i));

			}

		}

	}

	/**
	 *
	 * 批量删除
	 *
	 * @param collection
	 *
	 * @param list
	 */

	public void deleteBatch(String collection, List<DBObject> list) {

		if (list == null || list.isEmpty()) {

			return;

		}

		for (int i = 0; i < list.size(); i++) {

			getCollection(collection).remove((list.get(i)));

		}

	}

	/**
	 *
	 * 计算满足条件条数
	 *
	 * @param collection
	 *
	 * @param map
	 */

	public long getCount(String collection, DBObject map) {

		return getCollection(collection).getCount((map));

	}

	/**
	 *
	 * 计算集合总条�?
	 *
	 * @param collection
	 *
	 */

	public long getCount(String collection) {

		return getCollection(collection).find().count();

	}

	/**
	 *
	 * 更新
	 *
	 * @param collection
	 *
	 * @param setFields
	 *
	 * @param whereFields
	 */

	public void update(String collection, DBObject setFields,

	DBObject whereFields) {

		DBObject obj1 = (setFields);

		DBObject obj2 = (whereFields);

		getCollection(collection).update(obj2, obj1, true, false);

	}
	/**
	 * 只更新存在的数据,不会新增. 批量更新.
	 *
	 * @param name
	 * @param set
	 * @param where
	 */
	public void update2(String name, DBObject set, DBObject where) throws Exception {

		getCollection(name).update(where, new BasicDBObject("$set", set), false, true);

	}

	/**
    *根据ID批量更新数据
    * @param collection
    * @param obj
    * @param setFields
    */
    public void updateBatchByIDs(String collection,BasicDBObject obj,BasicDBObject setFields){
    	if(obj==null||obj.toString()=="")
    		return ;

    		 BasicDBObject  destFields=new BasicDBObject();
  		     destFields.put("_id", obj);
     	     BasicDBObject doc = new BasicDBObject();
   			 doc.put("$set", setFields);
     	     getCollection(collection).update(destFields,doc,false, true);
    }
	/**
	 *根据ID批量更新数据
	 * @param collection
	 * @param obj
	 * @param setFields
	 */
	public void updateBatchByHitIds(String collection,BasicDBObject obj,BasicDBObject setFields){
		if(obj==null||obj.toString()=="")
			return ;

		BasicDBObject  destFields=new BasicDBObject();
		destFields.put("d", obj);
		BasicDBObject doc = new BasicDBObject();
		doc.put("$set", setFields);
		getCollection(collection).update(destFields,doc,false, true);
	}

	/**
	 * 根据ID批量更新数据
	 *
	 * @param collection
	 * @param ids
	 * @param setFields
	 */
	public void updateBatchByIDs(String collection, String ids, DBObject setFields) {
		if (ids == null || ids == "")
			return;

		String[] strids = ids.split(",");
		for (int i = 0; i < strids.length; i++) {
			BasicDBObject destFields = new BasicDBObject();
			destFields.put("_id", ObjectId.massageToObjectId(strids[i]));
			BasicDBObject doc = new BasicDBObject();
			doc.put("$set", setFields);
			getCollection(collection).update(destFields, doc, true, false);
		}
	}

	/**
	 *
	 * 查找对象（根据主键_id�?
	 *
	 * @param collection
	 *
	 * @param _id
	 */

	public DBObject findById(String collection, String _id) {

		DBObject obj = new BasicDBObject();

		obj.put("_id", ObjectId.massageToObjectId(_id));

		return getCollection(collection).findOne(obj);

	}

	/**
	 *
	 * 查找集合�?��对象
	 *
	 * @param collection
	 */

	public List<DBObject> findAll(String collection) {

		return getCollection(collection).find().toArray();

	}

	/**
	 *
	 * 查找（返回一个对象）
	 *
	 * @param map
	 *
	 * @param collection
	 */

	public DBObject findOne(String collection, DBObject map) {

		DBCollection coll = getCollection(collection);

		return coll.findOne((map));

	}

	/**
	 *
	 * 查找（返回一个List<DBObject>
	 * @param map
	 * @param collection
	 * @throws Exception
	 */

	public List<DBObject> find(String collection, DBObject map) throws Exception {

		DBCollection coll = getCollection(collection);

		DBCursor c = coll.find((map));

		if (c != null)
			return c.toArray();
		else
			return null;

	}

	public int countTotal(String collection, DBObject queryMap) {
		DBCollection coll = getCollection(collection);
		int num = coll.find(queryMap).count();
		return num;
	}

	public List<DBObject> findPageList(String collection, DBObject queryMap, DBObject orderbyMap, int pageIndex, int pageSize) {
		DBCollection coll = getCollection(collection);
		DBCursor c = coll.find(queryMap).sort(orderbyMap).skip(pageIndex).limit(pageSize);
		if (c != null) {
			return c.toArray();
		}
		return null;
	};

	public List<DBObject> find(String collection, DBObject map, int limit) throws Exception {
		long starttime = System.currentTimeMillis();
		DBCollection coll = getCollection(collection);
		DBCursor c = coll.find((map)).limit(limit);
		if (c != null) {
			return c.toArray();
		} else {
			return null;
		}

	}

	/**
	 * 【重号过滤使用分组】
	 * @param collection
	 * @param queryMap
	 * @param cond
	 * @param initial
	 * @return
	 */
	public Iterable<DBObject> findRepeatGroup(String collection, DBObject queryMap, DBObject cond, DBObject initial) {
		DBCollection coll = getCollection(collection);
		AggregationOutput c = coll.aggregate(queryMap, cond, initial);
		return c.results();
	}


	public DBObject group(String collection, DBObject key, DBObject cond, DBObject initial, String reduce) {
		DBCollection coll = getCollection(collection);
		return coll.group(key, cond, initial, reduce);
	}
}
