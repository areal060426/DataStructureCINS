package Activity3;
//Group 4: Vhenz Santuele, Roel Ernest Fernandes, Justine Lagrada, Daryl Paña 
//Lira Linatoc, Clare Margaret De Leon, Glayza Maxinne Dimailig, Angelica Estores
public class Group4 {
	public static void main(String[] args) {
		double totalBranchSale = 0;
		double totalEmployeeSale = 0;
		double highestEmployeeSale = 0;
		double highestBranchSale = 0;
		int branchWithHighestSale = 0;
		int employeeBranch = 0;
		int employeeWithHighestSale = 0;
		
		double[][][] sales = {
				{ 
					{500, 700, 600, 800},
					{450, 550, 700, 900}, 
					{600, 650, 750, 850}  
				},
				{
					{700,800,750,900},
					{500,600,650,700}, 
					{800,850,900,950}  
				}
			
		};
		
		for (int branch = 0; branch < sales.length; branch++) {
            System.out.println("Branch " + (branch + 1));

            for (int employee = 0; employee < sales[branch].length; employee++) {
				
				System.out.print("Employee " + (employee +1) + " \t");
				
				for (int week = 0; week < sales[branch][employee].length; week++) {
					
                    System.out.print("[" +sales[branch][employee][week] + "] ");
					totalEmployeeSale += sales[branch][employee][week];
                }

				System.out.println("\t Total = " + totalEmployeeSale);
				totalBranchSale += totalEmployeeSale;
				
				if (totalEmployeeSale > highestEmployeeSale) {
					highestEmployeeSale = totalEmployeeSale;
					employeeWithHighestSale = employee;
					employeeBranch = branch;
				};

				totalEmployeeSale = 0;		

				
            }

            System.out.println();
			System.out.println("Total Branch Sale: " + totalBranchSale);
			
			if (totalBranchSale > highestBranchSale) {
					highestBranchSale = totalBranchSale;
					branchWithHighestSale = branch;
				};

			totalBranchSale = 0;
			System.out.println();
        }
		System.out.println("Branch with the Highest Sale: Branch " + (branchWithHighestSale + 1));
		System.out.println("Employee with the Highest Sale: Employee " + (employeeWithHighestSale + 1) + " From Branch " + (employeeBranch+ 1));
    }

	}


