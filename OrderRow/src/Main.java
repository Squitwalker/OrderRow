import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//import javax.swing.JFrame;

public class Main {
	
	static Scanner in = new Scanner(System.in); 
	static Random r = new Random();
	
	public static final String BLACK = "\033[0;30m";   // BLACK
	public static final String RED = "\033[0;31m";     // RED
	public static final String GREEN = "\033[0;32m";   // GREEN
	public static final String YELLOW = "\033[0;33m";  // YELLOW
	public static final String BLUE = "\033[0;34m";    // BLUE
	public static final String PURPLE = "\033[0;35m";  // PURPLE
	public static final String CYAN = "\033[0;36m";    // CYAN
	public static final String WHITE = "\033[0;37m";   // WHITE
	
	//public static JFrame frame = new JFrame();
	
	
	
	
	public static void makeMap(ArrayList<Integer> map) 
	{
		map.add(r.nextInt(0,3));
		map.add(r.nextInt(0,3));
		map.add(r.nextInt(0,3));
		
		map.set(r.nextInt(0,2), 0);
		
	}
	
	public static String getMapString(ArrayList<Integer> map) 
	{
		String temp= YELLOW+"|| ";
		
		for(int i:map) 
		{
			String o ="";
			
			if(i==0) 
			{
				o="fight";
			}
			if(i==1) 
			{
				o="shop ";
			}
			if(i==2) 
			{
				o="random ";
			}
			
			temp+=o+" || ";
		}
		
		temp=temp.substring(0, temp.length()-1)+WHITE;
		
		return temp;	
	}
	
	public static String getRowString(ArrayList<Card> row) 
	{
		String temp="|| ";
		
		for(Card i:row) 
		{
			temp+=i+" || ";
		}
		temp=temp.substring(0,temp.length()-1);
		
		
		return temp;
	}
	
