package proxy.example1;

public class ProxyPatternTest {

	public static void main(String[] args){
		CommandExecutor executor = new CommandExecutorProxy("Pankaj", "wrong_pwd");
		try {
			executor.runCommand("ls -ltr");
			executor.runCommand(" rm -rf abc.pdf");
		} catch (Exception e) {
			System.out.println("Exception Message::"+e.getMessage());
		}
		
	}

}
//https://www.digitalocean.com/community/tutorials/proxy-design-pattern