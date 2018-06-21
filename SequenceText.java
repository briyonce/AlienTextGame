import java.util.concurrent.TimeUnit;

public class SequenceText {

	public static final boolean noNewLine = false;	//I just put this here for clarification

	public static void jessieIsAlive() {
		//TODO: move the narration sequences into this file
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