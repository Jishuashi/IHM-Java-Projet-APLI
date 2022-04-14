module fr.uvsq.iutvelizy.apli.ihmjavaprojetapli {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.uvsq.iutvelizy.apli.ihmjavaprojetapli to javafx.fxml;
    exports fr.uvsq.iutvelizy.apli.ihmjavaprojetapli;
}