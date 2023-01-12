import java.util.Scanner;

public class IbrahimYousriPHARMACYproject{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		String bar = "---------------------------------------------------------------------------------------------------------\n";
		String space = "                                        ";
		String menue = " 1- Add new Medicine\n 2- Update Medicine (quantity, price) \n 3- Veiw Reports \n 4- Sell Medicine \n 5- Delete Medicine \n 6- Exit";
		int option =1 ;
		int maxMed= 1;
		int medNum=0;
		int newMed =0;
		Medicine[] medicine;
	//	 int[] medNums ;
		boolean found = false;
int pointer=0;//counter for the last medicine inputed
int lastMed =0;
boolean allow = false;


					while(allow==false){

						System.out.println("How many Mdeicines in your pharmacy Storage ? ");
						maxMed = input.nextInt();

						if(maxMed>0){
						medicine = Medicine.createMedicine(maxMed);
						allow = true;
								}
						else
						System.out.println("Please Enter a positive value ! ");

							}// end of while loop
							allow =false;

						medicine = Medicine.createMedicine(maxMed);


						for(int i =0;i<medicine.length;i++){
							medicine[i]=new Medicine();//create new medicine

					//		medicine[i].medNo =i; // give the medNo of medicine the value of its order, if the medicine is 0 the medNO is 0 also

						System.out.println("the medicine "+i+" medNO is "+medicine[i].medNo);//to check


							}//end of for





