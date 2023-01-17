package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DeleteDto;
import dto.DtoKadai1601;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class DaoKadai1601 {

	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static int registerAccount(DtoKadai1601 account) {
		String sql = "INSERT into login VALUES(?, ?, ?, ?, ?, ? ,? ,?)";
		int result = 0;
		
		String salt = GenerateSalt.getSalt(32);
		String hashedPw = GenerateHashedPw.getSafetyPassword(account.getPassword(), salt);
		
		System.out.println("ハッシュに使うソルトは"+salt);
		System.out.println("登録されるハッシュ済みのPWは"+hashedPw);
		
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, account.getId());
			pstmt.setString(2, account.getName());
			pstmt.setInt(3, account.getAge());
			pstmt.setString(4,account.getGender());
			pstmt.setString(5,account.getTel());
			pstmt.setString(6, account.getMail());
			pstmt.setString(7, salt);
			pstmt.setString(8, hashedPw);
			

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	
	public static List<DtoKadai1601>SelectAllUser(){
		List<DtoKadai1601> result = new ArrayList<>();
		String sql ="select*from login";
		
		try ( 
				Connection con =getConnection();
				PreparedStatement pstmt=con.prepareStatement(sql);
				){
			
			try(ResultSet rs =pstmt.executeQuery()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String name=rs.getString("name");
					int age=rs.getInt("age");
					String gender=rs.getString("gender");
					
				    DtoKadai1601 selectAll= new DtoKadai1601(id,name,age,gender, gender, gender, gender, gender, gender);
					
					result.add(selectAll);
				}
			}
	}catch (SQLException e) {
		e.printStackTrace();
	}catch (URISyntaxException e) {
		e.printStackTrace();
	}
	
	return result;

	}
	
	public static int deleteUser(DeleteDto del) {
		   
		String sql = "DELETE FROM login WHERE id = ?";
		
		int result = 0;

		try (
				Connection con =getConnection();	
				PreparedStatement pstmt = con.prepareStatement(sql);		
				){
			
			pstmt.setInt(1, del.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件削除しました。");
		}
		return result;
 }
	public static String getSalt(String mail) {
		String sql="SELECT salt FROM login WHERE mail=?";
		  try (
			  Connection con=getConnection();
			  PreparedStatement pstmt= con.prepareStatement(sql);
		  ){
			  pstmt.setString(1,mail);
			  
			  try (ResultSet rs=pstmt.executeQuery()){
				  if(rs.next()) {
					  String salt =rs.getString("salt");
					  return salt;
				  }
			  }
		  } catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null; 
		  }
	    public static DtoKadai1601 login(String mail,String hashedPw) {
	    	String sql="SELECT * FROM login WHERE mail =? AND password=?";
	    	
	    	try(
	    			Connection con =getConnection();
	    			PreparedStatement pstmt =con.prepareStatement(sql);
	    			){
	    		pstmt.setString(1,mail);
	    		pstmt.setString(2, hashedPw);
	    		
	    		try(ResultSet rs=pstmt.executeQuery()){
	    			if(rs.next()) {
	    				int id = rs.getInt("id");
	    				String name=rs.getString("name");
	    				int age=rs.getInt("age");
	    				String salt=rs.getString("salt");
	    				
	    				return new DtoKadai1601(id,name,age, salt,null,null,null,null,null);
	    			}
	    		}
	    	} catch (SQLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
	    	}
	    }
	
	
