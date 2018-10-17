package application;

import java.util.List;

import jdk.jshell.*;

public class JshellCalculator {
	
	private double fact(double x) {
		if (x==0) return 1;
		else return x*fact(x-1);
	}
	
	private double sqr(double x) {
		return x*x;
	}
	
	private double sqrt(double x) {
		return Math.sqrt(x);
	}


	public static String calculate(String input) {
		JShell jshell = JShell.create();
		try (jshell) { // automatyczne zwalnianie zasobów we frazie try-catch, od JDK9
			
			jshell.eval("private double fact(double x) {\r\n" + 
					"		if (x==0) return 1;\r\n" + 
					"		else return x*fact(x-1);\r\n" + 
					"	}");
			
			jshell.eval("private double sqr(double x) {\r\n" + 
					"		return x*x;\r\n" + 
					"	}");
			
			jshell.eval("private double sqrt(double x) {\r\n" + 
					"		return Math.sqrt(x);\r\n" + 
					"	}");
			
			List<SnippetEvent> events = jshell.eval(input);
			for (SnippetEvent e : events) {
				if (e.causeSnippet() == null) {
					return e.value();
				}
			}
		}
		return null;
	}
}

