package Utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/*
 * This class is to read sql string from XML files
 * Public entrance: 
 * 			-Input: sql function name
 * 			-return: sql query
 * 
 * Private method:
 * 			-input: sql method name
 * 			-return: sql query string
 */
public class XMLReaderUtil {
	private static Document document;
	private static Map<String,String> sqlMap = new HashMap<>();
	private static Map<String,String> sqlInfoMap = new HashMap<>();
	
	static {
		try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("MySql.xml")
				){
			if(in == null) throw new RuntimeException("Cannot find properties file...");
		// read XML file data into document
		SAXReader sr = new SAXReader();
		document = sr.read(in);
		
		// get data from document and put into Map structure
		putDataIntoMap();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void putDataIntoMap() {
		@SuppressWarnings("unchecked")
		List<Node> sqlList = (List<Node>)document.selectNodes("sqls//sql");
		@SuppressWarnings("unchecked")
		List<Node> sqlInfoList = (List<Node>)document.selectNodes("sqls//info");
		for(Node node:sqlList) {
			String key = node.valueOf("@key");
			String value = node.valueOf("@value");
			sqlMap.put(key,value);
		}
		
		for(Node node:sqlInfoList) {
			String key = node.valueOf("@key");
			String value = node.valueOf("@value");
			sqlInfoMap.put(key, value);
		}
	}
	
	public static String getSqlQuery(String methodName) {
		return sqlMap.get(methodName);
	}
	
	public static String getSQLInfo(String key) {
		return sqlInfoMap.get(key);
	}
}
