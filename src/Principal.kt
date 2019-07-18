import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.stage.Stage

class Principal {
    @FXML
    var etiqueta:Label= Label()
    @FXML
    fun pulsame(event: ActionEvent)
    {//javax.swing.JOptionPane.showMessageDialog(null, "yyyy")
     // Visualizr venta clientes
      //  var proyector: Stage =Stage()
        val proyector = (event.getSource() as Node).getScene().getWindow() as Stage


        val root : Parent =
        FXMLLoader.load(javaClass.getResource("MateriasPrimasImagenes/MateriasPrimasImagenes.fxml"))
       // var root = FXMLLoader.load<Parent>(javaClass.getResource("clientes/pantallaClientes.fxml"))
        val escena: Scene =   Scene(root)
        proyector.scene = escena

        proyector.title="Gestión del Metal"

        val ico = Image("Imagenes/icono.jpg")
        proyector.icons.add(ico)

        proyector.show()



    }
}