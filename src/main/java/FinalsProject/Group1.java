package FinalsProject;

import javax.swing.JOptionPane;

public class Group1 {
		public static void main(String[] args) {
		
			int f = 1;
			while (f > 0){
			
			JOptionPane.showMessageDialog(null,
	                "==========================================\n" +
	                "                              Welcome!\n"  +
	                "==========================================",
	                "Welcome To Gerald's Cafe!" , JOptionPane.PLAIN_MESSAGE);
			
			
			String PiliCafe = JOptionPane.showInputDialog(null,
	                "==========================================\n" +
	                "                         What do you prefer: \n" +
	                "==========================================\n" +
	                "[1] Dine In\n"  + 
	                "[2] Take Out\n",
	                "Welcome To Gerald's Cafe!" , JOptionPane.PLAIN_MESSAGE);
			

			
	        if (PiliCafe == null) 
	        {
	            JOptionPane.showMessageDialog(
	                    null,
	                    "Error: You closed the menu!",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE);
	            		System.exit(0);
	        }
	            
	            if (PiliCafe.equals("")) 
	            {
	                JOptionPane.showMessageDialog(
	                        null,
	                        "Error: Invalid",
	                        "Error",
	                        JOptionPane.ERROR_MESSAGE);
	                		continue;
	            }
	                
	            
	            
	            int PiliNo = Integer.parseInt(PiliCafe);
	            
	         if (PiliNo == 1 || PiliNo == 2)   {
	        	 String CafeMenu = JOptionPane.showInputDialog(null,
	                     "==========================================\n" +
	                     "                         Cafe Menu Categories: \n" +
	                     "==========================================\n" +
	                     "[1] Coffee\n"  + 
	                     "[2] Non-Coffee\n" +
	                     "[3] Pastries & Desserts\n" +
	                     "[4] Meals/Snacks\n" +
	                     "[5] Exit\n",
	                     "Welcome To Gerald's Cafe!" , JOptionPane.PLAIN_MESSAGE); 
	        	 
	        	 if (CafeMenu == null || CafeMenu.equals("")) {
	                 JOptionPane.showMessageDialog(
	                         null,
	                         "Error: You closed the menu!",
	                         "Error",
	                         JOptionPane.ERROR_MESSAGE);
	                 		 return;
	        	 }
	        	 
	        	 int PiliCafeMenu = Integer.parseInt(CafeMenu);
	        	 
	        	 if (PiliCafeMenu == 1) {
	            	 String Coffee = JOptionPane.showInputDialog(null,
	                         "==========================================\n" +
	                         " 					Coffee Menu: \n" +
	                         "==========================================\n" +
	                         "Item                                                      Price\n" +
	                         "[1] Espresso                                       ₱90\n"  + 
	                         "[2] Americano                                    ₱100\n" +
	                         "[3] Cappuccino                                  ₱120\n" +
	                         "[4] Latte                                               ₱130\n" +
	                         "[5] Caramel Macchiato                    ₱150\n" + 
	                         "[6] Mocha                                            ₱150\n" +
	                         "[7] Iced Coffee                                   ₱120\n",
	                         "Welcome To Gerald's Cafe!" , JOptionPane.PLAIN_MESSAGE); 
	        	 } else if (PiliCafeMenu == 2) {
	            	 String NonCoffee = JOptionPane.showInputDialog(null,
	                         "==========================================\n" +
	                         " 				Non - Coffee Menu: \n" +
	                         "==========================================\n" +
	                         "Item                                                      Price\n" +
	                         "[1] Hot Chocolate                                       ₱120\n"  + 
	                         "[2] Matcha Latte                                    ₱150\n" +
	                         "[3] Milk Tea                                  ₱140\n" +
	                         "[4] Lemonade                                               ₱110\n" +
	                         "[5] Mango Shake                    ₱160\n" + 
	                         "[6] Strawberry Shake                                            ₱160\n",
	                         "Welcome To Gerald's Cafe!" , JOptionPane.PLAIN_MESSAGE); 
	        	 } else if (PiliCafeMenu == 3) {
	            	 String Desserts = JOptionPane.showInputDialog(null,
	                         "==========================================\n" +
	                         " 				Pastries & Desserts: \n" +
	                         "==========================================\n" +
	                         "Item                                                      Price\n" +
	                         "[1] Croissant                                       ₱90\n"  + 
	                         "[2] Muffin                                    ₱90\n" +
	                         "[3] Chocolate Cake Slice                                  ₱120\n" +
	                         "[4] Cheesecake                                               ₱150\n" +
	                         "[5] Brownies                    ₱100\n" + 
	                         "[6] Cinnamon Roll                                            ₱120\n",
	                         "Welcome To Gerald's Cafe!" , JOptionPane.PLAIN_MESSAGE); 
	        	 } else if (PiliCafeMenu == 4) {
	            	 String Snacks = JOptionPane.showInputDialog(null,
	                         "==========================================\n" +
	                         " 				Meals / Snacks: \n" +
	                         "==========================================\n" +
	                         "Item                                                      Price\n" +
	                         "[1] Ham & Cheese Sandwich                                       ₱150\n"  + 
	                         "[2] Tuna Sandwich                                    ₱150\n" +
	                         "[3] Chicken Sandwich                                  ₱170\n" +
	                         "[4] French Fries                                               ₱100\n" +
	                         "[5] Nachos                    ₱160\n" + 
	                         "[6] Carbonara Roll                                            ₱190\n" +
	                         "[7] Clubhouse Sandwich                                            ₱200\n",
	                         "Welcome To Gerald's Cafe!" , JOptionPane.PLAIN_MESSAGE); 
	        	 
	        	 
	        	 
	        	 
	         } else {
	                 JOptionPane.showMessageDialog(
	                         null,
	                         "Error: Invalid",
	                         "Error",
	                         JOptionPane.ERROR_MESSAGE);
	                 		continue;
	             
	         }
	            
	            
	            
		
	            
			}
		
		}

		}
	}