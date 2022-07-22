import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Initializer initialize = new Initializer();
		// maps containing objects
		Map<Integer, People> people_map = initialize.initilize_Peoples("people.txt");
		Map<Integer, Food> food_map = initialize.Initilize_Foods("food.txt");
		Map<Integer, Sport> sport_map = initialize.Initilize_Sports("sport.txt");
		
		//creating file and file reader
		File file = new File(args[0]);
		Scanner sc = new Scanner(file);     
		sc.useDelimiter("\\Z");
		File outputfile= new File("monitoring.txt");
	    FileWriter writer=new FileWriter("monitoring.txt");
	    
	    //final string that will be written to file
	    String final_string = new String();
	    
	    //array of sorted people IDs that appeared in command.txt
	    ArrayList<People> command_people = new ArrayList<People>();
	    
	    while (sc.hasNextLine()) {
			String[] line_arr = sc.nextLine().split("\t");
			
			//conditions controlling whether a line is a print function line or an information line
			if (line_arr.length == 3 ) {
				People person = people_map.get(Integer.valueOf(line_arr[0]));
				
				//adding IDs to array
				if (!command_people.contains(person)) {
					command_people.add(person);
				}
				//conditions controlling whether a person have done an exercise or eaten a food
				if (line_arr[1].substring(0, 2).equals("20")) {
					
					// to get the object
					Sport exercise = sport_map.get(Integer.valueOf(line_arr[1]));
					int cal_burned = exercise.getCal_burned_hourly(Integer.parseInt(line_arr[2]));
					person.Cal_loss(cal_burned);
					String tempString = person.getID()+"\thas\tburned\t"+cal_burned+
							"kcal\tthanks to\t"+exercise.getName_of_sport()+"\n***************\n";
					final_string = final_string + tempString;
				}else {
					
					//to get the object
					Food eaten_Food = food_map.get(Integer.valueOf(line_arr[1]));
					int cal_taken = eaten_Food.getCal_count()*Integer.parseInt(line_arr[2]);
					person.Cal_add(cal_taken);
					String tempString = person.getID() + "\thas\ttaken\t"+cal_taken+
							"kcal\tfrom\t"+eaten_Food.getName_of_food()+"\n***************\n";
					final_string = final_string + tempString;
				}
			}else if (line_arr.length == 1) {
				
				//conditions controlling print functions
				if (line_arr[0].equals("printList")) {
					for (People p : command_people) {
						final_string = final_string + p.PrintInfo();
					}
					//adding stars outside from the for-loop
					final_string = final_string + "***************\n";
				}else if (line_arr[0].equals("printWarn")) {
					
					// to control the condition when there is no person to be warned
					int control_number = 0;
					for (People p : command_people) {
						if (p.getCal_total() > 0) {
							final_string = final_string + p.PrintInfo();
							
							//if a person gets a warning control number increases 
							control_number++;
						}
					}
					//if number never increase then there is no person to be warned
					if (control_number == 0) {
						final_string = final_string + "There\tis\tno\tsuch\tperson\n";
					}final_string = final_string + "***************\n";
				}else {
					
					//to get the ID of a person
					Integer id = Integer.valueOf(line_arr[0].substring(6,11));
					final_string = final_string + people_map.get(id).PrintInfo() +"***************\n";
				}
			}
		}
	    //for striping last line of star let by the upper processes
	    StringBuffer temp_form = new StringBuffer(final_string);
		temp_form.replace(final_string.length()-17, final_string.length(), "");
	    final_string = temp_form.toString();
		
	    //writing the final string and closing
	    writer.write(final_string);
		sc.close();
		writer.close();
	}
}
