package com.example;
import java.sql.*;

public class DBconnect {
	  private static Connection getDBConnection() {
	        Connection dbConnection = null;
	        try {
	          Class.forName("org.h2.Driver");
	          
	             
	        } catch (ClassNotFoundException e) {
	            System.out.println(e.getMessage());
	        }
	        try {
	            dbConnection =  DriverManager.getConnection("jdbc:h2:~/test", "", "");
	            return dbConnection;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return dbConnection;
	    }

    static String insertWithStatement(String shopName,String shopAddress,String sLat,String sLong) throws SQLException {
        Connection connection = getDBConnection();
        String LastsName,lastsAddress=null;
        Statement stmt = null;
       String[] shopNames=null;
        String status=null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE SHOPDETAILS(name varchar(25) primary key,Address varchar(255),Lat varchar(25),Long varchar(25))");
            ResultSet rs = stmt.executeQuery("select * from SHOPDETAILS ");
            if(rs== null)
            {
            	stmt.execute("INSERT INTO SHOPDETAILS(name,Address,Lat,Long) VALUES('"+shopName+"','"+shopAddress+"','"+sLat+"','"+sLong+"')");
            	
            }
            else{
            shopNames=(String[])rs.getArray("name").getArray();
            for(int i=0;i<shopNames.length;i++){
            	if (shopNames[i].equals(shopName))
            	{
            		LastsName=shopNames[i];
            		lastsAddress=rs.getString("Address");
            		
            		int k=stmt.executeUpdate("UPDATE TABLE SHOPDETAILS SET NAME="+shopName+",Adress='"+shopAddress+"',Lat='"+sLat+"',Long='"+sLong+"'");
            		status="Shop details updated Successfully!! \n Previous Shop Name:"+LastsName+"Last Address:"+lastsAddress;
            	}
            }
            }
                      
            
            
          
           
            stmt.execute("DROP TABLE SHOP");
            stmt.close();
            connection.commit();
          
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
        }
        
        return  status;
    }

}
