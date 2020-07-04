package ManageInventory;
 
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.chart.*;

public class ManageInventory extends Application {
    
	//Global Variables
	
	//Panes
	StackPane spn_Root = new StackPane();
	GridPane gpn_Login = new GridPane();
	GridPane gpn_LoginTop = new GridPane();
	GridPane gpn_LoginMiddle = new GridPane();
	GridPane gpn_LoginBottom = new GridPane();
	GridPane gpn_Register = new GridPane();
	StackPane spn_Manage = new StackPane();
	
	//Login Pane Attributes
    Label lbl_WelcomeMsg = new Label("          WELCOME TO INVENTORY MANAGEMENT        \n\n");
	Button btn_SignIn = new Button("Login");
    Button btn_Register = new Button("Register");
    Label lbl_UserName = new Label("User Name:");
    Label lbl_Password = new Label("Password:");
    TextField txt_UserName = new TextField();
    PasswordField pwd_Password = new PasswordField();
    Label lbl_ErrMessage = new Label("");
    
    //Register Pane Attributes
    Label lbl_NewUserName = new Label("User Name:");
    Label lbl_NewPassword = new Label("Password:");
    Label lbl_NewEmail = new Label("Email:");
    TextField txt_NewUserName = new TextField();
    PasswordField pwd_NewPassword = new PasswordField();
    TextField txt_NewEmail = new TextField();
    Button btn_NewRegister = new Button("Register");
   
    //Manage Pane 
    int ItemID = 1;
    
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
        //primaryStage and root pane
        primaryStage.setTitle("Inventory Management");
        primaryStage.show();  
        primaryStage.setScene(new Scene(spn_Root, 301, 220));
        spn_Root.setStyle("-fx-background-color: #ffffff");
        spn_Root.getChildren().add(gpn_Login);
    	
