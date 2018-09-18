import java.security.Provider;
import java.security.Security;

public class SmineQuery {
    public static  void main(String[] args) {
        Provider[] arr = Security.getProviders();
		for (int i = 0; i < arr.length; i++) {
			System.out.println("名字：" + arr[i].getName() + "\n版本：" + arr[i].getVersion() + "\n详情：" + arr[i].getInfo() + "\n");
		}

    }
}