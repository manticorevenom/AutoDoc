module com.autodoc.autodocgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.autodoc.autodoc to javafx.fxml;
    exports com.autodoc.autodoc;
}