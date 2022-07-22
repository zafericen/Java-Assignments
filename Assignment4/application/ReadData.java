package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

import javafx.scene.Scene;

public class ReadData{
	public static ArrayList<Users> userList = new ArrayList<Users>();
	public static ArrayList<Halls> hallList = new ArrayList<Halls>();
	public static ArrayList<Films> filmList = new ArrayList<Films>();
	public static String userStatus[] = new String[2];
	public static HashMap<String,String> properties = new HashMap<String,String>();
	
	public static String hashPassword(String password) {
        byte[] bytesOfPassword = password.getBytes(StandardCharsets.UTF_8);
        byte[] md5Digest;
        try {
            md5Digest = MessageDigest.getInstance("MD5").digest(bytesOfPassword);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return Base64.getEncoder().encodeToString(md5Digest);
    }
	public static void readTheData(String path) throws FileNotFoundException {
		
		File datafile=new File(path);
	    Scanner sc = new Scanner(datafile);
	    while(sc.hasNextLine()){
	        String line=sc.nextLine();
	        String[] subLine=line.split("\t");
	        switch (subLine[0]){
	            case "user":
	                saveUser(subLine);
	                break;
	            case "film":
	                saveFilm(subLine);
	                break;
	            case "hall":
	                saveHall(subLine);
	                break;
	            case "seat":
	                saveSeat(subLine);
	                break;
	        }
	    
	    }sc.close();
		
	}
	
	public static void readTheProperties(String path) throws FileNotFoundException {
		File datafile=new File(path);
	    Scanner sc = new Scanner(datafile);
	    while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split("=");
			try {
				properties.put(line[0], line[1]);
			} catch (Exception e) {
				continue;
			}
			
		}
	}
	
	public static Films getFilm(String filmName){
		Films film = new Films(filmName, "0", 0);
        int index = filmList.indexOf(film);
        film = filmList.get(index);
        return film;
	}
	public static Users getUser(String userName){
		Users user = new Users(userName, "0", false, false);
        int index = userList.indexOf(user);
        try {
        	user = userList.get(index);
            return user;
		} catch (Exception e) {
			return null;
		}
        
	}
	public static Halls getHall(String hallName){
		Halls hall = new Halls(hallName, 0, 0, 0);
        int index = hallList.indexOf(hall);
        hall = hallList.get(index);
        return hall;
	}
	
	
    private static void saveUser(String[] subLine){
        Users user=new Users(subLine[1],subLine[2],subLine[3].equals("true"),subLine[4].equals("true"));
        userList.add(user);
    }
    private static void saveFilm(String[] subLine){
        Films film = new Films(subLine[1],subLine[2],Integer.parseInt(subLine[3]));
    	filmList.add(film);
        
    }
    private static void saveHall(String[] subLine){
        Films film=getFilm(subLine[1]);
        Halls hall=new Halls(subLine[2],Integer.parseInt(subLine[3]),Integer.parseInt(subLine[4]),Integer.parseInt(subLine[5]));
        film.getHallList().add(hall);
        hall.setFilm(film);
        hallList.add(hall);
    }
    private static void saveSeat(String[] subLine){
        Films film = getFilm(subLine[1]);
        Halls hall= getHall(subLine[2]);
        Users user=getUser(subLine[5]);
        int row=Integer.parseInt(subLine[3]);
        int column=Integer.parseInt(subLine[4]);
        int money=Integer.parseInt(subLine[6]);
        hall.addSeat(row,column,new Seats(film, hall, user, row, column, money));
    }

}
