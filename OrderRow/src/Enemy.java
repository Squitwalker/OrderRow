public class Enemy {

	private int dmg;
	private int hp; 
	private String name;
	private int time; //when it reaches zero, this enemy attacks;
	private int maxTime;
	private boolean dead;
	private boolean duckBoss;
	private int burn; //remaining turns of being burnt
	
	public Enemy(int type) 
	{
		burn=0;
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
		if (type == 3)
		{
			hp = 10;
			name = "Thin Coyote";
			time = 2;
			dmg=5;
		}
		if (type==4)
		{
			hp=12;
			name = "Rogue DeliveryBot";
			time = 6;
			dmg=4;
		}
		if (type == 5)   // Strong melee
		{
			hp = 15;
			name = "Brute";
			time = 2;
			dmg = 12;
		}

		if (type == 6)   // Fast assassin
		{
			hp = 13;
			name = "Thief";
			time = 1;
			dmg = 16;
		}

		if (type == 7)   // Very high health
		{
			hp = 25;
			name = "Athletic Thug";
			time = 2;
			dmg = 14;
		}

		if (type == 8)
		{
			hp = 20;
			name = "Dire Wolf";
			time = 1;
			dmg = 20;
		}

		if (type == 9)  // Low HP, very high damage with slow speed
		{
			hp = 10;
			name = "Droid";
			time = 5;
			dmg = 25;
		}

		if (type == 10) {
			hp = 13;
			name = "Rogue Bot";
			time = 7;
			dmg = 17;
		}
		if (type == 11) {
			hp = 11;
			name = "Marine Tech";
			time = 4;
			dmg = 13;
		}
		if (type == 12) {
			hp = 16;
			name = "Ant wires";
			time = 8;
			dmg = 9;
		}
		if (type == 13) {
			hp = 11;
			name = "Aggressive advertisement";
			time =4;
			dmg = 16;
		}
		if (type == 14) {
			hp = 7;
			name = "Terminator";
			time = 4;
			dmg = 20;

		}
		if (type == 2147483647) { // Boss
			hp = 50;
			name = "Duck.exe";
			time = 3;
			dmg = 25;
			duckBoss=true;
		}

		maxTime = time;
	}
	
	//tick down turn clock. if it hits 0, return true so this can perform its action
	public boolean tickDown() 
	{
		if (burn>0)
		{
			burn--;
			System.out.println(name+ "is burnt for 2 DMG");
			this.takeDMG(2);
		}
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

	//use when stunned
	public void addTime(int t)
	{
		if(duckBoss)
		{
			System.out.println("Woah... the duck is made of rubber and can't be stunned!");
			return;
		}
		while(t>0&&time<maxTime)
		{
			t--;
			time++;
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