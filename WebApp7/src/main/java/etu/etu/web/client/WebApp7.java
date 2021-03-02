package etu.etu.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import etu.etu.web.shared.MedicineReader;
import java.util.List;

public class WebApp7 implements EntryPoint {
    private static final String SRV_ERR = "Server error! ";
    private static final String SRV_ERR_GET_READER_LIST = "Unable to get patient list.";
    private static final String SRV_ERR_GET_MEDICINE_LIST = "Unable to get a list of the patient's doctors.";
    private static final String GET_MEDICINE_LIST_BTN = "Get a list of doctors.";
    private static final String CLOSE_BTN = "Close";
    private static final String MEDICINE_WND_TITLE = "List of the patient's doctors: ";
    /** RPC-сервис */
    private final GreetingServiceAsync greetingService = GWT
            .create(GreetingService.class);
    public void onModuleLoad() {
        final ListBox readerListBox = new ListBox(false);
        final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
        final Label errorLabel = new Label();
        final Button sendButton = new Button(GET_MEDICINE_LIST_BTN);
        sendButton.addStyleName("sendButton");
        RootPanel.get("readerListBoxContainer").add(readerListBox);
        RootPanel.get("errorLabelContainer").add(errorLabel);
        RootPanel.get("sendButtonContainer").add(sendButton);
        readerListBox.setFocus(true);
        greetingService.getReaderList(new AsyncCallback<List<String>>() {
            public void onFailure(Throwable caught) {
                errorLabel.setText(SRV_ERR+SRV_ERR_GET_READER_LIST);
            }

            public void onSuccess(List<String> result) {
                oracle.clear();
                oracle.addAll(result);
                for(String r : result){readerListBox.addItem(r);
                }
            }
        });
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText(MEDICINE_WND_TITLE);
        dialogBox.setAnimationEnabled(true);
        final Button closeButton = new Button(CLOSE_BTN);
        closeButton.getElement().setId("closeButton");
        final HTML serverResponseLabel = new HTML();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");

        final CellTable<MedicineReader> table = createCellTable();
        dialogVPanel.add(table);

        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                sendButton.setEnabled(true);
                sendButton.setFocus(true);
            }
        });
        class RPCClickHandler implements ClickHandler, KeyUpHandler {
            public void onClick(ClickEvent event) {
                sendReaderFIOToServer();
            }
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    sendReaderFIOToServer();
                }
            }
            private void sendReaderFIOToServer() {
                errorLabel.setText("");
                final String readerFIO = readerListBox.getValue(readerListBox.getSelectedIndex());
                sendButton.setEnabled(false);
                greetingService.getMedicineReaderList(readerFIO,

                new AsyncCallback<List<MedicineReader>>() {
                    public void onFailure(Throwable caught) {
                        dialogBox.setText(SRV_ERR);
                        serverResponseLabel
                                .addStyleName("serverResponseLabelError");
                        serverResponseLabel.setHTML(SRV_ERR+SRV_ERR_GET_MEDICINE_LIST);
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }
                    public void onSuccess(List<MedicineReader> result) {
                        dialogBox.setText(MEDICINE_WND_TITLE + readerFIO);
                        table.setRowCount(result.size(), true);
                        table.setRowData(0, result);
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }
                });
            }
        }
        RPCClickHandler handler = new RPCClickHandler();
        sendButton.addClickHandler(handler);
    }
    private CellTable<MedicineReader> createCellTable() {
        final CellTable<MedicineReader> table = new CellTable<MedicineReader>();
        table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        TextColumn<MedicineReader> authorColumn = new TextColumn<MedicineReader>() {
            public String getValue(MedicineReader object) {
                return object.getSpecialty();
            }
        };
        table.addColumn(authorColumn, "Specialty");
        TextColumn<MedicineReader> titleColumn = new TextColumn<MedicineReader>() {
            public String getValue(MedicineReader object) {
                return object.getDoctorName();
            }
        };
        table.addColumn(titleColumn, "Full name of the doctor");
        TextColumn<MedicineReader> isReadColumn = new TextColumn<MedicineReader>() {
            public String getValue(MedicineReader object) {
                return object.isVisited() ? "Yes":"No";
            }
        };
        table.addColumn(isReadColumn, "Visited");

        return table;
    }
}
