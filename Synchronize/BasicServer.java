package edu.Brandeis.cs131.Common.MengdiZhu;

import java.util.ArrayList;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Server;

public class BasicServer extends Server {
	private ArrayList<Client> newArry;
	public BasicServer(String name) {
		super(name);
		newArry = new ArrayList<Client>();
	}

	@Override
	public boolean connectInner(Client client) {
		// TODO Auto-generated method stub
		synchronized(this){
			if(newArry.isEmpty()){
				newArry.add(client);
				return true;
			}
			else if((newArry.size() == 1) 
					&&(newArry.get(0) instanceof SharedClient)
					&& (client instanceof SharedClient)
					&&(newArry.get(0).getIndustry() != client.getIndustry())){
					{newArry.add(client);
					return true;}
			}
			else{return false;}
		}	
	}

	@Override
	public void disconnectInner(Client client) {
		synchronized(this){
			newArry.remove(client);
			notifyAll();
		}
	}
}



