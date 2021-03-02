package etu.etu.web.server;

import etu.etu.web.client.GreetingService;
import etu.etu.web.shared.MedicineReader;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
    private Map<String, List<MedicineReader>> db = null;
    public List<String> getReaderList() {
        if( db == null ){
            initDB();
        }
        String[] tmp = new String[db.keySet().size()];
        db.keySet().toArray(tmp);
        return Arrays.asList(tmp);
    }
    public List<MedicineReader> getMedicineReaderList(String readerFIO) {
        if( db == null ){
            initDB();
        }
        return db.get(readerFIO);
    }
    private void initDB(){
        db = new HashMap<String, List<MedicineReader>>();
        List<MedicineReader> entries1 = new ArrayList<MedicineReader>();
        entries1.add(new MedicineReader("Oculist", "Ivanov A.A.", true));
        entries1.add(new MedicineReader("Neurologist", "Zhuravleva E.Yu.",false));
        entries1.add(new MedicineReader("Surgeon", "Novikov S.Yu.", true));
        db.put("Akimov I.I.", entries1);
        List<MedicineReader> entries2 = new ArrayList<MedicineReader>();
        entries2.add(new MedicineReader("Oculist", "Ivanov A.A.",false));
        entries2.add(new MedicineReader("Therapist", "Lvova O.V.", true));
        db.put("Lobuzov N.I.", entries2);
        List<MedicineReader> entries3 = new ArrayList<MedicineReader>();
        entries3.add(new MedicineReader("Neurologist", "Zhuravleva E.Yu.", false));
        entries3.add(new MedicineReader("Surgeon", "Novikov S.Yu.",true));
        entries3.add(new MedicineReader("Therapist", "Lvova O.V.",  true));
        db.put("Kats A.M.", entries3);
        List<MedicineReader> entries4 = new ArrayList<MedicineReader>();
        entries4.add(new MedicineReader("Neurologist", "Zhuravleva E.Yu.", false));
        entries4.add(new MedicineReader("Oculist", "Ivanov A.A.",  false));
        entries4.add(new MedicineReader("Oncologist", "Zayarny O.G.",  false));
        db.put("Stepanova A.A.", entries4);
    }
}