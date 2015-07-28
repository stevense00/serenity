package utilsPackage;

import java.util.ArrayList;
import java.util.List;

public class eBox {
	private List<String> docList;
	
	public eBox() {
		docList = new ArrayList<String>();
	}
	
	public void addDoc(String title) {
		this.docList.add(title);
	}

}
