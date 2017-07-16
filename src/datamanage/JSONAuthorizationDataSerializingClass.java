package datamanage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class JSONAuthorizationDataSerializingClass {
	
	@JsonProperty("code")
	private String codeGeneratedOnWebSite;
	@JsonProperty("cid")
	private String chatID;
	 	
	@Override
	public String toString() {
		return (codeGeneratedOnWebSite + " : " + chatID);
	}
}
