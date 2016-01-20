package edu.Brandeis.cs131.Common.MengdiZhu;

import edu.Brandeis.cs131.Common.Abstract.Industry;

public class BasicClient extends MyClient {
	public BasicClient(String name, Industry industry){
		super(name,industry);		
	}
	public String toString() {
        return String.format("%s BASIC %s", this.getIndustry(), this.getName());
    }	
}

