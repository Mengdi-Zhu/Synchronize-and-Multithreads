package edu.Brandeis.cs131.Common.MengdiZhu;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Factory;
import edu.Brandeis.cs131.Common.Abstract.Industry;
import edu.Brandeis.cs131.Common.Abstract.Server;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;
import java.util.Collection;


public class ConcreteFactory implements Factory {

    @Override
    public Server createNewBasicServer(String label){
        return new BasicServer(label);
    	//throw new UnsupportedOperationException("Not supported yet.");    
    }

    @Override
    public Client createNewSharedClient(String label, Industry industry){
        return new SharedClient(label,industry);
    	//throw new UnsupportedOperationException("Not supported yet.");    
    }

    @Override
    public Client createNewBasicClient(String label, Industry industry){
        return new BasicClient(label,industry);
    	//throw new UnsupportedOperationException("Not supported yet.");    
    }

    @Override
    public Server createNewMasterServer(String label, Collection<Server> servers, Log log){
        return new MasterServer(label, servers, log);
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