	public static boolean allDead(ArrayList<Enemy> opp) 
	{
		for (Enemy o:opp) 
		{
			if(!o.isDead()) 
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean cardUpgrade(Card c) 
	{
		if(c.isUpgraded()) 
		{
			return false;
		}
		
		if(c.getDMG()>0) 
		{
			c.setDmg(c.getDMG()+2);
		}
		
		if(c.getHeal()>0) 
		{
			c.setHeal(c.getHeal()+2);
		}
		
		if(c.getArmor()>0) 
		{
			c.setArmor(c.getArmor()+2);
		}
		
		
		c.setUpgraded(true);
		
		return true;
	}
	
	public static void applyMod(Mod mod, Card card) 
	{
		card.setArmor(card.getArmor()+mod.getArmor());
		card.setHeal(card.getHeal()+mod.getHeal());
		card.setDmg(card.getDMG()+mod.getDMG());
		
		if(mod.isHitAll()) 
		{
			card.setHitAll(true);
		}
		if(mod.isEphemeral()) 
		{
			card.setEphemeral(true);
		}
		if(mod.isDuper()) 
		{
			card.setDuper(true);
		}
		
		System.out.println(mod.getName()+" was applied to "+card.getName()+"!");
		card.setModded(true);
	}
	
	
	public static void main(String[] args) {
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//I'm thinking the plot/context for this game is that, in the far-flung future,
		//everyone has upgraded their brains with brain-chips, the 'NomOS' but the mega-corporation 
		//founded on their creation (characterized by a hungry brain mascot) 
		//has become power hungry and erases people's memory
		//at the end of every day to keep their consumers loyal. 
		
		//You, a rogue cyborg, are tasked with traveling a few blocks down
		//And hacking into their database, stopping the reset-loop. 
		//However, they're onto you... 
		
		//Possible Class Ideas!:
		//Rogue: Default
		//Faculty: Rows are played automatically, instead you may choose to MOVE one card
		//Dancer: After a card is played, it moves to the end of the row below it
		
		//TODO: choose a better name?
		//Order-Row (name pending) is a monster/badguy-slaying RPG game 
		//where you attack by using a set order of cards that is difficult to change (a 'row'),
		//your only choices in battle are to either 
		//choose a 'row' of cards to play in sequence,
		//or use an item, then aim each card
		
		//frame.setSize(1100, 1000);
		//frame.setFocusable(true);

		
		
		
		
		//frame.add(hero);
		//frame.setVisible(true);
		
		
		
		
		
		int MAXHP=30; //player's HPCAP
		int HP=30; //player's HP, difficult to heal
		int ARM=0; //player's armor, sets to 0 at the start of every round
		int rowCount=2; //amount of rows player has
		int cash = 500;//TODO: remove test cash amount
		
		ArrayList<Card> deck = new ArrayList<>();
		ArrayList<Mod> mods = new ArrayList<>();
		//perhaps add a way to choose a deck or class?
		
		
		
		boolean go = true;
		
		
		System.out.println("deck contains");
		deck.add(new Card(1));
		deck.add(new Card(1));
		deck.add(new Card(3));
		deck.add(new Card(2));
		
		
		
		System.out.println(getRowString(deck));
		
		int roundCount=0;
		
		
		
		
		
		
		//TODO map + Row Setup
		
		
		
		while(go&&HP>0) 
		{
			
			ArrayList<Integer> map = new ArrayList<>(); //list of ints that represent what player can go to for the next few rounds
			ArrayList<Integer> route1 = new ArrayList<>();
			ArrayList<Integer> route2 = new ArrayList<>();
			
			
			ArrayList<ArrayList<Card>> rows = new ArrayList<>(); //the rows you set before battle
			ArrayList<ArrayList<Card>> rowsB = new ArrayList<>(); //the rows you use in battle, a copy of 'rows'. 
			rows.clear();
			rowsB.clear();
			for(int i = 0; i<rowCount; i++) 
			{
				rows.add(new ArrayList<Card>());
				rowsB.add(new ArrayList<Card>());
			}
			
			
			
			
			ArrayList<Card> tempDeck = new ArrayList<>();
			
			for(Card i : deck) 
			{
				tempDeck.add(i);
			}
			
			if(mods.size()>0) 
			{
				System.out.println("Time to Apply Mods!");
				Boolean modding = true;
				
				
				while(modding&&mods.size()>0)
				{
					
					
					for(int i=0; i<mods.size(); i++) 
					{
						System.out.println((i+1)+"): "+mods.get(i).toString());
					}
					System.out.println("Type # of Mod to choose it, or type 0 to stop: ");
					
					
					
					
					int choice = in.nextInt();
					
					if(choice==0) 
					{
						modding=false;
					}
					else if(choice>=mods.size()+1) 
					{
						System.out.println("Please type # of valid mod");
					}
					else
					{
						Mod pick = mods.get(choice-1);
						for(int i=0; i<deck.size(); i++) 
						{
							System.out.println("Card "+(i+1)+"): "+deck.get(i).getName());						
						}
						
						System.out.println("Type # of Card to Apply "+pick.getName()+" to: ");
						int choice2 = in.nextInt()-1;
						
						while(choice2<0||choice2>deck.size()-1) 
						{
							System.out.println("Please enter valid #: ");
							choice2 = in.nextInt()-1;
						}
							
						applyMod(mods.get(choice-1), deck.get(choice2));
						mods.remove(choice-1);
									
					}
					
					
					
				}
			}
			
			//Visuals for picking cards/adding to rows
			
			System.out.println("Time to assemble your rows!");
	
			while(tempDeck.size()>0) 
			{
				
				//choose Card to add
				System.out.println("Choose # of Card from deck to add");
				
				int tempDex=0;
				for(Card i:tempDeck) 
				{
					tempDex++;
					System.out.println("#"+tempDex+" "+i);
				}
				
				
				int choice2= in.nextInt();
				
				System.out.println("Row count: "+rowCount+". Add cards to row, Type which row to add cards to: ");
				
				//print rows		
				for(int i=0; i<rows.size(); i++) 
				{
					System.out.println("Row "+(i+1)+": ");
					for(Card x : rows.get(i)) 
					{
						System.out.print(x.getName()+" ");
					}
					System.out.println();
				}
				
				int choice = in.nextInt();	
				
				
				
				//ensure valid row
				while(choice>rowCount||choice<1) 
				{
					System.out.println("Please choose a valid Row: "); 
					choice = in.nextInt();
				}
				
				
				
				//TODO improve/revise this system
				
				rows.get(choice-1).add(tempDeck.remove(choice2-1));
			}
			
			
			for(int i=0; i<rows.size(); i++) 
			{
				System.out.println("Row "+(i+1)+": ");
				for(Card x : rows.get(i)) 
				{
					System.out.print(x+" ");
				}
				System.out.println();
			}
			
			
			
			
			 //0 is a fight, 1 is a shop, 2 is a random
			
			makeMap(route1);
			makeMap(route2);
			
			System.out.println();
			System.out.println(GREEN+HP+"/"+MAXHP+ " HP"+WHITE);
			System.out.println("You have "+YELLOW+"$"+cash+" cash"+WHITE);
			System.out.println("Choose a Route: ");
			System.out.println("Route 1: "+getMapString(route1));
			System.out.println("Route 2: "+getMapString(route2));
			
			
			boolean choose = true;
			while(choose)
			{
				int choice =in.nextInt();
				if(choice==1) 
				{
					map=route1;
					choose=false;
				}
				if(choice==2) 
				{
					map=route2;
					choose=false;
				}
			}
			
			
			for(int m=0; m<map.size(); m++) 
			{
				Boolean specialEncounter=false; 
				Enemy special = null;
				//Make true when setting the next enemy for a special encounter.
				roundCount++;
				rowsB.clear();
				
				for(int i=0; i<rowCount; i++) 
				{
					rowsB.add(new ArrayList<Card>());
				}
				
				for(int i=0; i<rows.size(); i++) 
				{
					for(int x=0; x<rows.get(i).size(); x++) 
					{
						rowsB.get(i).add(rows.get(i).get(x));
					}
				}
				//copy rows to rowsB before each round!
				
				System.out.println("Round "+roundCount);
				
				String part = "";
				
				if(map.get(m)==0) 
				{
					part="fight";
				}
				if(map.get(m)==1) 
				{
					part="shop";
				}
				if(map.get(m)==2) 
				{
					part="random";
				}
				
				System.out.println("You approached a "+part);
				
				
				if(part.equals("shop")) 
				{
					boolean shopping=true;
					Card sale1 = new Card(5); //TODO new Card(r.nextInt(1,8));
					Card sale2 = new Card(r.nextInt(1,8));
					Card sale3 = new Card(r.nextInt(1,8));
					int price1 = r.nextInt(25,126);
					int price2 = r.nextInt(25,126);
					int price3 = r.nextInt(25,126);
					if(r.nextInt(10)==1) 
					{
						cardUpgrade(sale1);
						price1+=20;
					}
					if(r.nextInt(25)==1) 
					{
						cardUpgrade(sale2);
						price2+=20;
					}
					if(r.nextInt(25)==1) 
					{
						cardUpgrade(sale3);
						price3+=20;
					}
					
					Mod sale4 = new Mod(r.nextInt(1,7));
					Mod sale5 = new Mod(r.nextInt(1,7));
					Mod sale6 = new Mod(r.nextInt(1,7));
					
					int modPrice=1;
					
					while(shopping) 
					{
									
						System.out.println("Current Balance: "+YELLOW+"$"+cash+WHITE);
						System.out.println();
						System.out.println("1): Buy new Row: "+YELLOW+"$"+(100*rows.size())+WHITE);
						System.out.println("Cards:");
						
						
						
						//chance to find expensive upgraded card
						
						
						System.out.println("2): "+sale1.toString()+": "+YELLOW+"$"+price1+WHITE);
						System.out.println("3): "+sale2.toString()+": "+YELLOW+"$"+price2+WHITE);
						System.out.println("4): "+sale3.toString()+": "+YELLOW+"$"+price3+WHITE);
						System.out.println("5): Remove a Card (FROM DECK): "+YELLOW+"$500"+WHITE);
						System.out.println("6): Upgrade a Card (FROM DECK): "+YELLOW+"$300"+WHITE);
						System.out.println("Mods: ");
						System.out.println("7): "+sale4.toString()+": "+YELLOW+"$"+modPrice*100+WHITE);
						System.out.println("8): "+sale5.toString()+": "+YELLOW+"$"+modPrice*100+WHITE);
						System.out.println("9): "+sale6.toString()+": "+YELLOW+"$"+modPrice*100+WHITE);
						
						System.out.println("Type # of Purchase or 0 to leave");
						
						int choice = in.nextInt();
						if(choice==0) 
						{
							shopping=false;
						}
						if(choice==1) 
						{
							if(cash>=(100*rows.size())) 
							{
								cash-=100*rows.size();
								rows.add(new ArrayList<Card>());
								rowCount++;
								System.out.println("You now have "+rows.size()+" rows!");
							}
							else 
							{
								System.out.println("Insufficient funds!");
							}
						}
						if(choice==2) 
						{
							if(cash>=price1) 
							{
								deck.add(sale1);
								cash-=price1;
								System.out.println(sale1.getName()+" was added to your deck!");
							}
							else 
							{
								System.out.println("Insufficient funds!");
							}
						}
						if(choice==3) 
						{
							if(cash>=price2) 
							{
								deck.add(sale2);
								cash-=price2;
								System.out.println(sale2.getName()+" was added to your deck!");
							}
							else 
							{
								System.out.println("Insufficient funds!");
							}
						}
						if(choice==4) 
						{
							if(cash>=price3) 
							{
								deck.add(sale3);
								cash-=price3;
								System.out.println(sale3.getName()+" was added to your deck!");
							}
							else 
							{
								System.out.println("Insufficient funds!");
							}
						}
						if(choice==5) 
						{
						
							if(cash>=500) 
							{
								System.out.println();
								int i=0;
								for(Card c : deck) 
								{
									i++;
									System.out.println("Card "+i+"):"+ c.getName());
								}
								System.out.println();
								System.out.println("Type the number of Card to remove");
								int choice2 = in.nextInt()-1;
								System.out.println(deck.remove(choice2).getName()+" was removed from the deck!");
								cash-=500;
							}
							else 
							{
								System.out.println("Insufficient funds!");
							}
						}
						if(choice==6) 
						{
							if(cash>=300) 
							{
								cash-=300;
								System.out.println();
								int i=0;
								for(Card c : deck) 
								{
									i++;
									System.out.println("Card "+i+"):"+ c.getName());
								}
								System.out.println();
								System.out.println("Type the number of Card to upgrade: ");
								int choice2 = in.nextInt()-1;
								while (!cardUpgrade(deck.get(choice2))) 
								{
									System.out.println("Please choose a valid card: ");
									choice2=in.nextInt()-1;
								}
								System.out.println(deck.get(choice2).getName()+" was upgraded!");
							}
							else 
							{
								System.out.println("Insufficient funds!");
							}
						}
						if(choice==7) 
						{
							if(cash>=modPrice*100) 
							{
								cash-=modPrice*100;
								System.out.println("Purchased "+sale4.getName());
								mods.add(sale4);
								
								modPrice++;
							}
							else 
							{
								System.out.println("Insufficient funds!");
							}
						}
						if(choice==8) 
						{
							if(cash>=modPrice*100) 
							{
								cash-=modPrice*100;
								System.out.println("Purchased "+sale5.getName());
								mods.add(sale5);
								
								modPrice++;
							}
							else 
							{
								System.out.println("Insufficient funds!");
							}
						}
						if(choice==9) 
						{
							if(cash>=modPrice*100) 
							{
								cash-=modPrice*100;
								System.out.println("Purchased "+sale6.getName());
								mods.add(sale6);
								
								modPrice++;
							}
							else 
							{
								System.out.println("Insufficient funds!");
							}
						}
						
						
					}//while shopping
					
					//TODO more purchases (EX: items)
				}//shop
				if(part.equals("random")) 
				{
					int random = r.nextInt(0,11);
					if(random==0) 
					{
						System.out.println("Yikes... A huge set of traps stands in your way. Would you like to:");
						System.out.println("1): Sprint past (Lose 5 Max HP)");
						System.out.println("2): Run while covering your face (Lose 15 HP)");
						System.out.println("3): Take a Leap of Faith (Remove A Random Card from Deck, 50% chance to halve health)");
						
						System.out.println();
						System.out.println("Type # of choice: ");
						int choice = in.nextInt();
						System.out.println();
						
						if(choice==1) 
						{
							MAXHP-=5;
							if(HP>MAXHP) 
							{
								HP=MAXHP;
								
							}
							System.out.println("You got hit AND lost your breath... still more to go. HP:"+GREEN+HP+"/"+MAXHP+WHITE);
						}
						if(choice==2) 
						{
							HP-=15;
							System.out.print("That looked like it hurt... HP:"+GREEN+HP+"/"+MAXHP+WHITE);
						}
						if(choice==3) 
						{
							int randomCardNum = r.nextInt(0,deck.size());
							System.out.println("You took a leap of Faith; and the card '"+deck.remove(randomCardNum).getName()+"' was removed from your deck!");
							int chance = r.nextInt(0,2);
							if(chance==0) 
							{
								HP-=HP/2;
								System.out.println(" -aaaaand then you landed on your face. HP:"+GREEN+HP+"/"+MAXHP+WHITE);
							}
						}
					} //Traps
					if(random==1) 
					{
						System.out.println("You walk down the road past the sounds of chanting. A gang is betting on fights");
						System.out.println("1): Bet on the tough guy: 75% to earn $50, 25% to lose $500");
						System.out.println("2): Bet on the little guy: 25% to earn $500, 75% to lose $50");
						System.out.println("3): Get in on the next fight: Battle"); //TODO: when adding special items, make a 'street-champ belt' here.
						System.out.println("4): Leave. ");
						
						System.out.println();
						System.out.println("Type # of choice:");
						int choice = in.nextInt();
						
						if(choice==1) 
						{
							int chance = r.nextInt(0,5);
							if(chance==0) 
							{
								System.out.println("No...! that little guy can fight. Lost $500");
								cash-=500;
							}
							else 
							{
								System.out.println("Obviously, the big guy won. Gained $50");
								cash+=50;
							}
						}
						if(choice==2) 
						{
							int chance = r.nextInt(0,5);
							if(chance==0) 
							{
								System.out.println("He actually won! Gained $500");
								cash+=500;
							}
							else 
							{
								System.out.print("He didn't stand a chance... $50");
								cash+=50;
							}
						}
						if(choice==3) 
						{
							part="fight";
							specialEncounter=true;
							special= new Enemy(r.nextInt(-2,0)); //little guy or street champ
							
							//TODO
						}
						
					}//street Fight
					if (random==2) 
					{
						System.out.println("A strange man approaches you with his hands behind his back.");
						System.out.println("\"For $50, you can take one of the things im holding. choose an arm!\"");
						System.out.println("1): Tap the left arm: Add a random Card to deck");
						System.out.println("2): Tap the right arm: 50% chance to recieve $100");
						System.out.println("3): Attempt to Leave : 75% chance to enter battle");
						System.out.println();
						System.out.println("Type # of choice: ");
						
						int choice = in.nextInt();
						
						if(choice==1) 
						{
							cash-=50;
							deck.add(new Card(r.nextInt(0,6)));
							System.out.println("A "+deck.get(deck.size()-1).getName()+  " was added to your deck");
						}
						if (choice==2) 
						{
							cash-=50;
							System.out.print("He holds out his right arm, and in it...");
							int chance= r.nextInt(0,2);
							if(chance ==0) 
							{
								System.out.println(" was $100! The man congratulates you");
								cash+=100;
							}
							else 
							{
								System.out.println(" was nothing. The man smirks, pats you on the shoulder, and walks on.");
							}
						}
						if(choice==3) 
						{
							int chance = r.nextInt(0,4);
							if(chance==0) 
							{
								System.out.println("The man shakes and rattles toward you!");
								specialEncounter=true;
								special = new Enemy(-3);
							}
							else 
							{
								System.out.println("The man hurls insults as you pace away");
							}
						}
						
						
					}
					//Pick-An-Arm
	
					if(random==3) 
					{
						
					}
					
					
					//TODO more mysteries,,, 👻
				}//random
				if(part.equals("fight")) 
				{
					ArrayList<Enemy> opp = new ArrayList<Enemy>();
					if(specialEncounter&&special!=null)
					{
						opp.add(special);
					}
					else 
					{
						opp.add(new Enemy(1)); //TODO: spawn enemy based on progress, spawn multiple
						opp.add(new Enemy(2));
					}
					
					ARM=0;
					for(Enemy o : opp) 
					{
						System.out.println("A "+o.getName()+" approached");
					}
					
					System.out.println();
					
					while(!allDead(opp)&&HP>0) 
					{
						
						
						System.out.print("You have "+GREEN+HP+"/"+MAXHP+" HP"+WHITE);
						if(ARM>0) 
						{
							System.out.print(" and "+CYAN+ARM+" armor"+WHITE);
						}
						System.out.println();
						
						for(int i=0; i<opp.size(); i++) 
						{
							if(opp.get(i).getHP()>0) 
							{
								System.out.println(opp.get(i).getName()+GREEN+" HP: "+opp.get(i).getHP()+WHITE);
							}
							else 
							{
								System.out.println(opp.get(i).getName()+RED+" (DEAD)"+WHITE);
							}
						}
						
						System.out.println();
						//HP display
						
						for(Enemy o : opp) 
						{
							if(!o.isDead()) 
							{
								System.out.println(o.getTime() +" turns until "+o.getName()+ " attacks for "+RED+o.getDMG()+" DMG"+WHITE);

							}
						}
						//count-downs
						
						System.out.println();
						
						for(int i=0; i<rowsB.size(); i++) 
						{
							System.out.print("Row "+(i+1)+": "+getRowString(rowsB.get(i)));
							System.out.println();
						}
						System.out.println();
						System.out.println("Enter # of Row to play from: ");
						
						int choice = in.nextInt()-1;
						
						while(choice<0||choice>=rowsB.size()||rowsB.get(choice).size()<=0) 
						{
							System.out.println("Please choose a valid row");
							choice = in.nextInt()-1;
						}
						
						
						
						Card pick;
						int target=-1;
						
						
						
						
						pick = rowsB.get(choice).get(0);
						
						if(pick.isHitAll()&&pick.getDMG()>0)
						{
							for(Enemy o : opp) 
							{
								if(!o.isDead())
								{
									System.out.println(o.getName()+ " Takes "+RED+pick.getDMG()+" DMG"+WHITE);
									o.takeDMG(pick.getDMG());
								}
							}
						} 
						
						
						{
							
						}
						if(pick.getDMG()>0&&!pick.isHitAll()) //PICK TARGET TODO add more conditions to pick target for IE De-buffs
						{
							//check if choice is valid
							boolean targeting=true;
							while(targeting) 
							{
								
								int left=0;
								for(int i=0; i<opp.size(); i++) 
								{
									if(!opp.get(i).isDead()) 
									{
										left++;
									}
								}
								
								
								if(left==1) 
								{
									left =0;
									for(int i=0; i<opp.size(); i++) 
									{
										if(!opp.get(i).isDead()) 
										{
											left=i;
										}
									}
									
									target=left;
									targeting=false;
								}
								//one left, automatically select target
								else 
								{
									for(int i=0; i<opp.size(); i++) 
									{
										if(opp.get(i).getHP()>0) 
										{
											System.out.println("Enemy "+(i+1)+"): "+opp.get(i).getName()+GREEN+" HP: "+opp.get(i).getHP()+WHITE);
										}
										else 
										{
											System.out.println("Enemy "+(i+1)+"): "+opp.get(i).getName()+RED+" (DEAD)"+WHITE);
										}
										
									}
									System.out.println("Choose a target: ");
									target = in.nextInt()-1;
									if(target<0||target>opp.size()) 
									{
										System.out.println("choose valid target");
									}
									else if(opp.get(target).isDead()) 
									{
										System.out.println("choose valid target");
									}
									else 
									{
										targeting=false;
									}
								}
								//choose target
								
							}
							
							
						}
						
						if(pick.getArmor()>0) 
						{
							ARM+=pick.getArmor();
							System.out.println("You gained "+CYAN+pick.getArmor()+" armor"+WHITE);							
						}
						if(pick.getDMG()>0&&!pick.isHitAll()) 
						{
							
							opp.get(target).takeDMG(pick.getDMG()); //put other DMG calculations here(?) 
							System.out.println(opp.get(target).getName()+" takes "+RED+ pick.getDMG()+ " DMG"+WHITE);
						}
						if(pick.getHeal()>0) 
						{
							HP+=pick.getHeal();
							if(HP>MAXHP) 
							{
								HP=MAXHP;
								System.out.println("You healed to full!");
							}
							else 
							{
								System.out.println("You healed "+GREEN+pick.getHeal()+" HP"+WHITE);
							}
						}
						//Check card values 
						//TODO choose target to hit
						
						if(rowsB.get(choice).get(0).isDuper()) 
						{
							rowsB.get(choice).add(rowsB.get(choice).get(0).makeDuplicate());
						}
						
						rowsB.get(choice).add(rowsB.get(choice).remove(0)); 
						//remove card at front of row and put it at the end of the row 
						
						
						if(rowsB.get(choice).get(rowsB.get(choice).size()-1).isEphemeral()) 
						{
							System.out.println(rowsB.get(choice).remove(rowsB.get(choice).size()-1).getName()+" faded!");
						}
						//remove if ephemeral
						
						for(Enemy o : opp) 
						{
							boolean oppTurn= o.tickDown();
							if(oppTurn&&!o.isDead()) 
							{
								int remainder=0;
								ARM-=o.getDMG();					
								if(ARM<=0) 
								{
									remainder-=ARM;
									ARM=0;
								}
								if(ARM>0) 
								{
									remainder=0;
								}
								//Armor stuff
								
								HP-=remainder;
								System.out.println("You took "+RED+o.getDMG()+" DMG"+WHITE);
							}
						}
						//Enemy turn ^
						
						//TODO display enemy data, do player turn
						
						if(allDead(opp)&&HP>0) 
						{
							System.out.println("You win!");
							double mult = ((m+1)/2)*(HP/MAXHP)+1;
							int reward = r.nextInt(10,51);
							reward=(int)mult*reward;
							cash+=reward;
							System.out.println("You earned "+YELLOW+"$"+reward+" cash!"+WHITE);
							
							//REWARDS!!! $D
						}
						
					}
					
					
					
					
					System.out.println();
					
				}//fight
			}//map
		} //go&&HP>0
		System.out.println(RED+"GAME OVER D:"+WHITE);
	}//main
}//Main
		
