package config;

import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {

	private static SqlMapClient smc = null;
	
	static {
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset); 
			Reader rd = Resources.getResourceAsReader("/config/sqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
		} catch (Exception e) {
			System.out.println("iBatis 환경 설정 오류");
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapClient() {
		return smc;
	}
	//SqlMapClient 객체가 필요한 곳에서 호출
	
}
