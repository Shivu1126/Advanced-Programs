import java.util.*;
public class Banking_System {

	static Scanner s=new Scanner(System.in);
	static ArrayList<person_detail> persons=new ArrayList<person_detail>();
	static ArrayList<line_det> lines=new ArrayList<line_det>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		persons.add(new person_detail("Shiv", 123, 500, "Shiv123",null));
		persons.add(new person_detail("Hari", 124, 500, "Hari123",null));
		persons.add(new person_detail("Satiz", 125, 500, "Satiz123",null));
		int rep=1;
		do 
		{
			System.out.println("Your options...");
			System.out.println("1.Login         ");
			System.out.println("2.Create account");
			System.out.println("3.View customer details");
			System.out.println("4.Exit");
			System.out.println("Enter your option");
			int opt=s.nextInt();
			switch(opt)
			{
			case 1:
				Login();
				break;
			case 2:
				create();
				break;
			case 3:
				full_customers_details();
				break;
			default:
				break;	
			}			
			System.out.println("If you want continue...press 1...");
			rep=s.nextInt();
		}while(rep==1);

	}
	public static void Login()
	{
		int i=1;
		repeat:
			while(i==1)
			{

				int f=0;			

				System.out.println("Enter account number : ");
				int acc=s.nextInt();
				for(person_detail p:persons)
				{
					if(p.acc_no==acc)
					{
						f=1;
					}
				}
				if(f==0)
				{
					System.out.println("Account number not exist...pls create account or re-login");
					break repeat;
				}
				for(person_detail p:persons)
				{
					if(p.acc_no==acc)
					{					
						boolean rt=true;
						int c=0;
						System.out.println("Enter pass");
						String pw=s.next();
						while(rt)
						{

							if(pw.equals(p.pass))
							{
								Uses(p);
								System.out.println("Do you want continue ..press 1..");
								int rt1=s.nextInt();
								if(rt1==1)
									rt=true;
								else
									rt=false;
							}
							else if(c<2)
							{
								System.out.println("Re-enter password : ");
								pw=s.next();
								c++;
							}
							else
							{
								System.out.println("Goto change password");
								c=0;
								changepass(p);

							}							
						}

					}
				}			
				i=0;
			}
	}
	public static void Uses(person_detail p)
	{
		System.out.println("Enter yout option : ");
		System.out.println("1.View Transection");
		System.out.println("2.Withdraw");
		System.out.println("3.Deposite");
		int opt=s.nextInt();
		switch(opt)
		{
		case 1:
			View(p);
			break;
		case 2:
			withdraw(p);
			break;
		case 3:
			deposite(p);
			break;
		default:
			break;
		}
	}


	public static boolean changepass(person_detail p)
	{	
		System.out.println("Enter new password :");
		String new_pw=s.next();
		while(true)
		{
			if(check(new_pw))
			{
				p.pass=new_pw;
				System.out.println("Your password accepted");			
				return true;			
			}
			else
			{
				System.out.println("Re-enter password");
				new_pw=s.next();
			}
		}
	}
	public static boolean check(String new_pw)
	{
		if(new_pw.length()<=6)
			return false;
		char arr[]=new_pw.toCharArray();
		int cap=0,small=0,num=0;
		for(int i=0;i<new_pw.length();i++)
		{
			if(arr[i]>='A' && arr[i]<='Z')
				cap++;
			if(arr[i]>='a' && arr[i]<='z')
				small++;
			if(arr[i]>='0' && arr[i]<='9')
				num++;
		}
		if(cap>0 && small>0 && num>0)
		{
			return true;
		}
		return false;
	}


	public static void create()
	{
		System.out.println("Welcome.....");
		System.out.println("Enter your name : ");
		String name_c=s.next();
		int len=persons.size();
		person_detail p=persons.get(len-1);
		//System.out.println(len);
		int acc_no_c=p.acc_no+1;
		//System.out.println(acc_no_c);

		System.out.println("Create your password : ");
		String pass_c=s.next();
		while(true)
		{
			if(check(pass_c))
			{
				System.out.println("Your password is strong");
				break;
			}
			else
			{
				System.out.println("Your password is weak : ");
				System.out.println("Re-enter password");
				pass_c=s.next();
			}
		}
		persons.add(new person_detail(name_c, acc_no_c, 500, pass_c,null));
		System.out.println("Successfully created..");
		System.out.println("-------------------------------------");
		System.out.println("Your details.....");
		System.out.println("-------------------------------------");
		System.out.println("Name    : "+name_c);
		System.out.println("Acc_No  : "+acc_no_c);
		System.out.println("PassWord: "+pass_c);
		System.out.println("Acc_Bal :  500");
		System.out.println("-------------------------------------");
	}

	public static void full_customers_details()
	{

		System.out.println("---------------------------------------------------------------------");
		System.out.println("Name\t\tAccount.No\tBalance\tPassword");
		System.out.println("---------------------------------------------------------------------");
		for(person_detail p : persons)
		{
			System.out.println(p.name+"\t\t"+p.acc_no+"\t\t"+p.acc_balance+"\t\t"+p.pass);
		}
		System.out.println("---------------------------------------------------------------------");


	}

	public static void withdraw(person_detail p) {

		System.out.println("Enter which type of withdraw");
		System.out.println("1.ATM");
		System.out.println("2.Gpay");
		System.out.println("3.Bank");
		int type=s.nextInt();
		String v="";
		if(type==1)
			v="ATM";
		if(type==2)
			v="Gpay";
		if(type==3)
		{
			v="Bank";
		}
		boolean ch=true;
		while(ch)
		{
			System.out.println("Enter amount : ");
			int am=s.nextInt();
			if(am<=p.acc_balance) 
			{
				p.acc_balance-=am;
				System.out.println("Money depited");
				lines.add(new line_det(p.acc_no,v,"withdraw",am,p.acc_balance));
				System.out.println("Your Account Balance : "+p.acc_balance);

				ch=false; 
			}
			else
				System.out.println("insufficent money...");

		}
		//lines.add(new line_det(123,"atm","withdraw",200,300));
	}
	public static void deposite(person_detail p)
	{
		System.out.println("Enter which type of deposite");
		System.out.println("1.ATM");
		System.out.println("2.Gpay");
		System.out.println("3.Account Transfer");
		boolean k=true;
		String v="";
		int type=0;
		while(k)
		{	
			type=s.nextInt();			
			if(type==1)
			{v="ATM";k=false;}
			else if(type==2)
			{v="Gpay";k=false;}
			else if(type==3)
			{
				v="AccountTranfer";
				k=false;

			}
			else
				System.out.println("Enter 1 to 3....");			
		}
		if(type==3)
		{
			int f=1;
			
			System.out.println("Enter Transfer Account Num : ");
			int t_acc=s.nextInt();
			for(person_detail pq:persons)
			{
				if(pq.acc_no==t_acc)
				{
					f=0;
					int am=0;
					boolean f1=true;
					while(f1)
					{
						System.out.println("Enter amount : ");
						am=s.nextInt();
						if(p.acc_balance>=am)
						{
							pq.acc_balance+=am;		
							lines.add(new line_det(pq.acc_no,v,"Deposite",am,pq.acc_balance));
							System.out.println("Money credited to '"+pq.name+"'");

							p.acc_balance-=am;
							lines.add(new line_det(p.acc_no,v,"Tranfer",am,p.acc_balance));
							System.out.println("Your Account Balance : "+p.acc_balance);
							f1=false;
						}
						else
						{
							System.out.println("Insufficient Money...");
						}
					}
				}
			}
			if(f==1)
			{
				System.out.println("Account Number doesn't exist....");
			}
		}
		else
		{
			System.out.println("Enter amount : ");
			int am=s.nextInt();

			p.acc_balance+=am;		
			lines.add(new line_det(p.acc_no,v,"Deposite",am,p.acc_balance));
			System.out.println("Money credited...");
		}

	}

	public static void View(person_detail p)
	{
		String pname=p.name;
		int ac=p.acc_no;
		System.out.println("-----------------------------------------------------------");
		System.out.println("Name : " +p.name+ "\tAccount.No :"+p.acc_no);
		System.out.println("Via\t\tMethod\t\tAmount\t\tBalance");
		System.out.println("-----------------------------------------------------------");

		for(line_det l:lines)
		{
			if(ac==l.acc_no)
				System.out.println(l.via+"\t\t"+l.method+"\t\t"+l.amount+"\t\t"+l.acc_balance);
		}
		System.out.println("-----------------------------------------------------------");

	}	
}



class line_det
{

	int acc_no;
	String via;
	String method;
	int amount;
	int acc_balance;
	line_det(int acc_no,String via,String method,int amount,int acc_balance)
	{

		this.acc_no=acc_no;
		this.via=via;
		this.method=method;
		this.amount=amount;
		this.acc_balance=acc_balance;
	}
}
class person_detail
{
	String name;
	int acc_no;
	int acc_balance;
	String pass;
	List<line_det> lines;
	person_detail(String name,int acc_no,int acc_balance,String pass,List<line_det> lines)
	{
		this.name=name;
		this.acc_no=acc_no;
		this.acc_balance=acc_balance;
		this.pass=pass;
		this.lines=lines;
	}

}