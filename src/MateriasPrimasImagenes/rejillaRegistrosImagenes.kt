package MateriasPrimasImagenes

import MateriasPrimassImagenes.bdMateriasPrimassImagenes
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.Image
import javafx.stage.Stage
import java.sql.ResultSet

class rejillaRegistrosImagenes {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////
    @FXML
    private var rejillaRegistros: TableView<datosVisualizarRejilla>? = null


    @FXML
    private var cId: TableColumn<datosVisualizarRejilla, Int>? = null
  //  var cIdPresupuesto = TableColumn<objetoPresupuesto, Int>()
    @FXML
    private var cNombre: TableColumn<datosVisualizarRejilla, String>? = null
    @FXML
    private var cDireccion: TableColumn<datosVisualizarRejilla, String>? = null
    @FXML
    private var cPoblacion: TableColumn<datosVisualizarRejilla, String>? = null
    @FXML
    private var cProvincia: TableColumn<datosVisualizarRejilla, String>? = null

    @FXML
    private var cTelefono: TableColumn<datosVisualizarRejilla, String>? = null

    @FXML
    private var cSectorComercial: TableColumn<datosVisualizarRejilla, String>? = null


    fun initialize() {
            visualiza()
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////// REJILLA REGISTROS
/////////////////////////////////////////////////////////////////////////////////////////////////////


    fun visualiza() {
        var bd: bdMateriasPrimassImagenes = bdMateriasPrimassImagenes()
        bd.conexion()
     //   var rs: ResultSet = bd.leerPrimerRegistro()
        cId!!.setCellValueFactory(PropertyValueFactory<datosVisualizarRejilla, Int>("Id"))
        cNombre!!.setCellValueFactory(PropertyValueFactory<datosVisualizarRejilla, String>("Nombre"))
        cDireccion!!.setCellValueFactory(PropertyValueFactory<datosVisualizarRejilla, String>("Direccion"))
        cPoblacion!!.setCellValueFactory(PropertyValueFactory<datosVisualizarRejilla, String>("Poblacion"))
        cProvincia!!.setCellValueFactory(PropertyValueFactory<datosVisualizarRejilla, String>("Provincia"))
        cTelefono!!.setCellValueFactory(PropertyValueFactory<datosVisualizarRejilla, String>("Telefono"))
        cSectorComercial!!.setCellValueFactory(PropertyValueFactory<datosVisualizarRejilla, String>("SectorComercial"))

        var consulta: ResultSet = bd.leerPrimerRegistro()

        while (consulta!!.next()) {
            println("Dato " + consulta!!.getString(1) + " " + consulta!!.getString(2))

            var registroLeido: datosVisualizarRejilla = datosVisualizarRejilla(
                consulta!!.getInt(1),
                consulta!!.getString(2),
                consulta!!.getString(3),
                consulta!!.getString(4),
                consulta!!.getString(5),
                consulta!!.getString(6),
                consulta!!.getString(7)
            )

           rejillaRegistros!!.items.add(registroLeido)


        }

        ///////////////////////////////////
    }
    @FXML
    fun finalizar(event: ActionEvent) {
        try {

            val root = FXMLLoader.load<Parent>(javaClass.getResource("pantallaClientes.fxml"))

            // val root = FXMLLoader.load<Parent>(javaClass.getResource("..//Metal.fxml"))
            val scene = Scene(root)
            val appStage = (event.getSource() as Node).getScene().getWindow() as Stage
            appStage.scene = scene
            appStage.toFront()
            appStage.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}