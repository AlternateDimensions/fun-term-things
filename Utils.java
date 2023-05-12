import java.util.Scanner;

public class Utils {
    // Utils is a neat little collection of methods that I use. Feel free to use them as well!
    // Frank Norris, '23

    private static Scanner in = new Scanner(System.in);

    // Typewriter method
    /* String text is printed character by character at either a designated delay (in milliseconds) or randomly generated delay based on 2*delay. Useful for fun text! */
	public static void typewriter(String text, double delay, boolean random, boolean newline){
		double actualDelay;
		for (char c : text.toCharArray()){
			if (random){
	        	actualDelay = ((int)(Math.random() * 51) + (delay*1000))/1000;
	        } else {
				actualDelay = delay;
			}
			wait(actualDelay);
			System.out.print(c);
      	}

      	if (newline){
        	System.out.print("\n");
      	}
		try{lock();} catch (Exception really){}
    }
	
    // Divider (default delay)
    /* Divider typewritten with a 0.05s delay or printed all at once */
	public static void divider(boolean useTypewriter, boolean newLine){
		String divider = Colors.CLEAR + "--------------------------------------------";
		if (useTypewriter){
			typewriter(divider, 50, false, newLine);
		} else {
			System.out.print(divider);
			if (newLine){
				System.out.print("\n");
			}
		}
		try{lock();} catch (Exception really){}
	}

    // Divider (default delay)
    /* Divider typewritten with a delay or variated delay */
	public static void divider(int delay, boolean random, boolean newLine){
		String divider = Colors.CLEAR + "--------------------------------------------";
		typewriter(divider, delay, random, newLine);
		try{lock();} catch (Exception really){}
	}


    // Wait
    /* Delays the thread by x seconds, with floating point input for a more precise control of delay. */
	public static void wait(double seconds){
		try{Thread.sleep((int) (seconds*1000)); lock();} catch (Exception really){}
	}


    // Clear
    /* Clears the terminal of all lines on screen and resets formatting. */
	public static void clear(){
		System.out.print(Colors.CLEAR + "\033[H\033[2J");
        try{lock();} catch (Exception really){}
	}


    // Lock & Unlock
    /* This in theory should prevent people from typing in the terminal but it has been known to not work perfectly (because of Java)*/
	public static void lock() throws Exception {
		Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "stty -echo </dev/tty"});
	}

	public static void unlock() throws Exception {
		Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "stty echo </dev/tty"});
	}

    // getInput
    /* Clean usage of Scanner that ignores input when not needed. Should work without needing this code in your actual files. Returns an empty string if error occurs. */
    public static String getInput(String lineStarter){
        System.out.print(lineStarter);
        String input = "";

        in = new Scanner(System.in);
        try {
        while(System.in.available() > 0) {
            System.in.read(new byte[System.in.available()]);
        }

        Util.unlock();
        input = in.nextLine();
        Util.lock();
        } catch (Exception a){return "";}
        return input;
    }
}
