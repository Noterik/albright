package org.springfield.albright;

import org.springfield.albright.filters.EP_Image;
import org.springfield.albright.filters.EP_Video;
import org.springfield.albright.homer.LazyHomer;
import org.springfield.mojo.interfaces.ServiceInterface;
import org.springfield.mojo.interfaces.ServiceManager;

public class ServiceHandler implements ServiceInterface{
	
	private static ServiceHandler instance;
	
	public static ServiceHandler instance() {
		if (instance==null) {
			instance = new ServiceHandler();
			ServiceManager.setService(instance);
		}
		return instance;
	}
	
	public String getName() {
		return "albright";
	}
	
	public String get(String uri,String fsxml,String mimetype) {
		if (uri.indexOf("/ep_images/")!=-1) {
			return EP_Image.get(uri,fsxml,mimetype);
		}else if(uri.indexOf("/ep_videos/")!=-1) {
			return EP_Video.get(uri,fsxml,mimetype);
		}
		return null;
	}
	
	public String put(String path,String value,String mimetype) {
		return null;
	}
	
	public String post(String path,String fsxml,String mimetype) {
		return null;
	}
	
	public String delete(String path,String value,String mimetype) {
		return null;
	}
	
	
}
