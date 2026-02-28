public class Mod {

	private String BLACK = "\033[0;30m";   // BLACK
	private String RED = "\033[0;31m";     // RED
	private String GREEN = "\033[0;32m";   // GREEN
	private String YELLOW = "\033[0;33m";  // YELLOW
	private String BLUE = "\033[0;34m";    // BLUE
	private String PURPLE = "\033[0;35m";  // PURPLE
	private String CYAN = "\033[0;36m";    // CYAN
	private String WHITE = "\033[0;37m";
	
	private String name;
	private String desc;
	
	private int dmg;
	private int armor;
	private int heal;
	
	private boolean hitAll; //hits all enemies
	private boolean duper; //duplicates
	private boolean ephemeral; //disappears after use until next batt
	
	//TODO: ADD EVERYTHING FROM HERE INTO APPLYMOD METHOD IN FUTURE ADDITIONS
	
	public Mod(int type) 
	{
		desc="";
		name="";
		if(type==1) 
		{
			name="Sharpness";
			dmg=2;
			
		}
		if(type==2) 
		{
			name="Metal";
			armor=2;
		}
		if(type==3) 
		{
			name="Soothing";
			heal=1;
		}
		if(type==4) 
		{
			name="Cloning";
			duper=true;
		}
		if(type==5) 
		{
			name="Ephemeral";
			heal=5;
			ephemeral = true;
		}
		if(type==6) 
		{
			name="Boogey-man's";
			dmg=5;
			ephemeral = true;
		}
		
		name+=" Mod";
		
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
		if(!desc.equals("")) 
		{
			temp+=" "+desc;
		}
		if(temp.substring(temp.length()-2).equals(", ")) 
		{
			temp=temp.substring(0,temp.length()-2);
		}
		
		
		return temp;
		
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getDesc() 
	{
		return desc;
	}
	
	
	
	public boolean isHitAll() 
	{
		return hitAll;
	}
	
	public boolean isDuper() 
	{
		return duper;
	}
	
	public boolean isEphemeral() 
	{
		return ephemeral;
	}
	
	public int getDMG() 
	{
		return dmg;
	}
	
	public int getArmor() 
	{
		return armor;
	}
	
	public int getHeal() 
	{
		return heal; 
	}
	
}
