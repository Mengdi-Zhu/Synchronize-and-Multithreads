package edu.Brandeis.cs131.Common.MengdiZhu;

import java.util.HashMap;
import java.util.LinkedList;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;
import edu.Brandeis.cs131.Common.Abstract.Server;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MasterServer extends Server {

	private final Map<Integer, List<Client>> mapQueues = new HashMap<Integer, List<Client>>();
	private final Map<Integer, Server> mapServers = new HashMap<Integer, Server>();
	public MasterServer(String name, Collection<Server> servers, Log log) {
		super(name, log);
		Iterator<Server> iter = servers.iterator();
		while (iter.hasNext()) {
			this.addServer(iter.next());
		}
	}

	public void addServer(Server server) {
		int location = mapQueues.size();
		this.mapServers.put(location, server);
		this.mapQueues.put(location, new LinkedList<Client>());
	}

	@Override
	public boolean connectInner(Client client) {
		// TODO Auto-generated method stub
		int key = this.getKey(client);
		synchronized (this.mapQueues.get(key)) {
			this.mapQueues.get(key).add(client);
			while (mapQueues.get(key).indexOf(client)!=0) {
				{
					try { 
						mapQueues.get(key).wait();
					} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					}
				}				
			}
		}
		synchronized(mapServers.get(key)){
			if (mapQueues.get(key).indexOf(client)==0) {
				while(!(mapServers.get(key).connect(client))){
					try { 
						mapServers.get(key).wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}					
				}
			}		
			synchronized(mapQueues.get(key)){
				mapQueues.get(key).remove(0);
				this.mapQueues.get(key).notifyAll();
			}
			return true;
		}
	}

	@Override
	public void disconnectInner(Client client) {
		int key = this.getKey(client);					
		this.mapServers.get(key).disconnect(client);
	}
	
	// returns a number from 0- mapServers.size -1
	// mUST be used when calling get() on mapServers or mapQueues
	private int getKey(Client client) {
		return client.getSpeed() % mapServers.size();
	}
}