        //Login, Register and Manage Pane
        fn_LoginPane(primaryStage);
        fn_RegisterPane();
        fn_ManagePane();       
    }
    
    public void fn_LoginPane(Stage primaryStage)
    {
        //Add Login Pane
        gpn_Login.add(gpn_LoginTop, 0, 0);
        gpn_Login.add(gpn_LoginMiddle, 0, 1);
        gpn_Login.add(gpn_LoginBottom, 0, 2);
        
        //Add Attributes
        gpn_LoginTop.add(lbl_WelcomeMsg, 0, 0);
        gpn_LoginMiddle.add(lbl_UserName, 0, 0);
        gpn_LoginMiddle.add(txt_UserName, 1, 0);
        gpn_LoginMiddle.add(lbl_Password, 0, 1);  
        gpn_LoginMiddle.add(pwd_Password, 1, 1);
        gpn_LoginMiddle.add(btn_SignIn, 0, 2);
        gpn_LoginMiddle.add(btn_Register, 1, 2);
        gpn_LoginBottom.add(lbl_ErrMessage, 0, 0);
        
        //Set CSS
        lbl_WelcomeMsg.setTextFill(Color.rgb(3, 90, 100));
        lbl_WelcomeMsg.setStyle("-fx-font-weight: bold");
        lbl_ErrMessage.setTextFill(Color.rgb(210, 39, 30));
        gpn_LoginMiddle.setStyle("-fx-background-color: #ffffff");
        gpn_LoginMiddle.setAlignment(Pos.CENTER);
        gpn_LoginMiddle.setHgap(10);
        gpn_LoginMiddle.setVgap(10);
        gpn_LoginMiddle.setPadding(new Insets(25, 25, 25, 25));
        
        //Login Button
        btn_SignIn.setOnAction(new EventHandler<ActionEvent>() {      	
            @Override
            public void handle(ActionEvent event) {
            	
            	//Read from file: users.txt
            	ArrayList<String> str_RegisterInfo=new ArrayList<String>();
            	try (BufferedReader reader = new BufferedReader(new FileReader(new File("users.txt")))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                    	str_RegisterInfo.add(line);
                    }                    

                } catch (IOException e) {
                    e.printStackTrace();
                }
            
                //Login
            	if(!txt_UserName.getText().equals(str_RegisterInfo.get(0)) || !pwd_Password.getText().equals(str_RegisterInfo.get(1)))
            	{
            		lbl_ErrMessage.setText("                  Login is incorrect, try again. \n\n");
            		txt_UserName.clear();
                	pwd_Password.clear();
            	}
            	else
            	{
            		gpn_Login.setVisible(false);
                    primaryStage.setWidth(427);
                    primaryStage.setHeight(400);
            		spn_Root.getChildren().add(spn_Manage);
                	lbl_ErrMessage.setText("");
            	}         	
            }          
        });
    
        //Register Button
        btn_Register.setOnAction(new EventHandler<ActionEvent>() {       	
            @Override
            public void handle(ActionEvent event) {
            	gpn_Login.setVisible(false);
        		spn_Root.getChildren().add(gpn_Register);
        		lbl_WelcomeMsg.setText("");
        		spn_Root.setStyle("-fx-background-color: #ffffff");
            }         
        });     
    }
    
    public void fn_RegisterPane()
    {
    	//set Register Pane
        gpn_Register.setAlignment(Pos.TOP_CENTER);
        gpn_Register.setHgap(10);
        gpn_Register.setVgap(10);
        gpn_Register.setPadding(new Insets(25, 25, 25, 25));
        gpn_Register.setStyle("-fx-background-color: #ffffff");
         
        //Add Attributes
        gpn_Register.add(lbl_NewUserName, 0, 0);
        gpn_Register.add(txt_NewUserName, 1, 0);
        gpn_Register.add(lbl_NewPassword, 0, 1);  
        gpn_Register.add(pwd_NewPassword, 1, 1);
        gpn_Register.add(lbl_NewEmail, 0, 2);  
        gpn_Register.add(txt_NewEmail, 1, 2);
        gpn_Register.add(btn_NewRegister, 1, 3);
        
        //Register Button
        btn_NewRegister.setOnAction(new EventHandler<ActionEvent>() {
        	
            @Override
            public void handle(ActionEvent event) {
            	
            	 //Save to file
            	 try (BufferedWriter bw = new BufferedWriter(new PrintWriter("users.txt"))) {
                     bw.write(txt_NewUserName.getText());
                     bw.newLine();
                     bw.write(pwd_NewPassword.getText());
                     bw.newLine();
                     bw.write(txt_NewEmail.getText());
                 } catch (IOException e) {
                     e.printStackTrace();
                 }   
            	 
                 //Clear Attributes
            	 txt_NewUserName.clear();
            	 pwd_NewPassword.clear();
            	 txt_NewEmail.clear();
            	 lbl_ErrMessage.setText("");
            	 
            	 //Switch Pane
         		 gpn_Register.setVisible(false);
         		 gpn_Login.setVisible(true);
            }
        });
    }
    
    public void fn_ManagePane()
    {     	
    	//Add Inventory   	
    	Label lbl_ItemCategory = new Label("Item Category:");
    	ChoiceBox<String> cbx_ItemCategory = new ChoiceBox<String>(FXCollections.observableArrayList("Books", "Clothing", "Electronics", "Shoes", "Toys"));
    	Label lbl_ItemName = new Label("Item Name:");
    	TextField txt_ItemName = new TextField ();
    	Label lbl_ItemCost = new Label("Item Cost ($):");
    	TextField txt_ItemCost = new TextField ();
    	Label lbl_ItemPrice = new Label("Item Price ($):");
    	TextField txt_ItemPrice = new TextField ();
    	Label lbl_ItemWeight = new Label("Item Weight (oz):");
    	TextField txt_ItemWeight = new TextField ();
    	Label lbl_PurchaseDate = new Label("Purchase Date:");
    	DatePicker dpr_PurchaseDate = new DatePicker();
    	Button btn_AddInventory = new Button("Add Item");
        Button btn_ClearInventoryField = new Button("Clear");
        
        GridPane gpn_AddInventory = new GridPane();
        gpn_AddInventory.setPadding(new Insets(10, 10, 10, 10));
        gpn_AddInventory.setVgap(5);
        gpn_AddInventory.setHgap(5);
        
        gpn_AddInventory.add(lbl_ItemCategory, 0, 0);
        gpn_AddInventory.add(cbx_ItemCategory, 1, 0);
        gpn_AddInventory.add(lbl_ItemName, 0, 1);
        gpn_AddInventory.add(txt_ItemName, 1, 1);
        gpn_AddInventory.add(lbl_ItemCost, 0, 2);
        gpn_AddInventory.add(txt_ItemCost, 1, 2);
        gpn_AddInventory.add(lbl_ItemPrice, 0, 3);
        gpn_AddInventory.add(txt_ItemPrice, 1, 3);
        gpn_AddInventory.add(lbl_ItemWeight, 0, 4);
        gpn_AddInventory.add(txt_ItemWeight, 1, 4);
        gpn_AddInventory.add(lbl_PurchaseDate, 0, 5);
        gpn_AddInventory.add(dpr_PurchaseDate, 1, 5);
        gpn_AddInventory.add(btn_AddInventory, 0, 6);
        gpn_AddInventory.add(btn_ClearInventoryField, 1, 6);
        
    	//View Inventory
        TextField txt_SearchCategory = new TextField();
        Button btn_SearchCategory = new Button("Search By Category");
        TextField txt_SearchItemName = new TextField();
        Button btn_SearchItemName = new Button("Search By Name");
        
        GridPane gpn_ViewInventory = new GridPane();
        GridPane gpn_ViewInventoryTop = new GridPane();
        
        gpn_ViewInventory.add(gpn_ViewInventoryTop, 0, 0);     
        gpn_ViewInventoryTop.setPadding(new Insets(10, 10, 10, 10));
        gpn_ViewInventoryTop.setVgap(5);
        gpn_ViewInventoryTop.setHgap(5);        
        gpn_ViewInventoryTop.add(txt_SearchCategory, 0, 0);
        gpn_ViewInventoryTop.add(btn_SearchCategory, 1, 0);
        gpn_ViewInventoryTop.add(txt_SearchItemName, 0, 1);
        gpn_ViewInventoryTop.add(btn_SearchItemName, 1, 1);
        
        //Edit Inventory
        TextField txt_SearchEditItem = new TextField();
        Button btn_SearchEditItem = new Button("Search Edit Item By ID");
           
        Label lbl_EditItemCategory = new Label("Item Category:");
    	ChoiceBox<String> cbx_EditItemCategory = new ChoiceBox<String>(FXCollections.observableArrayList("Books", "Clothing", "Electronics", "Shoes", "Toys"));
    	Label lbl_EditItemName = new Label("Item Name:");
    	TextField txt_EditItemName = new TextField ();
    	Label lbl_EditItemCost = new Label("Item Cost ($):");
    	TextField txt_EditItemCost = new TextField ();
    	Label lbl_EditItemPrice = new Label("Item Price ($):");
    	TextField txt_EditItemPrice = new TextField ();
    	Label lbl_EditItemWeight = new Label("Item Weight (oz):");
    	TextField txt_EditItemWeight = new TextField ();
    	Label lbl_EditPurchaseDate = new Label("Purchase Date:");
    	DatePicker dpr_EditPurchaseDate = new DatePicker();
    	Label lbl_EditSoldDate = new Label("Sold Date:");
    	DatePicker dpr_EditSoldDate = new DatePicker();
    	Button btn_EditInventory = new Button("Edit Item");
        Button btn_ClearEditField = new Button("Clear");
        Button btn_DeleteItem = new Button("Delete Item");
               
        GridPane gpn_EditPane = new GridPane();
        GridPane gpn_EditSearchPane = new GridPane();
        GridPane gpn_EditFieldsPane = new GridPane();
        
        gpn_EditPane.add(gpn_EditSearchPane, 0, 0);
        gpn_EditSearchPane.setPadding(new Insets(10, 10, 10, 10));
        gpn_EditSearchPane.setVgap(5);
        gpn_EditSearchPane.setHgap(5);        
        gpn_EditSearchPane.add(txt_SearchEditItem, 0, 0);
        gpn_EditSearchPane.add(btn_SearchEditItem, 1, 0);
        
        //gpn_EditPane.add(gpn_EditFieldsPane, 0, 1);
        gpn_EditFieldsPane.setPadding(new Insets(10, 10, 10, 10));
        gpn_EditFieldsPane.setVgap(5);
        gpn_EditFieldsPane.setHgap(5);

        gpn_EditFieldsPane.add(lbl_EditItemCategory, 0, 0);
        gpn_EditFieldsPane.add(cbx_EditItemCategory, 1, 0);
        gpn_EditFieldsPane.add(lbl_EditItemName, 0, 1);
        gpn_EditFieldsPane.add(txt_EditItemName, 1, 1);
        gpn_EditFieldsPane.add(lbl_EditItemCost, 0, 2);
        gpn_EditFieldsPane.add(txt_EditItemCost, 1, 2);
        gpn_EditFieldsPane.add(lbl_EditItemPrice, 0, 3);
        gpn_EditFieldsPane.add(txt_EditItemPrice, 1, 3);
        gpn_EditFieldsPane.add(lbl_EditItemWeight, 0, 4);
        gpn_EditFieldsPane.add(txt_EditItemWeight, 1, 4);
        gpn_EditFieldsPane.add(lbl_EditPurchaseDate, 0, 5);
        gpn_EditFieldsPane.add(dpr_EditPurchaseDate, 1, 5);
        gpn_EditFieldsPane.add(lbl_EditSoldDate, 0, 6);
        gpn_EditFieldsPane.add(dpr_EditSoldDate, 1, 6);
        gpn_EditFieldsPane.add(btn_EditInventory, 0, 7);
        gpn_EditFieldsPane.add(btn_ClearEditField, 1, 7);
        gpn_EditFieldsPane.add(btn_DeleteItem, 2, 7);
               
        //Analyze Inventory
        Hyperlink lnk_CategoryDistribution = new Hyperlink("Category Distribution");
        Hyperlink lnk_GrossSales = new Hyperlink("Gross Sales ($)");
        Hyperlink lnk_NetProfit = new Hyperlink("Total Net Profit ($)");
        Hyperlink lnk_CostOfGoodsSold = new Hyperlink("Cost of Goods Sold ($)");
        Hyperlink lnk_AverageSellingPrice = new Hyperlink("Average Selling Price ($)");
        
        GridPane gpn_AnalyzePane = new GridPane();
        gpn_AnalyzePane.setPadding(new Insets(10, 10, 10, 10));
        
        gpn_AnalyzePane.add(lnk_CategoryDistribution, 0, 0);
        gpn_AnalyzePane.add(lnk_GrossSales, 0, 1);
        gpn_AnalyzePane.add(lnk_NetProfit, 0, 2);
        gpn_AnalyzePane.add(lnk_CostOfGoodsSold, 0, 3);
        gpn_AnalyzePane.add(lnk_AverageSellingPrice, 0, 4);
        
        
        //Tabs
    	TabPane tpn_Manage = new TabPane();
        spn_Manage.getChildren().add(tpn_Manage);
        Tab tab_AddInventory = new Tab("Add Inventory", gpn_AddInventory);
        Tab tab_ViewInventory = new Tab("View Inventory"  , gpn_ViewInventory);
        Tab tab_EditInventory = new Tab("Edit Inventory" , gpn_EditPane);
        Tab tab_AnalyzeInventory = new Tab("Analyze Inventory" , gpn_AnalyzePane);

        tpn_Manage.getTabs().add(tab_AddInventory);
        tpn_Manage.getTabs().add(tab_ViewInventory);
        tpn_Manage.getTabs().add(tab_EditInventory);
        tpn_Manage.getTabs().add(tab_AnalyzeInventory);
        tpn_Manage.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        
        
        //Auto Generate Item ID
        File fl_items=new File("items.txt");
        if ((fl_items.exists()))
        {
        	ArrayList<String[]> ItemIdList=new ArrayList<String[]>();
        	String[] ItemIdFiled = new String[6];
        	ItemIdList = fn_ItemDataFromFile();	
        	if(ItemIdList.size() == 0) 
        		System.out.println("");
        	else {
        		ItemIdFiled = ItemIdList.get(ItemIdList.size()-1);
        		ItemID = Integer.parseInt(ItemIdFiled[0]) + 1;
        	}
        }
   	 
        
		///////////////////////////
		// Add Inventory Tab
		///////////////////////////
        
        //Add New Inventory Button
        btn_AddInventory.setOnAction(new EventHandler<ActionEvent>() {
         	    	 
             @Override
             public void handle(ActionEvent event) { 

 				try ( 	 
                		FileWriter fr = new FileWriter("items.txt", true); 
                		BufferedWriter bw = new BufferedWriter(fr)) {
 						bw.write(Integer.toString(ItemID)+'|');
                	 	bw.write(cbx_ItemCategory.getValue()+'|');
                	 	bw.write(txt_ItemName.getText()+'|');
                	 	bw.write(txt_ItemCost.getText()+'|');
                	 	bw.write(txt_ItemPrice.getText()+'|');
                	 	bw.write(txt_ItemWeight.getText()+'|');
                	 	bw.write(dpr_PurchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"||");
                	 	bw.newLine();
                	 	ItemID = ItemID + 1;
                 } catch (IOException e) {
                     e.printStackTrace();
                 } 
                 
                 cbx_ItemCategory.getSelectionModel().select(-1);
                 txt_ItemName.setText("");
                 txt_ItemCost.setText("");
                 txt_ItemPrice.setText("");
                 txt_ItemWeight.setText("");
                 dpr_PurchaseDate.setValue(null);
             }
         });
         
         //Clear Button
         btn_ClearInventoryField.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				 cbx_ItemCategory.getSelectionModel().select(-1);
                 txt_ItemName.setText("");
                 txt_ItemCost.setText("");
                 txt_ItemPrice.setText("");
                 txt_ItemWeight.setText("");
                 dpr_PurchaseDate.setValue(null);		
			}	
         });
         
		 
     	 ///////////////////////////
     	 // View Inventory Tab
     	 ///////////////////////////
         
         btn_SearchCategory.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fn_SearchItem(1,txt_SearchCategory);
			}  	 
         });  
         
         btn_SearchItemName.setOnAction(new EventHandler<ActionEvent>() {

 			@Override
 			public void handle(ActionEvent arg0) {
 				fn_SearchItem(2,txt_SearchItemName);
 			} 
          });  
         
         
		 ///////////////////////////
		 // Edit Inventory Tab
		 ///////////////////////////
                      
         //Edit Search Button
         btn_SearchEditItem.setOnAction(new EventHandler<ActionEvent>() {

 			@Override
 			public void handle(ActionEvent arg0) {
				
 				int int_ItemId = Integer.parseInt(txt_SearchEditItem.getText());
	    		ArrayList<String[]> ItemList=new ArrayList<String[]>();
	    		ItemList = fn_ItemDataFromFile();				
				String[] ItemArray = new String[8];
		    	for (int i = 0; i < ItemList.size(); i++) {
		    		ItemArray = ItemList.get(i); 
		    		if(!ItemArray[0].isEmpty() && Integer.parseInt(ItemArray[0]) == int_ItemId)
		    		{
		    			gpn_EditPane.add(gpn_EditFieldsPane, 0, 1);
		    			cbx_EditItemCategory.setValue(ItemArray[1]);
		    			txt_EditItemName.setText(ItemArray[2]);		    			
		    		    txt_EditItemCost.setText(ItemArray[3]);
		    	    	txt_EditItemPrice.setText(ItemArray[4]);
		    	    	txt_EditItemWeight.setText(ItemArray[5]);	    	
		    	    	dpr_EditPurchaseDate.setValue(LocalDate.parse(ItemArray[6]));
		    	    	if(!ItemArray[7].isEmpty())
		    	    		dpr_EditSoldDate.setValue(LocalDate.parse(ItemArray[7]));	    	    	
		    		}
		    	}	
 			}  	 
          }); 
         
         
         //Edit Button
         btn_EditInventory.setOnAction(new EventHandler<ActionEvent>() {
        	 
        	 @Override
  			 public void handle(ActionEvent arg0) {
        		 
        		int int_ItemId = Integer.parseInt(txt_SearchEditItem.getText());
 	    		ArrayList<String[]> ItemList=new ArrayList<String[]>();
 	    		ItemList = fn_ItemDataFromFile();				
 				String[] ItemArray = new String[8];
 				for (int i = 0; i < ItemList.size(); i++) {
		    		ItemArray = ItemList.get(i); 
		    		if(ItemArray[0]!="" && Integer.parseInt(ItemArray[0]) == int_ItemId)
		    		{
		    			ItemArray[1] = cbx_EditItemCategory.getValue();
		    			ItemArray[2] = txt_EditItemName.getText();
		    			ItemArray[3] = txt_EditItemCost.getText();
		    			ItemArray[4] = txt_EditItemPrice.getText();
		    			ItemArray[5] = txt_EditItemWeight.getText();
		    			ItemArray[6] = dpr_EditPurchaseDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		    			if(dpr_EditSoldDate.getValue() != null)
		    				ItemArray[7] = dpr_EditSoldDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		    							
		    		}
 				}
 				
 				//Clear file old content
 				try ( 	 
                		FileWriter fr = new FileWriter("items.txt", false); 
                		BufferedWriter bw = new BufferedWriter(fr)) {
 						bw.write("");
 						bw.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 } 
 				
 				//Add edited content
 				try ( 	 
                		FileWriter fr = new FileWriter("items.txt", true); 
                		BufferedWriter bw = new BufferedWriter(fr)) {
 					
 					for (int i = 0; i < ItemList.size(); i++) {
 			    		ItemArray = ItemList.get(i);
 						bw.write(ItemArray[0]+'|');
                	 	bw.write(ItemArray[1]+'|');
                	 	bw.write(ItemArray[2]+'|');
                	 	bw.write(ItemArray[3]+'|');
                	 	bw.write(ItemArray[4]+'|');
                	 	bw.write(ItemArray[5]+'|');
                	 	bw.write(ItemArray[6]+'|');
                	 	bw.write(ItemArray[7]+'|');
                	 	bw.newLine();		    		
 					}
                 } catch (IOException e) {
                     e.printStackTrace();
                 } 
 				
 				//Clear all the edit fields
 				txt_SearchEditItem.setText("");
 				cbx_EditItemCategory.setValue(null);
    			txt_EditItemName.setText("");		    			
    		    txt_EditItemCost.setText("");
    	    	txt_EditItemPrice.setText("");
    	    	txt_EditItemWeight.setText("");	    	
    	    	dpr_EditPurchaseDate.setValue(null);
    	    	dpr_EditSoldDate.setValue(null);	
 				
 				//Remove edit pane
    	    	gpn_EditPane.getChildren().remove(gpn_EditFieldsPane);
        	 }	          
         });
         
         
         //Clear Button
         btn_ClearEditField.setOnAction(new EventHandler<ActionEvent>() {
        	 
        	 @Override
  			 public void handle(ActionEvent arg0) {
        		txt_SearchEditItem.setText("");
  				cbx_EditItemCategory.setValue(null);
     			txt_EditItemName.setText("");		    			
     		    txt_EditItemCost.setText("");
     	    	txt_EditItemPrice.setText("");
     	    	txt_EditItemWeight.setText("");	    	
     	    	dpr_EditPurchaseDate.setValue(null);
     	    	dpr_EditSoldDate.setValue(null);
        	 }       	 
         });
         
         
         //Delete Button       
         btn_DeleteItem.setOnAction(new EventHandler<ActionEvent>() {
        	 
        	 @Override
  			 public void handle(ActionEvent arg0) {
        		 
        		int int_ItemId = Integer.parseInt(txt_SearchEditItem.getText());
  	    		ArrayList<String[]> ItemList=new ArrayList<String[]>();
  	    		ItemList = fn_ItemDataFromFile();				
  				String[] ItemArray = new String[8];
  				for (int i = 0; i < ItemList.size(); i++) {
 		    		ItemArray = ItemList.get(i); 
 		    		if(ItemArray[0]!="" && Integer.parseInt(ItemArray[0]) == int_ItemId)
 		    			ItemList.remove(i);
  				}
  				
 				//Clear file old content
 				try ( 	 
                		FileWriter fr = new FileWriter("items.txt", false); 
                		BufferedWriter bw = new BufferedWriter(fr)) {
 						bw.write("");
 						bw.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 } 
 				
 				//Add edited content
 				try ( 	 
                		FileWriter fr = new FileWriter("items.txt", true); 
                		BufferedWriter bw = new BufferedWriter(fr)) {
 					
 					for (int i = 0; i < ItemList.size(); i++) {
 			    		ItemArray = ItemList.get(i);
 						bw.write(ItemArray[0]+'|');
                	 	bw.write(ItemArray[1]+'|');
                	 	bw.write(ItemArray[2]+'|');
                	 	bw.write(ItemArray[3]+'|');
                	 	bw.write(ItemArray[4]+'|');
                	 	bw.write(ItemArray[5]+'|');
                	 	bw.write(ItemArray[6]+'|');
                	 	bw.write(ItemArray[7]+'|');
                	 	bw.newLine();		    		
 					}
                 } catch (IOException e) {
                     e.printStackTrace();
                 } 
 				
 				//Clear all the edit fields
 				txt_SearchEditItem.setText("");
 				cbx_EditItemCategory.setValue(null);
    			txt_EditItemName.setText("");		    			
    		    txt_EditItemCost.setText("");
    	    	txt_EditItemPrice.setText("");
    	    	txt_EditItemWeight.setText("");	    	
    	    	dpr_EditPurchaseDate.setValue(null);
    	    	dpr_EditSoldDate.setValue(null);	
 				
 				//Remove edit pane
    	    	gpn_EditPane.getChildren().remove(gpn_EditFieldsPane);
  				
        	 }      
         });
         
             
		 ///////////////////////////
		 // Analyze Inventory Tab
		 ///////////////////////////
 
         //Category Distribution Link
         lnk_CategoryDistribution.setOnAction(e -> {
      	 
        	 int BooksCount=0;
        	 int ClothingCount=0;
        	 int ElectronicsCount=0;
        	 int ShoesCount=0;
        	 int ToysCount=0;
        	 
        	 ArrayList<String[]> ItemList=new ArrayList<String[]>();
	    		ItemList = fn_ItemDataFromFile();				
				String[] ItemArray = new String[8];
				for (int i = 0; i < ItemList.size(); i++) {
		    		ItemArray = ItemList.get(i); 
		    		switch (ItemArray[1])
		    		{
		    			case "Books": 
		    				BooksCount++;
		    				break;
		    			case "Clothing": 
		    				ClothingCount++;
		    				break;
		    			case "Electronics": 
		    				ElectronicsCount++;
		    				break;
		    			case "Shoes": 
		    				ShoesCount++;
		    				break;
		    			case "Toys": 
		    				ToysCount++;
		    				break;
		    		}		    			
				}
								
             ObservableList<PieChart.Data> pieChartData =
                     FXCollections.observableArrayList(
                     new PieChart.Data("Books", BooksCount),
                     new PieChart.Data("Clothing", ClothingCount),
                     new PieChart.Data("Electronics", ElectronicsCount),
                     new PieChart.Data("Shoes", ShoesCount),
                     new PieChart.Data("Toys", ToysCount));
             final PieChart chart = new PieChart(pieChartData);
             chart.setTitle("Category Distribution");
             
             VBox vbox = new VBox(chart);
        	 Scene scene = new Scene(vbox);
        	 Stage stage = new Stage();
        	 stage.setScene(scene);
        	 stage.show();         
         });
         
         
         // Link
         lnk_GrossSales.setOnAction(e -> {
        	 
        	 double db_GrossSales = 0;
        	 Item it_Item = new Item();
        	 
        	 ArrayList<String[]> ItemList=new ArrayList<String[]>();
	    		ItemList = fn_ItemDataFromFile();				
				String[] ItemArray = new String[8];
				for (int i = 0; i < ItemList.size(); i++) {
		    		ItemArray = ItemList.get(i); 
		    			it_Item = new Item(Integer.parseInt(ItemArray[0]), ItemArray[1], ItemArray[2], Double.parseDouble(ItemArray[3]), Double.parseDouble(ItemArray[4]), Double.parseDouble(ItemArray[5]), ItemArray[6], ItemArray[7]);
		    			if (!ItemArray[7].isEmpty())
		    			{
		    			db_GrossSales = db_GrossSales + Double.parseDouble(ItemArray[4]);
		    			}
				}
			 DecimalFormat df = new DecimalFormat("#.00"); 
	    	 db_GrossSales = Double.parseDouble(df.format(db_GrossSales));
	    		
			 Label lbl_GrossSales = new Label("Gross Sales:  " + Double.toString(db_GrossSales));
			 VBox vbox = new VBox(lbl_GrossSales);
			 vbox.setPadding(new Insets(10, 10, 10, 10));
	    	 Scene scene = new Scene(vbox);	         
	    	 Stage stage = new Stage();
	    	 stage.setScene(scene);
	    	 stage.show();
         });
      
         
         //Net Profit Link
         lnk_NetProfit.setOnAction(e -> {
         
        	 double db_TotalNetProfit = 0;
        	 Item it_Item = new Item();
        	 Book bk_Book = new Book();
        	 
        	 ArrayList<String[]> ItemList=new ArrayList<String[]>();
	    		ItemList = fn_ItemDataFromFile();				
				String[] ItemArray = new String[8];
				for (int i = 0; i < ItemList.size(); i++) {
		    		ItemArray = ItemList.get(i); 
		    		if(!ItemArray[1].contentEquals("Books"))
		    		{
		    			it_Item = new Item(Integer.parseInt(ItemArray[0]), ItemArray[1], ItemArray[2], Double.parseDouble(ItemArray[3]), Double.parseDouble(ItemArray[4]), Double.parseDouble(ItemArray[5]), ItemArray[6], ItemArray[7]);
		    			if (!ItemArray[7].isEmpty())
		    			{
		    			db_TotalNetProfit = db_TotalNetProfit + it_Item.fn_CalculateNetProfit();
		    			}
		    		}
		    		else
		    		{
		    			bk_Book = new Book(Integer.parseInt(ItemArray[0]), ItemArray[1], ItemArray[2], Double.parseDouble(ItemArray[3]), Double.parseDouble(ItemArray[4]), Double.parseDouble(ItemArray[5]), ItemArray[6], ItemArray[7] );
		    			if (!ItemArray[7].isEmpty())
		    			{
		    			db_TotalNetProfit = db_TotalNetProfit + bk_Book.fn_CalculateNetProfit();
		    			}
		    		}
				}
			 DecimalFormat df = new DecimalFormat("#.00"); 
	    	 db_TotalNetProfit = Double.parseDouble(df.format(db_TotalNetProfit));
	    		
			 Label lbl_TotalProfit = new Label("Total Net Profit:  " + Double.toString(db_TotalNetProfit));
			 VBox vbox = new VBox(lbl_TotalProfit);
			 vbox.setPadding(new Insets(10, 10, 10, 10));
	    	 Scene scene = new Scene(vbox);	         
	    	 Stage stage = new Stage();
	    	 stage.setScene(scene);
	    	 stage.show();
         });
         
         //COGS Link
         lnk_CostOfGoodsSold.setOnAction(e -> {
         
        	 double db_CostOfGoodsSold = 0;
        	 Item it_Item = new Item();
        	 
        	 ArrayList<String[]> ItemList=new ArrayList<String[]>();
	    		ItemList = fn_ItemDataFromFile();				
				String[] ItemArray = new String[8];
				for (int i = 0; i < ItemList.size(); i++) {
		    		ItemArray = ItemList.get(i); 
		    			it_Item = new Item(Integer.parseInt(ItemArray[0]), ItemArray[1], ItemArray[2], Double.parseDouble(ItemArray[3]), Double.parseDouble(ItemArray[4]), Double.parseDouble(ItemArray[5]), ItemArray[6], ItemArray[7]);
		    			if (!ItemArray[7].isEmpty())
		    			{
		    			db_CostOfGoodsSold = db_CostOfGoodsSold + Double.parseDouble(ItemArray[3]);
		    			}
				}
			 DecimalFormat df = new DecimalFormat("#.00"); 
	    	 db_CostOfGoodsSold = Double.parseDouble(df.format(db_CostOfGoodsSold));
	    		
			 Label lbl_CostOfGoodsSold = new Label("Cost of Goods Sold:  " + Double.toString(db_CostOfGoodsSold));
			 VBox vbox = new VBox(lbl_CostOfGoodsSold);
			 vbox.setPadding(new Insets(10, 10, 10, 10));
	    	 Scene scene = new Scene(vbox);	         
	    	 Stage stage = new Stage();
	    	 stage.setScene(scene);
	    	 stage.show();
         });
         
         //ASP Link
         lnk_AverageSellingPrice.setOnAction(e -> {
         
        	 int itemCount = 0;
        	 double db_AverageSellingPrice = 0;
        	 Item it_Item = new Item();
        	 
        	 ArrayList<String[]> ItemList=new ArrayList<String[]>();
	    		ItemList = fn_ItemDataFromFile();				
				String[] ItemArray = new String[8];
				for (int i = 0; i < ItemList.size(); i++) {
		    		ItemArray = ItemList.get(i); 
		    			it_Item = new Item(Integer.parseInt(ItemArray[0]), ItemArray[1], ItemArray[2], Double.parseDouble(ItemArray[3]), Double.parseDouble(ItemArray[4]), Double.parseDouble(ItemArray[5]), ItemArray[6], ItemArray[7]);
		    			if (!ItemArray[7].isEmpty())
		    			{
		    			itemCount++;
		    			db_AverageSellingPrice = (db_AverageSellingPrice + Double.parseDouble(ItemArray[4]))/itemCount;
		    			}
				}
			 DecimalFormat df = new DecimalFormat("#.00"); 
			 db_AverageSellingPrice = Double.parseDouble(df.format(db_AverageSellingPrice));
	    		
			 Label lbl_AverageSellingPrice = new Label("Average Selling Price:  " + Double.toString(db_AverageSellingPrice));
			 VBox vbox = new VBox(lbl_AverageSellingPrice);
			 vbox.setPadding(new Insets(10, 10, 10, 10));
	    	 Scene scene = new Scene(vbox);	         
	    	 Stage stage = new Stage();
	    	 stage.setScene(scene);
	    	 stage.show();
         });

         
    }
    
    
    public ArrayList<String[]> fn_ItemDataFromFile()
    {
		ArrayList<String[]> str_ItemList=new ArrayList<String[]>();
		String[] str_ItemFields = new String[8];
    	try (BufferedReader reader = new BufferedReader(new FileReader(new File("items.txt")))) {

            String line;
            while ((line = reader.readLine()) != null) {
  
            	str_ItemFields = line.split("\\|", -1);           	      	
            	str_ItemList.add(str_ItemFields);                   	                       
            }                    

        } catch (IOException e) {
            e.printStackTrace();
        }
    	return str_ItemList;
    }
    
    public void fn_ItemTableView(ArrayList<String[]> str_ItemList)
    {
    	 TableView<Item> tableView = new TableView<Item>();
    	 
    	 TableColumn<Item, String> itemIdCol = new TableColumn<>("Item ID");
    	 itemIdCol.setCellValueFactory(new PropertyValueFactory<>("itemId"));
    	 
    	 TableColumn<Item, String> categoryCol = new TableColumn<>("Item Category");
    	 categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
    	     	 
    	 TableColumn<Item, String> nameCol = new TableColumn<>("Item Name");
    	 nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	 
    	 TableColumn<Item, String> costCol = new TableColumn<>("Item Cost");
    	 costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
    	 
    	 TableColumn<Item, String> priceCol = new TableColumn<>("Item Price");
    	 priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    	 
    	 TableColumn<Item, String> weightCol = new TableColumn<>("Item Weight");
    	 weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
    	 
    	 TableColumn<Item, String> purchaseDateCol = new TableColumn<>("Purchase Date");
    	 purchaseDateCol.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
    	 
    	 TableColumn<Item, String> soldDateCol = new TableColumn<>("Sold Date");
    	 soldDateCol.setCellValueFactory(new PropertyValueFactory<>("soldDate"));   	 
    	  	  	     	 
    	 tableView.getColumns().add(itemIdCol);
    	 tableView.getColumns().add(categoryCol);
    	 tableView.getColumns().add(nameCol);
    	 tableView.getColumns().add(costCol);
    	 tableView.getColumns().add(priceCol);
    	 tableView.getColumns().add(weightCol);
    	 tableView.getColumns().add(purchaseDateCol);
    	 tableView.getColumns().add(soldDateCol);
           	
    	 String[] ItemArray = new String[8];
    	 for (int i = 0; i < str_ItemList.size(); i++) {
    		 ItemArray = str_ItemList.get(i); 
    		 tableView.getItems().add(new Item(Integer.parseInt(ItemArray[0]), ItemArray[1], ItemArray[2], Double.parseDouble(ItemArray[3]), Double.parseDouble(ItemArray[4]), Double.parseDouble(ItemArray[5]), ItemArray[6], ItemArray[7] ));
    	 }
    	 
    	 VBox vbox = new VBox(tableView);
    	 Scene scene = new Scene(vbox);
    	 Stage stage = new Stage();
    	 stage.setScene(scene);
    	 stage.show();
    }
    
    public void fn_SearchItem(int x, TextField textField) {
    	if(textField.getText().isEmpty())
			fn_ItemTableView(fn_ItemDataFromFile());
		else
		{
			ArrayList<String[]> newItemList=new ArrayList<String[]>();			
		
			ArrayList<String[]> ItemList=new ArrayList<String[]>();
			ItemList = fn_ItemDataFromFile();				
			String[] ItemArray = new String[8];
			for (int i = 0; i < ItemList.size(); i++) {
				ItemArray = ItemList.get(i); 
				if(ItemArray[x].toLowerCase().contains(textField.getText().toLowerCase()))
					newItemList.add(ItemArray);
			}	    	
			fn_ItemTableView(newItemList);
			
		}
    }
    
    

  
    
    
    
    
    
}//End of Class






