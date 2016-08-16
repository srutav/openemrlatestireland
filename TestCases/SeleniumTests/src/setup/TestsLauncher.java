package setup;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import tests.Test1;




public class TestsLauncher {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String url="http://firsttest.hrgmj34rtq.us-west-2.elasticbeanstalk.com/interface/login/login_frame.php?site=default";
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { Test1.class });
		testng.addListener(tla);
		LaunchBrowser.launchBrowser(url);
		testng.run();
	}

}
