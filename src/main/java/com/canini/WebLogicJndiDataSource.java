package com.canini;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class WebLogicJndiDataSource {

	public static void main(String[] args) {
		 Connection conn=null;
		    javax.sql.DataSource ds=null;
		    Hashtable env = new Hashtable();
		    env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
		    env.put(Context.PROVIDER_URL, "t3://localhost:7001");

		    try{
		      Context context=new InitialContext( env );
		      //you will need to have create a Data Source with JNDI name testDS
		      ds=(javax.sql.DataSource) context.lookup ("jdbc/test");
		      conn=(Connection) ds.getConnection();
		      java.util.Properties prop = new java.util.Properties();
		      System.out.println("Connection object details : "+conn);
		       String sql="select * from root.TESTTABLE";
		       Statement st=conn.createStatement();

		       ResultSet rs=st.executeQuery(sql);
		       while(rs.next()){
				   System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
			   }
		       
		      conn.close();
		    }catch(Exception ex){
		      //handle the exception
		      ex.printStackTrace();
		    }
	}

}
