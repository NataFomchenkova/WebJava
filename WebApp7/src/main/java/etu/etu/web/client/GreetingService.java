package etu.etu.web.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import etu.etu.web.shared.MedicineReader;


import java.util.List;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
    List<String> getReaderList();
    List<MedicineReader> getMedicineReaderList(String readerFIO);
}

