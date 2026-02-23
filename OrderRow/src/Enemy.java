public class Enemy {

	private int dmg;
	private int hp; 
	private String name;
	private int time; //when it reaches zero, this enemy attacks;
	private int maxTime; 
	private boolean dead;
	
	
	public Enemy(int type) 
	{
		dead=false;
		if(type==-3) 
		{
			hp=12;
			name="Shady man";
			time=3;
			dmg=8;
		}
		if(type==-2) 
		{
			hp=10;
			name="Little Guy"; //Special encounter in random
			time=2;
			dmg=2;
		}
		if(type==-1) 
		{
			hp=20;
			name="Street Champ"; //Special encounter in random
			time=4;
			dmg=10;
		}
		if(type==1) 
		{
			hp=8;
			name = "Thug";
			time = 3;
			dmg=4;
		}
		if(type==2) 
		{
			hp=5;
			name = "Pest";
			time = 2;
			dmg=3;
		}
		maxTime =time; 
	}
	
	//tick down turn clock. if it hits 0, return true so this can perform its action
	public boolean tickDown() 
	{
		time--;
		if(time==0) 
		{
			time=maxTime;
			return true;
		}
		return false;
	}
	
	public int getDMG() 
	{
		return dmg;
	}
	
	public int getHP() 
	{
		return hp;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void takeDMG(int amount) 
	{
		hp-=amount;
		if(hp<1) 
		{
			dead=true;
		}
	}
	
	public boolean isDead()
	{
		return dead;
	}
	
	public int getTime() 
	{
		return time; 
	}
}