		while(option!=6){

System.out.println(bar+space+"Ibrahim Phramacy Medicine System \n"+bar+menue);
		option = input.nextInt();



			if(option==1){//add medicine

								while(allow==false){
							System.out.println("How many medicine you want to add ?");
									 newMed = input.nextInt();

											if(newMed>0){
											allow = true;// to escape the loop
													}
											else
											System.out.println("Please Enter a positive value ! ");

									}// end of while loop
									allow = false;



				if( (medNum+newMed)<=maxMed){
				medNum = medNum+newMed;



			for( int i = pointer ; i<(pointer+newMed); i++){


				System.out.println("Enter the "+i+" medicine name");
				medicine[i].name =input.next();

				System.out.println("Enter the "+i+" medicine commercial name");
				medicine[i].comName =input.next();



			while(allow==false){

				System.out.println("Enter the"+i+" medicine Number (medNo) ");
				medicine[i].medNo =input.nextInt();

				if((medicine[i].medNo>0)){
				allow = true;// to escape the loop
							}
				else
				System.out.println("Please Enter a Valid Value ! ");

				}//end of while loop
				allow=false;




			while(allow==false){

				System.out.println("Enter the"+i+" medicine Production Year");
				medicine[i].Pyear=input.nextInt();

				if((medicine[i].Pyear>2015)&&(medicine[i].Pyear<=2022)){
				allow = true;// to escape the loop
							}
				else
				System.out.println("Please Make Sure the Medicine Production year is NOT : \n1- after 2022\n2- Or Older than 2015 ");

				}//end of while loop
				allow=false;


			while(allow==false){

				System.out.println("Enter the"+i+" medicine quantity");
				medicine[i].quantity =input.nextInt();

				if(medicine[i].quantity>0){
				allow = true;// to escape the loop
							}
				else
				System.out.println("Please Enter a Valid Quantity ! ");

				}//end of while loop
				allow=false;


			while(allow==false){

				System.out.println("Enter the"+i+" medicine Price");
				medicine[i].price =input.nextInt();


				if(medicine[i].price>0){
				allow = true;// to escape the loop
							}
				else
				System.out.println("Please Enter a Valid Price ! ");

				}//end of while loop
				allow=false;

			while(allow==false){

			System.out.println("Enter the"+i+" medicine Reoreder Limit ");
				medicine[i].reorderLimit =input.nextInt();


				if( (medicine[i].reorderLimit>=0)&&(medicine[i].reorderLimit<medicine[i].quantity) ){
				allow = true;// to escape the loop
							}
				else
				System.out.println("Please Make Sure the Medicine Reorder Limit is NOT Negative OR Greater than The Quantity !");

				}//end of while loop
				allow=false;

		}// end of for loop

				pointer= newMed+pointer;




				}//end of medNum


				else{
				System.out.println("Sorry (your storage is full), You can't add more Medicine, OR You added more medicine than your storage capicity ");
//		medNum = medNum-newMed;
					}//end of else



		//	}//end of for



				}//end of option 1



				if(option==2){//start of option 2

			System.out.println("1- Update Quantity \n2- Update price");
			int choise = input.nextInt();


			System.out.println("Enter Medicine number (medNo)");
			int medNumber = input.nextInt();


			for(int k =0;k<pointer;k++){// from the first medNo till the last one was in

				if((medicine[k].medNo)==medNumber){//search by medNo

			if(choise==1){
				found = true;

				System.out.println("Medicine is found, The old current quantitiy is  "+medicine[k].quantity+" \nEnter the new  Quantity");

				int newQuantity = input.nextInt();

				if ((newQuantity>0)&& (newQuantity<100)){
				medicine[k].quantity = newQuantity;
				}else System.out.println(" You can not add quantity larger than 100 and less than 0 !");




				}//end of if 1


				if(choise==2){
			found = true;

				System.out.println("Medicine is found, The old current Price is  "+medicine[k].price+" \nEnter the new  Price");

				int newPrice = input.nextInt();

					if (newPrice>0){
					medicine[k].price = newPrice;
					}else System.out.println(" You can not add  Price less than 0 !");




				}//end of if 2


			}// end of search by medNo if
			else found = false;


			}//end of for loop




				}//end of if option 2


			if(option==3){

					System.out.println("a. All Medicines Report \nb. All Medicines with less than or equal a specicfic price \nc.All expired Medicine with year less than 2021 ");
					String choise = input.next();

						report(choise,pointer,medicine);


				}// end of option 3
				else





			if(option==4){// sell medicine


			sell(pointer,medicine);



			}//end of option 4



			if(option==5){// delete medicine

			System.out.println(" Enter the medNumer of the Medicine you want to delete :");
			int medNumber = input.nextInt();

			for(int k =0;k<pointer;k++){// from the first medNo till the last one was in

							if((medicine[k].medNo)==medNumber){//search by medNo
								medicine[k].delete();
								System.out.println(" The Medicine was Successfully Deleted ");


						}//end of if
					// not neccessary an  else System.out.println("The Medicine number you enterd does not Exist !");

		   }//end of search loop





			}//end of option 4





		}//end of while loop for menue








	}//end of main method




			public static void  report(String choise, int pointer, Medicine[] medicine){
							Scanner input = new Scanner(System.in);



				if(choise.equals("a")){
								System.out.printf("%-12s%-22s%-17s%-12s%-8s%-8s%-5s\n"," Name     | ","Commercial Name ","| Production Year "," | Price","| Quanitiy "," ReorderLimit|"," NO ");

							for(int k =0;k<pointer;k++){

								if(medicine[k].medNo>0){
								System.out.printf( "%-12s%-22s%-22s%-12s%-11s%-11s%-5s\n",medicine[k].name,medicine[k].comName,medicine[k].Pyear,medicine[k].price,medicine[k].quantity,medicine[k].reorderLimit,medicine[k].medNo);
							}//end of if

							}//end of for loop for sear rch

					}//end of choise a




							if(choise.equals("b")){
								System.out.println(" Enter the price which you want to see medicine below ");
									int range = input.nextInt();

							if(range>0) {//check if
							System.out.println(" The medcine below price of  "+ range +" : \n");
							System.out.printf("%-12s%-22s%-17s%-12s%-8s%-8s%-5s\n"," Name     | ","Commercial Name ","| Production Year "," | Price","| Quanitiy "," Reorder Limit|","NO ");


								for(int k =0;k<pointer;k++){

								if(medicine[k].price<=range){
								System.out.printf( "%-12s%-22s%-22s%-12s%-11s%-11s%-5s\n",medicine[k].name,medicine[k].comName,medicine[k].Pyear,medicine[k].price,medicine[k].quantity,medicine[k].reorderLimit,medicine[k].medNo);


							}//end of if price


						}//end of pointer loop


				}//end of check if
				else System.out.println(" Sorry , Enter a value between above Zero ( 0 ) !");

					}//end of choise b





		if(choise.equals("c")){
			  System.out.println(" The medcine expires in less than  2021 : \n");
			System.out.printf("%-12s%-22s%-17s%-12s%-8s%-8s%-5s\n"," Name     | ","Commercial Name ","| Production Year "," | Price","| Quanitiy "," Reorder Limit|","NO ");

				for(int k =0;k<pointer;k++){

					     if(medicine[k].Pyear<2021){
								System.out.printf( "%-12s%-22s%-22s%-12s%-11s%-11s%-5s\n",medicine[k].name,medicine[k].comName,medicine[k].Pyear,medicine[k].price,medicine[k].quantity,medicine[k].reorderLimit,medicine[k].medNo);



								}//end of if price


						}//end of pointer loop



				}//end of choise c



			}//end of method report




			public static void sell(int pointer, Medicine[] medicine){
				Scanner input = new Scanner(System.in);


					System.out.println("Enter Medicine number (medNo)");
							int medNumber = input.nextInt();

						for(int k =0;k<pointer;k++){// from the first medNo till the last one was in

							if((medicine[k].medNo)==medNumber){//search by medNo
							System.out.println(" Medicine number "+medicine[k].medNo+" is FOUND \n THE PRICE IS :"
							+medicine[k].price+"\n THE QUANTITY AVAILABLE IS :"+medicine[k].quantity);

							System.out.println("Enter the Amount you want to sell :");
							int amount = input.nextInt();

								if(amount>medicine[k].quantity){
								System.out.println("SORRY, there is no enough Quantity !");

								}else if(amount<=0){
							System.out.println(" SORRY, plese Enter a Valid  Positive Value !");
							}
							else
							{
								medicine[k].quantity-=amount;
							 System.out.println("YOur Purchase process is Successful ! \nThe price is "+amount*(medicine[k].price)+" \nThe remaining Quantity is :"+medicine[k].quantity);


							}//end of last else


							}// end of if
									else System.out.println("Sorry this medicine does not exist ! ");


		}//end of search loop


			}//end of sell method



		}//end of main class

		class Medicine{

			int medNo;
			String name;
			String comName;
			int Pyear;
			int quantity;
			int price;
			int reorderLimit;

			Medicine(String name, String comName, int medNo, int Pyear, int quantity, int price, int reorderLimit){

			 this.medNo = medNo;
			 this.name = name;
			 this.comName = comName;
			 this.Pyear = Pyear;
			 this.quantity = quantity;
			 this.price = price;
			 this.reorderLimit = reorderLimit;




				}//constructor

				Medicine(){
									int medNo;
							 name="None";
							 comName="None";
							 Pyear=0;
							 quantity=0;
							 price=0;
							 reorderLimit=0;

				}// normal construcor




public static Medicine[] createMedicine(int maxMed){

		Medicine[] medicineArray = new Medicine[maxMed];


		return medicineArray;

}//end of create method



			public  void delete(){
				             medNo=-1;
					       	name=" Deleted Medicine";
						     comName="Deleted Medicine";
							 Pyear=0;
							 quantity=0;
							 price=0;
							 reorderLimit=0;




			}//end of delete method




		}//end of medicine class
