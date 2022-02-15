package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ProDoctorsList;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;

public class ProDoctorsListBoundary extends Boundary{
    int index =params.size()-1;
    private ProDoctorsList shobdek= (ProDoctorsList) params.get(index);



    @FXML
    private ImageView label;

    @FXML
    private ListView<String> Proslist;


    @FXML
    void submitAction(ActionEvent event) {
        if(Proslist.getSelectionModel().getSelectedItems().isEmpty()){
            MessageBoundary.displayError("please choose a doctor");
        }
        else{
            if(!Proslist.getSelectionModel().getSelectedItems().equals(null))
            {
                LinkedList<String> choice = new LinkedList<String>();
                String selectedString = Proslist.getSelectionModel().getSelectedItem();
                int point_index=selectedString.indexOf(".");
                int selectedindex = Integer.parseInt(selectedString.substring(0,point_index));
                System.out.println(selectedindex);
                int docId= shobdek.getProDoctors().get(selectedindex).getId();
                choice.add("#ProDocChoice" );
                choice.add(String.valueOf(docId));
                String id = String.valueOf(((Patient) params.get(0)).getId());
                //System.out.println(id);
                //String id = "2";
                //System.out.println(id);
                choice.add(id); // adding the id of the patient
                System.out.println(selectedString);
                int end=selectedString.indexOf("#");
                System.out.println("the end is:" +end);
                int StartOfClinicName=selectedString.indexOf(":");
                System.out.println(selectedString.substring(StartOfClinicName+1,end));
                choice.add(selectedString.substring(StartOfClinicName+1,end));
                try {
                    SimpleClient.getClient().sendToServer(choice);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

    @FXML
    void initialize() {
        System.out.println("I am in the init of ProDoctors");
        LinkedList<Doctor> DoctorsToShow = shobdek.getProDoctors();
        System.out.println(DoctorsToShow.size());
        int doc_num=0;
        for (int i = 0; i < DoctorsToShow.size(); i++) {
            for (int j = 0; j < DoctorsToShow.get(i).getClinicList().size(); j++) {
                Proslist.getItems().add(i+"."+j+" Clinic:" +DoctorsToShow.get(i).getClinicList().get(j).getClinicType()+ "# Dr." + DoctorsToShow.get(i).getLastName() + DoctorsToShow.get(i).getFirstName());
            }
        }
        Proslist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

}
