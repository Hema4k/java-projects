//This is simple ATM machine program


import java.util.Scanner;
public class IbrahimSalamaATMproject{
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		int operation=1;
		double numD,numW,d,w;
		numD=numW=d=w=0;
		String bar ="===================================";
		String mainMenue="\n\tMain Menue \n"+"1-Balance Inquiry \n"+"2-Withdraw\n"+"3-Deposit\n"+"4-View All Transactions\n"+"5-Balance Transfer\n"+"0-Exit\n";
		String[] veiwTrans;
 	veiwTrans = new String[10];
		  int t=0;

	System.out.println(bar+" \n\tATM machine \n"+"Please Enter Your Current Balance : ");
	//input how much the user have in his account

	double balance = input.nextInt();
//input balance

	while(operation!=0){
System.out.println(bar+mainMenue+bar);
	 operation = input.nextInt();
//show the main menue



	 if (operation==1){
System.out.println("Your Balance = "+displayBalance(balance)+"$");
//show the total balance of the user
						}




// THIS IS THE SECOND OPERATION
	else if (operation==2){


System.out.println("Please Enter How Much You Want to Withdraw : ");
	double withdraw =input.nextDouble();


		if((w<3000)&&(withdraw<3000)&&(balance>=withdraw)&&((w+withdraw)<=3000)){//preventing user for being able to withdraw more than 3000

veiwTrans[t]=("your have withdrawn :"+withdraw);
t++;

w=w+withdraw; //indicate the totoal amount withdrawn

	withdraw(balance,withdraw);
	balance=balance-withdraw;



		}
	else
	System.out.println("Sorry You Have Achived The Maximun Amount of 3000 $ of Total Withdrawn Balance, Please Try Again in (24) Hours");

					 }



	//THIS IS THE THIRD OPTION
if(operation==3){


	System.out.println("Enter The Amount You Want TO Deposit :");
	double deposit = input.nextDouble();


	if(deposit>0){
		//prevent user form dopsiting a negative value of money

		veiwTrans[t]=("your have Deposit :"+deposit);
		t++;


	balance =deposit(balance,deposit);
System.out.println("You Have Made A Deposit Succesfully ! \n"+"Your Previous Balance = "+(balance-deposit)+" $ \n"+"Your Curent Balance = "+balance+" $"+"\n"+
				"The Amount OF Deposit = "+deposit+" $");
//show when the user deposit
			}

			else
			System.out.println("Sorry, Please Enter a Valid Amount to Deposit .");
//shown when useer eneter a non-positive value


	}


	if(operation==4){
		transactions(veiwTrans,t);
	}



	if(operation==5){
	System.out.println("a- Zain \n"+"b- Sudani \n"+"c- MTN \n"+"d- exit from this menue");
	String option= input.next();

if((option.equals("a"))||(option.equals("b"))||(option.equals("c"))){//second if start
	System.out.println("How much You Want To Transfer ?");
			double transfer=input.nextDouble();


	if((transfer<3000)&&(balance>=transfer)&&(w<=3000)&&((w+transfer)<=3000)){//preventing user for being able to transfer  more than 3000

veiwTrans[t]=("your have Transferes :"+transfer+" $");
t++;

w=w+transfer ; //indicate the totoal amount transfered

	transfer(balance,transfer,option);
	balance=balance-transfer;
	}
	else
	System.out.println("Sorry You Have Achived The Maximun Amount of 3000 $ of Total Withdrawn Balance, Please Try Again in (24) Hours");

		}//second if end




	}





}
// the escape condition is  ( 0 ) to exit the program



	}


			//this is the method used to show the user balance
			public static double displayBalance(double b){

	return b;}



// this is the method used to withdraw amount from the user acoount if it less than 3000 $

public static void withdraw(double balance, double withdraw){
	if (withdraw >balance)
		System.out.println("Sorry You Don't Have Enought Balance in Your Account ,Try Again .");

				else  {
					balance = balance-withdraw;
									System.out.println("You Have Withdrawn Succesfully ! \n"+"Your Previous Balance = "+(balance+withdraw)+" $ \n"+"Your Curent Balance = "+balance+" $"+"\n"+
				"The Amount Withdrawn = "+withdraw+" $");}
							}



//this is the method used for adding the deposit to the user balance
public static double deposit(double balance, double deposit){
	balance = balance+deposit;

return balance;}



			//this is the method the show the last 10 transactions in user acoount
			public static void transactions(String []veiwTrans,int max){

	for(int t=0; t<max;t++)
System.out.println(veiwTrans[t]+" $");
//print last 10 transactions
									}

			//this is the method for transfering credit from user account to another acoount

			  public static void transfer(double balance,double transfer , String option){
		String success="Your transfer was successfull ! "+"\n"+"Your new Balance : ";


			if(option.equals("a")){

			System.out.println("====== Zain Blance Transfer ======= \n");

			balance=balance-transfer;
System.out.println(success+balance);


					}//the if end barclet
			if(option.equals("b")){
			System.out.println("====== Sudani  Blance Transfer ======= \n");

						balance=balance-transfer;
System.out.println(success+balance);


					}//the if end barclet
				if(option.equals("c")){
							System.out.println("====== MTN Blance Transfer ======= \n");

			balance=balance-transfer;
		System.out.println(success+balance);



					}//the if end barclet



					}//end of ransfering method
}
//the end Proerct Main class