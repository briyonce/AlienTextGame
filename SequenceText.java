import java.util.concurrent.TimeUnit;

public class SequenceText {

	public static final boolean noNewLine = false;	//I just put this here for clarification

	//Sequence one
	public static void jessieIsAlive() {
		narrationText("JESSIE! THAT'S HER NAME!");
		narrationText("She looks at you - eyes wide with fear.");
		narrationText("\"Wh..Wha... What WAS THAT THING????\" She screams. ");
		narrationText("You: \"I... I don't know, Jessie. Let's get out of here. There may be more.");
		narrationText("J: \"Okay...\"");
		narrationText("She is obviously very shaken by the whole experience. The salt from her tears stain her cheeks.");
		narrationText("The two of you exit the room. Never to return.");
		narrationText("You: We need to get you to Med Bay ASAP.", 0);
	}
	public static void youMonster() {
		narrationText("You monster.");
		narrationText("Brushing away your thoughts of cowardice, you decide to address the situation at hand.");
		narrationText("\"Okay... so we're not alone on this ship...\"");
		narrationText("\"I need to find the captain.\"", 2);
	}

	//Sequence two
	public static void sequenceTwoMedBay(Player player) {
		narrationText("MED BAY. 7:00", 2);
		narrationText("You: \"So... Jessie. Do you know what happened?\"", noNewLine);
		narrationText("J: \"Not really... I only remember up to the party last night.\"");
		narrationText("J: \"I woke up to some strange sounds in the room... then that thing.\"");
		narrationText("You place some gauze over her wound. It looks serious.");
		narrationText("J: \"Thank you so much for saving me... I really owe you one.\" She winces.");
		narrationText("You: \"Of course!\" \"I just wish I could've acted sooner...\" You think to yourself.");
		narrationText("You look up at Jessie. The blood is already soaking through from the massive gash on her forehead.", 0);
		narrationText("She looks faint.");
		narrationText("You: Hey, Jessie. Rest up a bit. I'm going to see if I can find the captain...", 0, noNewLine);
		narrationText(" Maybe she knows what's going on. AND " + player.getSpouse().toUpperCase() + "!!?");
		narrationText("Oh God, " + player.getSpouse() + ".");
		narrationText("You look over at her again. It didn't take long.");
		narrationText("You turn to leave the room.");
	}
	public static void sequenceTwoAlone(Player player) {
		narrationText("You're parched, famished, dazed. You deserve it.");
		narrationText("\"What the hell is going on here?\"");
		narrationText("\"AND WHERE IS " + player.getSpouse().toUpperCase() + "?\"");
	}
	public static void whyDoTheyAlwaysChooseRed() {
		narrationText("Looking out into the hall you can see the main power is out.", 0);
		narrationText(" The walls are tinted a faint red from the reserve lights.", 3);
		narrationText("\"Why do they always choose red....\"", 0);
	}


	public static void narrationText(Stirng text) {
		System.out.println(text);
		System.put.println();		//new line
		TimeUnit.SECONDS.sleep(3);	//default time at 3 seconds
	}
	public static void narrationText(Stirng text, int alternateTime) {
		System.out.println(text);
		System.put.println();		//new line
		TimeUnit.SECONDS.sleep(alternateTime);
	}
	public static void narrationText(String text, boolean newLine) {
		System.out.println(text);
		if(newLine)
			System.out.prinln()
		TimeUnit.SECONDS.sleep(3);	//default time at 3 seconds
	}
	public static void narrationText(String text, int alternateTime, boolean newLine) {
		System.out.println(text);
		if(newLine)
			System.out.prinln()
		TimeUnit.SECONDS.sleep(alternateTime);
	}
}