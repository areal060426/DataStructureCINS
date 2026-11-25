package FinalsProject;
import javax.swing.JOptionPane;
import java.util.*;

public class Group1 {
   static int Balance = 0;
   static int MenuPage = 0;
   static int PiliCafeMenu = 0;
   static int Result = 0;
   static int PiliNo = 0;
   static int Qty = 0;
   static String Cafe = "";
   static int Cafe_Prices = 0;
   static int Subtotal = 0;
   // ARRAYLISTS FOR PURCHASED ITEMS
   static ArrayList<String> Bought_Items = new ArrayList<>();
   static ArrayList<Integer> Bought_Prices = new ArrayList<>();
   static ArrayList<Integer> Quanti = new ArrayList<>();
   // COFFEE MENU
   static String[] Coffee_Menu = {
       "Espresso", "Americano", "Cappuccino", "Latte",
       "Caramel Macchiato", "Mocha", "Iced Coffee"
   };
   static int[] Coffee_Menu_Prices = {
       90, 100, 120, 130, 150, 150, 120
   };
   // NON-COFFEE MENU
   static String[] Non_Coffee_Menu = {
       "Hot Chocolate", "Matcha Latte", "Milk Tea",
       "Lemonade", "Mango Shake", "Strawberry Shake"
   };
   static int[] Non_Coffee_Menu_Prices = {
       120, 150, 140, 110, 160, 160
   };
   // PASTRIES AND DESSERTS MENU
   static String[] Pastries_Desserts_Menu = {
       "Croissant", "Muffin", "Chocolate Cake Slice",
       "Cheesecake", "Brownies", "Cinnamon Roll"
   };
   static int[] Pastries_Desserts_Menu_Prices = {
       90, 90, 120, 150, 100, 120
   };
   // MEALS AND SNACKS MENU
   static String[] Meals_Snacks_Menu = {
       "Ham & Cheese Sandwich", "Tuna Sandwich",
       "Chicken Sandwich", "French Fries", "Nachos",
       "Carbonara Roll", "Clubhouse Sandwich"
   };
   static int[] Meals_Snacks_Menu_Prices = {
       150, 150, 170, 100, 160, 190, 200
   };
   // ORDER OPTIONS
   static String[] Options = {
       "Order More", "Check Out", "Exit"
   };
   // MAIN MENU OPTIONS
   static String[] Main_Options = {
       "New Order", "Track Queue", "Queue History", "Exit"
   };
   // ==============================
   // QUEUEING ARRAYS
   // ==============================
   static int Next_Queue = 1001;
   static int Queue_Count = 0;
   // STORE QUEUE NUMBERS
   static int[] Queue_Numbers = new int[100];
   // 2D ARRAY: QUEUE NUMBER, TOTAL, ORDER TYPE
   static int[][] Queue_Records = new int[100][3];
   // STORE ORDER SUMMARIES
   static String[] Queue_Order_Summary = new String[100];
   // INITIALIZE QUEUE ARRAYS
   static {
       Arrays.fill(Queue_Numbers, -1);
       for (int i = 0; i < Queue_Records.length; i++) {
           Arrays.fill(Queue_Records[i], -1);
       }
   }
   // ==============================
   // BACK TO MENU
   // ==============================
   static void Back_Menu() {
       JOptionPane.showMessageDialog(null,
           "__________________________________________\n" +
           "              Redirecting To Menu\n" +
           "________________________________________",
           "Welcome To QuesoExpresso!",
           JOptionPane.PLAIN_MESSAGE
       );
   }
   // ==============================
   // CHECK OUT
   // ==============================
   static int Check_Out(
       ArrayList<String> Bought_Items,
       ArrayList<Integer> Bought_Prices,
       ArrayList<Integer> Quanti,
       int Type
   ) {
       String Display = "";
       for (int i = 0; i < Bought_Items.size(); i++) {
           Display += Bought_Items.get(i)
                   + " | ₱" + Bought_Prices.get(i)
                   + " | Quantity: " + Quanti.get(i)
                   + " | Subtotal: ₱"
                   + (Bought_Prices.get(i) * Quanti.get(i))
                   + "\n";
       }
       String[] Checkout_Options = {
           "Confirm", "Back To Menu", "Exit"
       };
       int Confirmation = JOptionPane.showOptionDialog(
           null,
           "==========================================\n" +
           "                 Purchased Items:\n" +
           "==========================================\n" +
           Display +
           "==========================================\n" +
           "Order Type: " +
           (Type == 1 ? "Dine In" : "Take Out") +
           "\nTotal Amount: ₱" + Balance +
           "\n==========================================\n" +
           "Proceed Transaction?",
           "Receipt",
           JOptionPane.DEFAULT_OPTION,
           JOptionPane.PLAIN_MESSAGE,
           null,
           Checkout_Options,
           Checkout_Options[0]
       );
       if (Confirmation == 0) {
           // CONFIRM ORDER AND GENERATE QUEUE
           if (Generate_Queue(Type)) {
               return 1;
           } else {
               return -1;
           }
       } else if (Confirmation == 1) {
           JOptionPane.showMessageDialog(null,
               "__________________________________________\n" +
               "          Returning To Main Menu \n" +
               "________________________________________",
               "Welcome To QuesoExpresso!",
               JOptionPane.PLAIN_MESSAGE
           );
           return 0;
       } else {
           Cancel();
           return -1;
       }
   }
   // ==============================
   // GENERATE QUEUE NUMBER
   // ==============================
   static boolean Generate_Queue(int Type) {
       if (Queue_Count >= Queue_Numbers.length) {
           JOptionPane.showMessageDialog(
               null,
               "Queue Storage Is Full!",
               "Queue Error",
               JOptionPane.ERROR_MESSAGE
           );
           return false;
       }
       // GENERATE QUEUE NUMBER
       int Current_Queue = Next_Queue;
       // STORE QUEUE NUMBER
       Queue_Numbers[Queue_Count] = Current_Queue;
       // STORE TRANSACTION IN 2D ARRAY
       Queue_Records[Queue_Count][0] = Current_Queue;
       Queue_Records[Queue_Count][1] = Balance;
       Queue_Records[Queue_Count][2] = Type;
       // STORE ORDER SUMMARY
       String Summary = "";
       for (int i = 0; i < Bought_Items.size(); i++) {
           Summary += Bought_Items.get(i)
                   + " x " + Quanti.get(i) + "\n";
       }
       Queue_Order_Summary[Queue_Count] = Summary;
       Queue_Count++;
       Next_Queue++;
       // DISPLAY QUEUE TICKET
       JOptionPane.showMessageDialog(
           null,
           "==========================================\n" +
           "           ORDER CONFIRMED! \n" +
           "==========================================\n" +
           "             YOUR QUEUE NUMBER\n\n" +
           "                  " + Current_Queue +
           "\n\n" +
           "Order Type: " +
           (Type == 1 ? "Dine In" : "Take Out") +
           "\nTotal Amount: ₱" + Balance +
           "\n==========================================\n" +
           "Please wait for your number to be called.\n" +
           "Thank you for ordering!",
           "QuesoExpresso Queue Ticket",
           JOptionPane.INFORMATION_MESSAGE
       );
       // DISPLAY QUEUE USING ARRAYS
       System.out.println("========== QUEUE GENERATED ==========");
       System.out.println("Queue Number: " + Current_Queue);
       System.out.println("Total Amount: ₱" + Balance);
       System.out.println(
           "Queue Numbers: " +
           Arrays.toString(Arrays.copyOf(Queue_Numbers, Queue_Count))
       );
       return true;
   }
   // ==============================
   // TRACK QUEUE
   // ==============================
   static void Track_Queue() {
       if (Queue_Count == 0) {
            JOptionPane.showMessageDialog(
               null,
               "No Queue Records Available!",
               "Queue Tracker",
               JOptionPane.WARNING_MESSAGE
           );
           return;
       }
       try {
           String Input = JOptionPane.showInputDialog(
               null,
               "==========================================\n" +
               "                 TRACK QUEUE\n" +
               "==========================================\n" +
               "Enter Your Queue Number:"
           );
           if (Input == null) {
               return;
           }
           if (Input.trim().isEmpty()) {
               Warning();
               return;
           }
           int Target = Integer.parseInt(Input.trim());
           // COPY QUEUE ARRAY
           int[] Queue_Copy = Arrays.copyOf(
               Queue_Numbers, Queue_Count
           );
           // SORT BEFORE BINARY SEARCH
           Arrays.sort(Queue_Copy);
           // SEARCH QUEUE NUMBER
           int Index = Arrays.binarySearch(Queue_Copy, Target);
           if (Index >= 0) {
               // FIND TRANSACTION DETAILS
               for (int i = 0; i < Queue_Count; i++) {
                   if (Queue_Records[i][0] == Target) {
                       JOptionPane.showMessageDialog(
                           null,
                           "==========================================\n" +
                           "                QUEUE FOUND!\n" +
                           "==========================================\n" +
                           "Queue Number: " + Queue_Records[i][0] +
                           "\nOrder Type: " +
                           (Queue_Records[i][2] == 1
                               ? "Dine In" : "Take Out") +
                           "\nTotal Amount: ₱" + Queue_Records[i][1] +
                           "\n\nPurchased Items:\n" +
                           Queue_Order_Summary[i] +
                           "==========================================\n" +
                           "Status: Order Confirmed",
                           "Queue Tracker",
                           JOptionPane.INFORMATION_MESSAGE
                       );
                       break;
                   }
               }
           } else {
               JOptionPane.showMessageDialog(
                   null,
                   "Queue Number " + Target + " Was Not Found!",
                   "Queue Tracker",
                   JOptionPane.WARNING_MESSAGE
               );
           }
       } catch (NumberFormatException E) {
           Invalid();
       }
   }
   // ==============================
   // QUEUE HISTORY
   // ==============================
   static void Queue_History() {
       if (Queue_Count == 0) {
           JOptionPane.showMessageDialog(
               null,
               "No Transactions Available!",
               "Queue History",
               JOptionPane.WARNING_MESSAGE
           );
           return;
       }
       // COPY QUEUE ARRAY
       int[] Queue_Copy = Arrays.copyOf(
           Queue_Numbers, Queue_Count
       );
       // SORT QUEUE NUMBERS
       Arrays.sort(Queue_Copy);
       // COMPARE ORIGINAL AND SORTED ARRAYS
       boolean Compare = Arrays.equals(
           Arrays.copyOf(Queue_Numbers, Queue_Count),
           Queue_Copy
       );
       String Display = "";
       Display += "==========================================\n";
       Display += "               QUEUE HISTORY\n";
       Display += "==========================================\n";
       for (int i = 0; i < Queue_Count; i++) {
           Display += "Queue Number: " + Queue_Records[i][0] +
                   " | Total: ₱" + Queue_Records[i][1] +
                   " | " +
                   (Queue_Records[i][2] == 1
                       ? "Dine In" : "Take Out") + "\n";
       }
       Display += "==========================================\n";
       Display += "Total Transactions: " + Queue_Count + "\n";
       // SORTED QUEUE
       Display += "\nAscending Order:\n";
       Display += Arrays.toString(Queue_Copy) + "\n";
       // DESCENDING QUEUE
       Display += "\nDescending Order:\n";
       for (int i = Queue_Copy.length - 1; i >= 0; i--) {
           Display += Queue_Copy[i] + " ";
       }
       // COMPARE QUEUE
       Display += "\n\nIs Original Queue Sorted? " + Compare;
       JOptionPane.showMessageDialog(
           null,
           Display,
           "Queue History",
           JOptionPane.INFORMATION_MESSAGE
       );
       // DISPLAY 2D ARRAY
       System.out.println("________ 2D QUEUE RECORDS ________");
       System.out.println(
           Arrays.deepToString(
               Arrays.copyOf(Queue_Records, Queue_Count)
           )
       );
   }
   // ==============================
   // DISPLAY MENU
   // ==============================
   static void QuesoExpresso_Menu(
       String[] Menu,
       int[] Prices,
       String Cafe_Menu_Categories
   ) {
       String Display = "__________________________________________\n";
       Display += "             " + Cafe_Menu_Categories + " Menu:\n";
       Display += "__________________________________________\n";
       for (int i = 0; i < Menu.length; i++) {
           Display += "[" + (i + 1) + "] "
                   + Menu[i] + " - ₱" + Prices[i] + "\n";
       }
       while (true) {
           try {
               String Menu_Pili = JOptionPane.showInputDialog(
                   null,
                   Display,
                   "Welcome To QuesoExpresso!",
                   JOptionPane.PLAIN_MESSAGE
               );
               if (Menu_Pili == null) {
                   return;
               }
               if (Menu_Pili.trim().isEmpty()) {
                   Warning();
                   continue;
               }
               int Cafe_Menu = Integer.parseInt(Menu_Pili.trim());
               if (Cafe_Menu < 1 || Cafe_Menu > Menu.length) {
                   Invalid();
                   continue;
               }
               // GET SELECTED ITEM
               Cafe = Menu[Cafe_Menu - 1];
               Cafe_Prices = Prices[Cafe_Menu - 1];
               // INPUT QUANTITY
               String Quantity = JOptionPane.showInputDialog(
                   null,
                   "Item: " + Cafe +
                   "\nPrice: ₱" + Cafe_Prices +
                   "\n\nEnter Quantity:",
                   "Welcome To QuesoExpresso!",
                   JOptionPane.QUESTION_MESSAGE
               );
               if (Quantity == null) {
                   return;
               }
               if (Quantity.trim().isEmpty()) {
                   Warning();
                   continue;
               }
               Qty = Integer.parseInt(Quantity.trim());
               if (Qty <= 0) {
                   Invalid();
                   continue;
               }
               // COMPUTE SUBTOTAL
               Subtotal = Cafe_Prices * Qty;
               // UPDATE BALANCE
               Balance += Subtotal;
               // STORE ITEMS IN ARRAYLIST
               Bought_Items.add(Cafe);
               Bought_Prices.add(Cafe_Prices);
               Quanti.add(Qty);
               // DISPLAY ADDED ITEM
               JOptionPane.showMessageDialog(
                   null,
                   "__________________________________________\n" +
                   "                 Added To Cart \n" +
                   "__________________________________________\n" +
                   "Item: " + Cafe + "\n" +
                   "Price: ₱" + Cafe_Prices + "\n" +
                   "Quantity: " + Qty + "\n" +
                   "Subtotal: ₱" + Subtotal + "\n" +
                   "__________________________________________\n" +
                   "Total Amount: ₱" + Balance,
                   "Welcome To QuesoExpresso!",
                   JOptionPane.PLAIN_MESSAGE
               );
               // DEBUGGING
               System.out.println("__________ PURCHASED ITEMS __________");
               for (int i = 0; i < Bought_Items.size(); i++) {
                   System.out.println(
                       "Item: " + Bought_Items.get(i)
                   );
                   System.out.println(
                       "Price: " + Bought_Prices.get(i)
                   );
                   System.out.println(
                       "Quantity: " + Quanti.get(i)
                   );
                   System.out.println("--------------------------------");
               }
               System.out.println("Total Balance: ₱" + Balance);
               break;
           } catch (NumberFormatException E) {
               Invalid();
           }
       }
   }
   // ==============================
   // ERROR MESSAGES
   // ==============================
   static void Invalid() {
       JOptionPane.showMessageDialog(
           null,
           "Error: Invalid INPUT",
           "Error Message",
           JOptionPane.ERROR_MESSAGE
       );
   }
   static void Warning() {
       JOptionPane.showMessageDialog(
           null,
           "Error: Required INPUT",
           "Wang Wang Wang Message",
           JOptionPane.WARNING_MESSAGE
       );
   }
   // ==============================
   // CANCEL ORDER
   // ==============================
   static void Cancel() {
       JOptionPane.showMessageDialog(
           null,
           "__________________________________________\n" +
           "              Cancelling Order '\n" +
           "________________________________________",
           "Order Cancel",
           JOptionPane.PLAIN_MESSAGE
       );
       JOptionPane.showMessageDialog(
           null,
           "__________________________________________\n" +
           "              Starting Over Again\n" +
           "________________________________________",
           "Welcome To QuesoExpresso!",
           JOptionPane.PLAIN_MESSAGE
       );
       // RESET ORDER DETAILS
       Balance = 0;
       Qty = 0;
       Subtotal = 0;
       Cafe = "";
       Cafe_Prices = 0;
       Bought_Items.clear();
       Bought_Prices.clear();
       Quanti.clear();
       MenuPage = 1;
   }
   // ==============================
   // RESET AFTER SUCCESSFUL ORDER
   // ==============================
   static void Reset_Order() {
       Balance = 0;
       Qty = 0;
       Subtotal = 0;
       Cafe = "";
       Cafe_Prices = 0;
       Bought_Items.clear();
       Bought_Prices.clear();
       Quanti.clear();
   }
   // ==============================
   // MAIN METHOD
   // ==============================
   public static void main(String[] args) {
       MenuPage = 1;
       while (true) {
           // MAIN KIOSK MENU
           int Main_Choice = JOptionPane.showOptionDialog(
               null,
               "__________________________________________\n\n" +
               "           QUESOEXPRESS SELF-SERVICE\n" +
               "__________________________________________\n\n" +
               "Welcome! Please choose an option.",
               "QuesoExpresso Kiosk",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.PLAIN_MESSAGE,
               null,
               Main_Options,
               Main_Options[0]
           );
           if (Main_Choice == 0) {
               // ==============================
               // DINE IN OR TAKE OUT
               // ==============================
               String PiliCafe = JOptionPane.showInputDialog(
                   null,
                   "__________________________________________\n\n" +
                   "           Choose how you would like to dine:\n\n" +
                   "__________________________________________\n" +
                   "[1] Dine In \n" +
                   "[2] Take Out \n",
                   "Welcome To QuesoExpresso!",
                   JOptionPane.PLAIN_MESSAGE
               );
               if (PiliCafe == null) {
                   continue;
               }
               try {
                   PiliNo = Integer.parseInt(PiliCafe.trim());
               } catch (NumberFormatException E) {
                   Invalid();
                   continue;
               }
               if (PiliNo < 1 || PiliNo > 2) {
                   Invalid();
                   continue;
               }
               // ==============================
               // CATEGORY MENU LOOP
               // ==============================
               boolean Order_Done = false;
               while (!Order_Done) {
                   String CafeMenu = JOptionPane.showInputDialog(
                       null,
                       "__________________________________________\n\n" +
                       "          Cafe Menu Categories:\n\n" +
                       "__________________________________________\n" +
                       "[1] Coffee\n" +
                       "[2] Non-Coffee\n" +
                       "[3] Pastries & Desserts\n" +
                       "[4] Meals/Snacks\n" +
                       "[5] Exit\n",
                       "Welcome To QuesoExpresso Cafe!",
                       JOptionPane.PLAIN_MESSAGE
                   );
                   if (CafeMenu == null) {
                       break;
                   }
                   try {
                       PiliCafeMenu = Integer.parseInt(CafeMenu.trim());
                   } catch (NumberFormatException E) {
                       Invalid();
                       continue;
                   }
                   if (PiliCafeMenu < 1 || PiliCafeMenu > 5) {
                       Invalid();
                       continue;
                   }
                   // EXIT CATEGORY MENU
                   if (PiliCafeMenu == 5) {
                       break;
                   }
                   // ==============================
                   // SELECT CATEGORY
                   // ==============================
                   if (PiliCafeMenu == 1) {
                       QuesoExpresso_Menu(
                           Coffee_Menu,
                           Coffee_Menu_Prices,
                           "Coffee"
                       );
                   } else if (PiliCafeMenu == 2) {
                       QuesoExpresso_Menu(
                           Non_Coffee_Menu,
                           Non_Coffee_Menu_Prices,
                           "Non-Coffee"
                       );
                   } else if (PiliCafeMenu == 3) {
                       QuesoExpresso_Menu(
                           Pastries_Desserts_Menu,
                           Pastries_Desserts_Menu_Prices,
                           "Pastries & Desserts"
                       );
                   } else if (PiliCafeMenu == 4) {
                       QuesoExpresso_Menu(
                           Meals_Snacks_Menu,
                           Meals_Snacks_Menu_Prices,
                           "Meals & Snacks"
                       );
                   }
                   // IF NO ITEM WAS ADDED
                   if (Bought_Items.isEmpty()) {
                       continue;
                   }
                   // ==============================
                   // AFTER ADDING ITEM
                   // ==============================
                   Result = JOptionPane.showOptionDialog(
                       null,
                       "What would you like to do next?",
                       "Welcome To QuesoExpresso!",
                       JOptionPane.DEFAULT_OPTION,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       Options,
                       Options[0]
                   );
                   if (Result == 0) {
                       // ORDER MORE
                       Back_Menu();
                       continue;
                   } else if (Result == 1) {
                       // CHECK OUT
                       int Checkout_Result = Check_Out(
                           Bought_Items,
                           Bought_Prices,
                           Quanti,
                           PiliNo
                       );
                       if (Checkout_Result == 1) {
                           // ORDER SUCCESSFUL
                           Reset_Order();
                           Order_Done = true;
                       } else if (Checkout_Result == 0) {
                           // BACK TO CATEGORY MENU
                           continue;
                       } else {
                           // CANCEL ORDER
                           Cancel();
                           Order_Done = true;
                       }
                   } else {
                       // EXIT / CANCEL
                       Cancel();
                       Order_Done = true;
                   }
               }
           } else if (Main_Choice == 1) {
               // TRACK QUEUE
               Track_Queue();
           } else if (Main_Choice == 2) {
               // VIEW QUEUE HISTORY
               Queue_History();
           } else {
               // EXIT APPLICATION
               JOptionPane.showMessageDialog(
                   null,
                   "Thank you for visiting QuesoExpresso!",
                   "Goodbye!",
                   JOptionPane.INFORMATION_MESSAGE
               );
               System.exit(0);
           }
       }
   }
}


