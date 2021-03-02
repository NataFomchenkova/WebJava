package etu.etu.web.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import etu.etu.web.shared.MedicineReader;
import java.util.List;
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
    void getReaderList(AsyncCallback<List<String>> callback);
    void getMedicineReaderList(String readerFIO, AsyncCallback<List<MedicineReader>> callback);
}
