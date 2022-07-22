package application;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EditUsers extends BaseScene {
	
    public TableView<Users> userTable = new TableView<Users>();
    public Button switchClubMemberStatus = new Button();
    public Button switchAdminStatus=new Button();
    public Button backButton=new Button();
	
    public EditUsers() {
    	setName("editUsers");
		setRoot(new Group());
		setMainScene(new Scene(getRoot(),440,470));
		
		initializeUserTable();
		getRoot().getChildren().add(userTable);
		
		initialize(switchClubMemberStatus, getRoot(), 80, 430,"Promote/Demote Club Member");
		
		initialize(switchAdminStatus, getRoot(), 275, 430,"Promote/Demote Admin");
		
		initialize(backButton, getRoot(), 20, 430,"◀ Back");
    }
    
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource()==backButton){
            AdminWelcome adminWelcome = new AdminWelcome();
            setMainStageScene(adminWelcome.getMainScene());
        }
		else if(event.getSource()==switchAdminStatus){
        	 Users selectedUser=userTable.getSelectionModel().getSelectedItem();
                selectedUser.setIsAdmin(!selectedUser.getIsAdmin());;
         }
         else if(event.getSource()==switchClubMemberStatus){
        	 Users selectedUser=userTable.getSelectionModel().getSelectedItem();
             selectedUser.setIsClubMember(!selectedUser.getIsClubMember());
         }
         userTable.refresh();

     }
    
		
	
	public void initializeUserTable() {
		userTable=new TableView<>();

        TableColumn<Users,String> nameColumn=new TableColumn<>("UserName");
        PropertyValueFactory<Users, String> pValueFactory = new PropertyValueFactory<Users, String>("name");
        nameColumn.setCellValueFactory(pValueFactory);
        nameColumn.setPrefWidth(100);
        userTable.getColumns().add(nameColumn);

        TableColumn<Users,Boolean> clubMemberColumn=new TableColumn<>("Club Member");
        PropertyValueFactory<Users, Boolean> pFactory = new PropertyValueFactory<Users, Boolean>("isClubMember");
        clubMemberColumn.setCellValueFactory(pFactory);
        clubMemberColumn.setPrefWidth(90);
        userTable.getColumns().add(clubMemberColumn);

        TableColumn<Users,Boolean> adminColumn=new TableColumn<>("Admin");
        PropertyValueFactory<Users, Boolean> sFactory = new PropertyValueFactory<Users, Boolean>("isAdmin");
        adminColumn.setCellValueFactory(sFactory);
        adminColumn.setPrefWidth(60);
        userTable.getColumns().add(adminColumn);

        TableColumn<Users,Boolean> empty=new TableColumn<>("");
        empty.setPrefWidth(140);
        userTable.getColumns().add(empty);
        
        userTable.getItems().addAll(ReadData.userList);
        userTable.getItems().remove(new Users(ReadData.userStatus[0], getName(), false, false));

        userTable.getSelectionModel().selectFirst();
	}
	

}
