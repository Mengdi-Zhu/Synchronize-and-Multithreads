package edu.Brandeis.cs131.Common.MengdiZhu;

import edu.Brandeis.cs131.Common.Abstract.Industry;

public class SharedClient extends MyClient {
	public SharedClient(String name, Industry industry){
		super(name,industry);
	}
	public String toString() {
        return String.format("%s SHARED %s", this.getIndustry(), this.getName());
    }
}
