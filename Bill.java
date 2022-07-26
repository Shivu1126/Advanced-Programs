package billsys;
import java.util.*;
public class Bill {

	 static List<bill_detail> billDetail = new ArrayList<bill_detail>();
	 static Scanner s = new Scanner(System.in);
	 static int bill_id=0;
	 static int temp=0;
	 static List<item_list> product_details = new ArrayList<item_list>();
	 static List<customer_list> customer_details = new ArrayList<customer_list>();

	 
	 public static void main(String[] args) {
		// TODO Auto-generated method stub

		 product_details.add(new item_list("Badam",1,100,65));
		 product_details.add(new item_list("Kitkat",2,100,20));
		 product_details.add(new item_list("Pen",3,100,15));
		 product_details.add(new item_list("Horlicks",4,100,80));
		 product_details.add(new item_list("Chips",5,100,50));

		 customer_details.add(new customer_list("Shiv",11));
		 customer_details.add(new customer_list("Saro",12));
		 customer_details.add(new customer_list("Harish",13));
		 customer_details.add(new customer_list("Kavis",14));
		 customer_details.add(new customer_list("Vk",15));
		 
		char repeat_function;
		int function_option;
		do {
			
			System.out.println("Your options :");
			System.out.println("1.Bill Craete");
			System.out.println("2.Search Bill");
			System.out.println("3.Search customer's Bill");
			System.out.println("4.Items stock details");
			System.out.println("5.Customer's Total Bill Amount");
			System.out.println("6.Search Item's Bill");
			System.out.println("7.Add Customer");
			System.out.println("8.Bill List");
			System.out.println("Enter option : ");
			function_option=s.nextInt();
			
			switch(function_option)
			{
			
			case 1:
				create_bill();
				break;
				
			case 2:
				if(temp!=0)
					search_bill();
				else
					System.out.println("No bill exist.....");
				break;
			case 3:
				if(temp!=0)
					search_customers_bill();
				else
					System.out.println("No bill exist.....");
				break;
			case 4:
				stock_detail();
				break;
			case 5:
				sales_detail();
				break;
			case 6:
				if(temp!=0)
					search_item_bill();
				else
					System.out.println("No bill exist.....");
				break;
			case 7:
				add_customer();
				break;	
			case 8:
				bill_list();
				break;
			default:
				System.out.println("Enter proper input....");	
			}
				System.out.println("If you want reapeat function...press \'y\' else press\'n\'");
				repeat_function=s.next().charAt(0);
			}while(repeat_function=='y');
	}
	
	public static void create_bill(){
		char repeat_invoice;
		
		do {
			int totprice=0;
		
			String name;
			int item_id;
			int qty;
			int q_price;
			int price;
			
			int opt;
			
			String cus_name="";
			int cus_id=0;
			char repeat_line;
			int choose_customer;
			
			System.out.println("Customer List : ");
			int n=0;
			//System.out.println("1.Shiv\n2.Saro\n3.Harish\n4.Kavis\n5.Vk");
			for(customer_list cl : customer_details) {
				n++;
				System.out.println(n+"."+cl.customer_name);
			}
			
			System.out.println("Choose Customer :");
			choose_customer=s.nextInt();
			if(choose_customer<=customer_details.size()) {
			customer_list customer=customer_details.get(choose_customer-1);

			cus_name=customer.customer_name;
			cus_id=customer.customer_id;
			}
			else
			{
				System.out.println("Enter proper input...");
				return ;
			}
			
			List<line_det> items_detail = new ArrayList<line_det>();

			do {
				System.out.println("Product details : ");
				System.out.println("1.Badam\n2.Kitkat\n3.Pen\n4.Horlicks\n5.Chips");
				System.out.println("Enter product number :");
				opt=s.nextInt();
				if(opt<=5) {
				item_list item = product_details.get(opt-1); 
		
				name=item.item_name;
				price = item.price;
				item_id=item.item_id;
				System.out.println("Quantity");
				qty = s.nextInt();
				
				q_price = price*qty;
				totprice = totprice + q_price;
				item.stock=item.stock-qty;
				
				items_detail.add(new line_det(name,qty,price,q_price,item_id));
				}
				else
				{
					System.out.println("Enter proper input...");
				
				}
				
				System.out.println("If you want purchase another product...press \'y\' else press \'n\'");
				repeat_line=s.next().charAt(0);
			 }while(repeat_line=='y');
			
			 bill_id++;
			 System.out.println("Customer Name : "+cus_name+"\t\t\t\tBill ID:" +bill_id);
			 System.out.println("Customer Id : "+cus_id+"\t\t\tDate : " +java.time.LocalDate.now());		         
			 System.out.println("----------------------------------------------------------------------------------------------------");  
		     System.out.println("Product_Name  \tItem id  \tQuantity    \tRate    \tTotal Price");  
		     System.out.println("----------------------------------------------------------------------------------------------------");  
			 for(line_det b : items_detail) {
				 b.display();
			 }
		     System.out.println("----------------------------------------------------------------------------------------------------");  
			 System.out.println("\t\t\t\t\t\tTotal Amount = " + totprice);
			//int i=0;
			 billDetail.add(new bill_detail(cus_name,cus_id,bill_id,totprice, items_detail));
			
			temp++;
			System.out.println("Next customer....press \'y\'else press \'n\'");
			repeat_invoice=s.next().charAt(0);
			 
		}while(repeat_invoice=='y');
	}
	
	
	public static void search_item_bill() {
		int search_item_id;
		char search_bill_repeat;
		do {
		System.out.println("Enter item id : ");
		search_item_id=s.nextInt();
		int empty_bill=0;
		
		 for(bill_detail b : billDetail) {
			 for(line_det c: b.line_items){
				 if( c.item_id==search_item_id)
				 {
					 b.display_bill_detail();
					 empty_bill++;
				 }
			 }
		 }
		 if(empty_bill==0)
		 {
			 System.out.println("No bill exist...");
		 }
		 System.out.println("If you want continue bill search ..press \'y\' else press\'n\'");
		 search_bill_repeat=s.next().charAt(0);
		}while(search_bill_repeat=='y');
	}
	
	
	public static void sales_detail() {
		System.out.println("Customer Name\t\tCustomer Id\t\tTotal sales Amount ");
		System.out.println("------------------------------------------------------------");
		for(customer_list cl : customer_details) {
			int sales_amount=0;
			for(bill_detail b : billDetail) {
				if(b.cus_id==cl.customer_id) {
						sales_amount=sales_amount+b.totalamount;
				}
			}
			System.out.println(cl.customer_name +"\t\t\t"+cl.customer_id+"\t\t"+sales_amount);
		}
	}
	
