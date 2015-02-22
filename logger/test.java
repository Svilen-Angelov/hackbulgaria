package logger;

public class test {


	public static void main(String[] args) {
		
		MyLogger cl = new ConsoleLogger();
		cl.log(2, "ERRORROROR");
		cl.log(7,"fdsfs");
		cl.log(1,"heyyyy");
		cl.log(-6,"wat");
		cl.log(3,"WAZZA dfds");
		cl.log(1,"Incoming Info");
		
		System.out.println();
		
		FileLogger fl = new FileLogger();
		fl.log(1, "Call 911 now");
		fl.log(8, "xxxf");
		fl.log(2, "tread lightly");
		fl.log(0, "swoosh");
		fl.log(3, "its alive");
		fl.log(4, "meh");
		fl.log(1, "testest");
		fl.log(2, "existing file test");
		fl.log(-5, "it works!");
		fl.closer();
		
		System.out.println();
		
		HTTPLogger hl = new HTTPLogger();
		hl.log(1, "Hope it works");
		hl.log(4, "Hope it doesnt");
		hl.log(2, "twoo");
		hl.log(-1, "welp");
		hl.log(3, "ANYBODY HERE?!");
		
		

	}

}
