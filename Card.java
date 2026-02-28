public class Card {
	
	private String BLACK = "\033[0;30m";   // BLACK
	private String RED = "\033[0;31m";     // RED
	private String GREEN = "\033[0;32m";   // GREEN
	private String YELLOW = "\033[0;33m";  // YELLOW
	private String BLUE = "\033[0;34m";    // BLUE
	private String PURPLE = "\033[0;35m";  // PURPLE
	private String CYAN = "\033[0;36m";    // CYAN
	private String WHITE = "\033[0;37m";
	
	private int cost; //likely will go unused
	private int dmg;
	private int armor;
	private int heal;
	private int stun; //Adds time to enemy

	private String name;
	private String description; //optional addition to ToString
	
	public Card duplicate; 


	private int burn;
	private boolean hitAll;
	private boolean duper;
	private boolean buff;
	private boolean curse; //can't be removed by any normal means
	private boolean status; //gone after battle
	private boolean ephemeral; //exhaust from slay the spire
	private boolean upgraded; //cards can only be upgraded ONCE. try to add perks to them beforehand in gameplay!
	private boolean modded;	//applied when card is modded, can stack, just an indicator for the name/toString
	
	
	//TODO: ADD NECCESARY FUTURE VALUES TO 'DUPLICATE'
	
	private int type;	//used in constructor
	
	public Card(int t) 
	{
		
		type=t;
		name="";
		description="";
		upgraded = false;
		if(type==-2) 
		{
			name="Daze";
			status=true;
			ephemeral=true;
		}
		if(type==-1) 
		{
			name="Scar"; 
			curse=true;
		}
		if(type==0) 
		{
			name="Empty";
		}
		if(type==1) 
		{
			name="Swing";
			dmg=3;
		}
		if(type==2) 
		{
			name="Breathe";
			heal=1;
		}
		if(type==3) 
		{
			name="Patch-Up";
			armor=2;
		}
		if(type==4) 
		{
			name="Sweep";
			dmg=2;
			hitAll=true;
			description="Hits all opponents";
		}
		if(type==5) 
		{
			name="Rage";
			description=
"Each time this card is played, add another Rage with +2 More dmg to the end of it's row until the end of battle";
			//buff=true;
			duper=true;
			
			dmg=2;
			
		}
		if(type==6) 
		{
			name="Surprise";
			dmg=10;
			ephemeral=true;
		}
		if(type==7) 
		{
			name="Snack";
			heal=5;
			ephemeral=true;
		}

		if (type == 8){
			name = "Ignite"; 	 //TODO: add burn status(?)
			dmg = 4;
			burn=2;
		}

		if (type == 9)
		{
			name = "Roundhouse";
			dmg = 4;
			stun = 1;
		}

		if(type == 10)
		{
			name = "Flashlight";
			dmg=1;
			stun = 2;
		}

		if(type == 11)
		{
			name = "Re-boot";
			dmg=2;
			heal=2;

		}
		if(type == 12)
		{
			name = "Block";
			armor = 5;
		}
		
	}
	
	
	
	
	public String toString() 
	{
		String temp=name+": ";
		
		if(dmg>0) 
		{
			temp+=RED+dmg+" DMG"+WHITE+", ";
		}
		if(heal>0) 
		{
			temp+=GREEN+"Heal "+heal+WHITE+", ";
		}
		if(armor>0) 
		{
			temp+=CYAN+armor+" Armor"+WHITE+", ";
		}
		if(ephemeral) 
		{
			temp+=PURPLE+" Ephemeral"+WHITE+", ";
		}
		if(!description.equals("")) 
		{
			temp+=" "+description;
		}
		if(temp.substring(temp.length()-2).equals(", ")) 
		{
			temp=temp.substring(0,temp.length()-2);
		}
		if(modded) 
		{
			temp="➕"+temp;
		}
		if(upgraded) 
		{
			temp="✨"+temp;
		}
		
		return temp;
	}
	
	public String toButtonString() 
	{
		String temp=name+": ";
		
		if(dmg>0) 
		{
			temp+=dmg+" DMG"+", ";
		}
		if(heal>0) 
		{
			temp+="Heal "+heal+", ";
		}
		if(armor>0) 
		{
			temp+=armor+" Armor"+", ";
		}
		if(ephemeral) 
		{
			temp+=" Ephemeral"+", ";
		}
		if(stun>0)
		{
			temp+=stun+" Stun"+", ";
		}
		if(!description.equals("")) 
		{
			temp+=" "+description;
		}
		if(temp.substring(temp.length()-2).equals(", ")) 
		{
			temp=temp.substring(0,temp.length()-2);
		}
		if(modded) 
		{
			temp="➕"+temp;
		}
		if(upgraded) 
		{
			temp="✨"+temp;
		}
		
		return temp;
	}
	
	
	public Card makeDuplicate() 
	{
		

		duplicate= new Card(type);
	
		duplicate.setHeal(heal);
		duplicate.setDMG(dmg);
		duplicate.setArmor(armor);
		
		duplicate.setUpgraded(upgraded);
		duplicate.setModded(modded);
		duplicate.setHitAll(hitAll);
		duplicate.setEphemeral(ephemeral);
		duplicate.setCurse(isCurse());
		
		
		if(type==5) 
		{
			
			duplicate.setDmg(dmg+2);
		}
		
		
		return duplicate;
	}
	
	
	public boolean isDuper() 
	{
		return duper;
	}
	
	public void setDuper(boolean d) 
	{
		duper = d;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public int getCost() 
	{
		return cost;
	}
	
	public int getDMG() 
	{
		return dmg;
	}
	
	public void setDmg(int d) 
	{
		dmg=d;
	}
	
	
	public int getArmor() 
	{
		return armor;
	}
	
	public void setArmor(int a) 
	{
		armor = a;
	}
	
	public int getHeal() 
	{
		return heal;
	}
	
	public void setHeal(int h) 
	{
		heal = h;
	}
	
	public String getName() 
	{
		String temp = name;
		if(upgraded) 
		{
			temp= "✨"+name;
		}
		if(modded) 
		{
			temp="➕"+temp;
		}
		return temp;
	}

	public int getStun()
	{
		return stun;
	}

	public void setDMG(int initDMG) 
	{
		dmg=initDMG;
	}
	
	public boolean isHitAll()
	{
		return hitAll;
	}
	
	public void setHitAll(boolean h) 
	{
		hitAll=h;
	}
	
	public boolean isUpgraded() 
	{
		return upgraded;
	}
	
	public void setUpgraded(boolean u) 
	{
		upgraded=u;
	}
	
	public void setModded(boolean m) 
	{
		modded=m;
	}
	
	public boolean isEphemeral() 
	{
		return ephemeral;
	}
	
	public void setEphemeral(boolean e) 
	{
		ephemeral = e; 
	}
	
	public boolean isCurse() 
	{
		return curse;
	}
	
	public void setCurse(boolean c) 
	{
		curse = c;
	}

	public int getBurn()
	{
		return burn;
	}
}