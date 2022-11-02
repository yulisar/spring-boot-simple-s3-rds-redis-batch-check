package com.example.springboot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

//import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.Bucket;
//import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
//import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

//@SpringBootApplication
//@EnableScheduling
public class Application2 implements ApplicationRunner {
	
	private static Logger log = LogManager.getLogger(Application2.class);
	
	private static final AmazonS3 s3Client = AmazonS3Client.builder()
			.withCredentials(new DefaultAWSCredentialsProviderChain())
			.withRegion(Regions.AP_SOUTHEAST_1)
			.build();
	
//	private static ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
//	private static Region region = Region.AP_SOUTHEAST_1;
//	private static S3Client s3Client = S3Client.builder().region(region).credentialsProvider(credentialsProvider).build();
	
	private static String urldb = "devmportalocbcsg.cjtt8eu0tpq1.ap-southeast-1.rds.amazonaws.com";
//	private static String urldb = "localhost";
	private static String dbId = "mp_dev";

	private static String jdbcUrl = "jdbc:postgresql://" + urldb + "/" + dbId;
	private static String username = "postgres";
	private static String pass = "Pass1234!!";
//	String pass = "sapassword";
	private static int port = 5432;
	
//	private static ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();

	public static void main(String[] args) {
		SpringApplication.run(Application2.class, args);
	}
	
	private void checkS3AndDB() {
		log.info("========================================");
		log.info("handle Request started.");
		log.info("Check if are getting any record");
		
		log.info("Check creadentials:");
		
		log.info(s3Client.getS3AccountOwner().getDisplayName());
		log.info(s3Client.getRegion().toString());
		
		log.info("=================================");
		log.info("List existing buckets.");
		for (Bucket s : s3Client.listBuckets()) {
			log.info(s.getName());
		}	
		
		log.info("=================================");
		log.info("Get DB list:");
		
		log.info("url: {}", urldb);
		log.info("port: {}", port);
		
		log.info("Getting Connection to DB..");
		
		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", pass);

		try {
			Connection conn = DriverManager.getConnection(jdbcUrl, props);
			
//			String INSERT_FILENAME_SQL = "INSERT INTO dbo.filenames (name) VALUES (?);";
			String SELECT_ALL_DB = "SELECT datname FROM pg_database;";

			if (conn != null) {
				log.info("Connected to the database!");
				
//				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_FILENAME_SQL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL_DB);
				
			      while ( rs.next() ) {
			          String  title = rs.getString("datname");
			          log.info("datname: {} ",title);
			       }
				
				rs.close();
//				preparedStatement.close();
//				preparedStatement
				conn.close();
			} else {
				log.info("Failed to make connection!");
			}

		} catch (SQLException e) {
			log.info(e.getSQLState() + "--" + e.getMessage());
		} catch (Exception e) {
			log.info(e.toString());
		}
		
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		checkS3AndDB();
		
//		log.info("========================================");
//		log.info("handle Request started.");
//		log.info("Check if are getting any record");
//		
//		log.info("Check creadentials:");
//		
//		log.info(s3Client.getS3AccountOwner().getDisplayName());
//		log.info(s3Client.getRegion().toString());
//		
//		log.info("=================================");
//		log.info("List existing buckets.");
//		for (Bucket s : s3Client.listBuckets()) {
//			log.info(s.getName());
//		}	
//		
////		ListBucketsResponse listBucketsResponse = s3Client.listBuckets(listBucketsRequest);
////		List<Bucket> lBucket = listBucketsResponse.buckets();
////		log.info("==============================");
////		log.info("List All Bucket.");
////		int nbucket = lBucket.size();
////		log.info("Found {} bucket(s).", nbucket);
////		lBucket.stream().forEach(x -> {
////			log.info("Bucket name : {}", x.name());
////		});
////		
//		log.info("=================================");
//		log.info("Get DB list:");
//		
//		log.info("url: {}", urldb);
//		log.info("port: {}", port);
//		
//		log.info("Getting Connection to DB..");
//		
//		Properties props = new Properties();
//		props.setProperty("user", username);
//		props.setProperty("password", pass);
////		props.setProperty("connectTimeout", "5000");
////		props.setProperty("options", "-c connectTimeout=5");
//
//		
////		int timeout = 5000;
//
//		try {
////			DriverManager.setLoginTimeout(timeout);
////			DriverManager.connec  //			log.info("Time Out set to: {} ms." , timeout);
////			Connection conn = DriverManager.getConnection(jdbcUrl, username, pass);
//			Connection conn = DriverManager.getConnection(jdbcUrl, props);
//			
////			String INSERT_FILENAME_SQL = "INSERT INTO dbo.filenames (name) VALUES (?);";
//			String SELECT_ALL_DB = "SELECT datname FROM pg_database;";
//
//			if (conn != null) {
//				log.info("Connected to the database!");
//				
////				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_FILENAME_SQL);
//				
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(SELECT_ALL_DB);
//				
//			      while ( rs.next() ) {
//			          String  title = rs.getString("datname");
//			          log.info("datname: {} ",title);
//			       }
//				
//				rs.close();
////				preparedStatement.close();
//			      
//				conn.close();
//			} else {
//				log.info("Failed to make connection!");
//			}
//
//		} catch (SQLException e) {
//			log.info(e.getSQLState() + "--" + e.getMessage());
//		} catch (Exception e) {
//			log.info(e.toString());
//		}

	}




}
