package lru;
import java.util.*;
public class Least {
	static Scanner s=new Scanner(System.in);
	static List<details> elements=new ArrayList<details>();
	
	static int x=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*elements.add(new details(1,'a',0));
		elements.add(new details(2,'b',0));
		elements.add(new details(3,'c',0));
		elements.add(new details(4,'d',0));
		elements.add(new details(5,'e',0));*/
		
		int option;
		int rep_fun=1;

		do {
			System.out.println("Your options : ");
			System.out.print("1.get\n2.put\n3.Display\nEnter option : ");
			option=s.nextInt();
			switch(option)
			{
				case 1:
					get();
					break;
				case 2:
					put();
					break;
				case 3:
					display();
					break;		
				default:
					System.out.println("Enter proper input... ");
			}
			System.out.println("If you want continue...press 0");
			rep_fun=s.nextInt();
			}while(rep_fun==0);
		
	}
	public static void get()
	{
		Long timeInMillis = System.currentTimeMillis();

		int key;
		
		System.out.print("Enter key...");
		key=s.nextInt();
		for(details d : elements)
		{
			if(key==d.k)
			{
				System.out.println("key="+d.k);
				System.out.println("value="+d.v);
				d.count=timeInMillis;
				System.out.println("used times="+d.count);
			}
		}
	}
	public static void put()
	{
		Long timeInMillis = System.currentTimeMillis();
	//	elements.add(new details(2,'b',0));
		int key=s.nextInt();
		char value;
		System.out.println("Enter value...");
		value=s.next().charAt(0);
		
		if(x==0)
		{
			x++;
			elements.add(new details(key,value,timeInMillis));
		}
		else {
				if(key<=5)
				{
					
					for(details d : elements)
					{
						if(key==d.k)
						{
							d.v=value;
							d.count=timeInMillis;
						}
						else
						{
							elements.add(new details(key,value,timeInMillis));
							break;
						}
					}					
				}
				else
				{
					
					Long m=timeInMillis;
					for(details d : elements)
					{
						if(m>d.count)
						{
							m=d.count;
						}
					}
					for(details d : elements)
					{
						if(m==d.count)
						{
							d.v=value;
						//	elements.add(new details(key,value,timeInMillis));
		
							d.count=m;
							System.out.println("Value added...");
							break;
						}
					}
				}	
			}	
	}
	public static void display()
	{
		System.out.println("KEY\tVALUES\tUSING TIMES");
		for(details d : elements)
		{
			System.out.println(d.k+"\t"+d.v+"\t"+d.count);
		}
	}
}
class details
{
	int k;
	char v;
	Long count;
	details(int k,char v,Long count)
	{
		this.k=k;
		this.v=v;
		this.count=count;
	}
}
