import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Initializer {
	
	// a helper class for the initializing people,food and sport objects from the txt files in a map
	public Map<Integer, People> initilize_Peoples(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner sc = new Scanner(file);     
		sc.useDelimiter("\\Z");
		Map<Integer , People> people_map = new HashMap<Integer, People>();
		while (sc.hasNextLine()) {
			String[] line_arr = sc.nextLine().split("\t");
			int ID_of_people = Integer.parseInt(line_arr[0]);
			String name = line_arr[1];
			String gender = line_arr[2];
			int weight = Integer.parseInt(line_arr[3]);
			int height = Integer.parseInt(line_arr[4]);
			int date_of_birth = Integer.parseInt(line_arr[5]);
			People peoples = new People(ID_of_people, name, gender, weight, height,date_of_birth);
			people_map.put(ID_of_people, peoples);
		}
		sc.close();
		return people_map;
		
	}public Map<Integer , Food> Initilize_Foods(String path) throws FileNotFoundException{
		File file = new File(path);
		Scanner sc = new Scanner(file);     
		sc.useDelimiter("\\Z");
		Map<Integer , Food> food_map = new HashMap<Integer , Food>();
		while (sc.hasNextLine()) {
			String[] line_arr = sc.nextLine().split("\t");
			int ID_of_food = Integer.parseInt(line_arr[0]);
			String name_of_food = line_arr[1];
			int cal_count = Integer.parseInt(line_arr[2]);
			Food foods = new Food(ID_of_food, name_of_food, cal_count);
			food_map.put(ID_of_food, foods);
		}
		sc.close();
		return food_map;
		
	}public Map<Integer , Sport> Initilize_Sports(String path) throws FileNotFoundException{
		File file = new File(path);
		Scanner sc = new Scanner(file);     
		sc.useDelimiter("\\Z");
		Map<Integer , Sport> sport_map = new HashMap<Integer , Sport>();
		while (sc.hasNextLine()) {
			String[] line_arr = sc.nextLine().split("\t");
			int ID_of_sport = Integer.parseInt(line_arr[0]);
			String name_of_sport = line_arr[1];
			int cal_burned = Integer.parseInt(line_arr[2]);
			Sport sports = new Sport(ID_of_sport, name_of_sport, cal_burned);
			sport_map.put(ID_of_sport, sports);
		}
		sc.close();
		return sport_map;
	}
}