	public static void stock_detail() {
		System.out.println("Item Name\t\tItem Id\t\tItem Stack");
		System.out.println("------------------------------------------------------------");
		for(item_list b :product_details) {
			System.out.println(b.item_name +"\t\t\t"+b.item_id+"\t\t\t"+b.stock);
		}
	}
	
	public static void search_bill(){
		int search_bill_id;
		char search_bill_repeat;
		do {
		System.out.println("Enter bill id : ");
		search_bill_id=s.nextInt();
		int empty_bill=0;
		 for(bill_detail b : billDetail) {
			 if( b.bill_id==search_bill_id)
			 {
				 b.display_bill_detail();
				 empty_bill++;
			 }
		 }
		 if(empty_bill==0)
		 {
			 System.out.println("No bill exist...");
		 }
		 System.out.println("If you want continue bill search ..press \'y\' else press\'n\'");
		 search_bill_repeat=s.next().charAt(0);
		}while(search_bill_repeat=='y');
	}
	
	public static void search_customers_bill(){
		int search_cus_id;
		char search_bill_repeat;
		do {
		System.out.println("Enter customer id : ");
		search_cus_id=s.nextInt();
		int empty_bill=0;
		
		 for(bill_detail b : billDetail) {
			 if( b.cus_id==search_cus_id)
			 {
				 b.display_bill_detail();
				 empty_bill++;
			 }
		 }
		 if(empty_bill==0)
		 {
			 System.out.println("No bill exist...");
		 }
		 System.out.println("If you want continue bill search ..press \'y\' else press\'n\'");
		 search_bill_repeat=s.next().charAt(0);
		}while(search_bill_repeat=='y');
	}
	
	public static void add_customer() {
		String new_cus_name;
		System.out.println("Enter new customer name : ");
		new_cus_name=s.next();
		int new_cus_id;
		int alr=0;
		System.out.println("Enter new customer id :");
		new_cus_id=s.nextInt();
		for(customer_list cl : customer_details) {
			if(new_cus_id==cl.customer_id) {
				alr++;
			}
		}
		if(alr==0) {
			customer_details.add(new customer_list(new_cus_name,new_cus_id));
			System.out.println("Customer add successfully..");
		}
		else
			System.out.println("This customer is already in....!");

	}
	public static void bill_list() {
		//System.out.println(": BILL LIST :");
		int empty_bill=0;
		for(bill_detail b : billDetail) {			 
				 b.display_bill_detail();
				 empty_bill++;			 
		}
		if(empty_bill==0) {
			System.out.println("No bill");
		}
	}
}
class customer_list
{
	String customer_name;
	int customer_id;
	
	customer_list(String customer_name,int customer_id)
	{
		this.customer_name=customer_name;
		this.customer_id=customer_id;		
		
	}
}
class item_list{
	String item_name;
	int item_id;
	int stock;
	int price;
	item_list(String item_name,int item_id,int stock,int price){
		this.item_name=item_name;
		this.item_id=item_id;
		this.stock=stock;
		this.price=price;
	}
}
class line_det
{
	
	String pname;
	int quantity;
	int price;
	int totprice;
	int item_id;
	line_det(String pname,int quantity,int price,int totprice,int item_id)
	{
		this.pname=pname;
		this.quantity=quantity;
		this.price=price;
		this.totprice=totprice;
		this.item_id=item_id;
	}

	public void display()
	{
		System.out.printf("%s \t\t %d \t\t %d \t\t %d \t\t %d\n",pname,item_id,quantity,price,totprice);
	}
}
class bill_detail
{
	String cus_name;
	int cus_id;
	int bill_id;
	int totalamount;
	
	List<line_det> line_items;
	bill_detail(String cus_name,int cus_id,int bill_id,int totalamount,List<line_det> line_items)
	{
		this.cus_name=cus_name;
		this.cus_id=cus_id;
		this.bill_id=bill_id;
		this.totalamount=totalamount;
		this.line_items=line_items;
	}

	public void display_bill_detail()
	{
		
		System.out.println("-------------------------------------------------------------------------");  
		System.out.printf(" Customer Name : %s\t\t\t\t\t Bill Id : %d\n",cus_name,bill_id);
		System.out.print(" Customer Id   : "+cus_id+"\t\t\tDate :"+java.time.LocalDate.now()+"\n");
		System.out.println("-------------------------------------------------------------------------");  
		System.out.println("Product_Name  \tQuantity    \tRate    \tTotal Price");  
		System.out.println("-------------------------------------------------------------------------");  
		
		for(line_det b : line_items) {
			 b.display();
		}
		System.out.println("--------------------------------------------------------------------------");  
		System.out.println("\t\t\t\t\t\tTotal Amount = " + totalamount);
		System.out.println("--------------------------------------------------------------------------");  

	}
	
}
