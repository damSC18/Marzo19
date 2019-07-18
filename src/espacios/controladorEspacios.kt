package espacios

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.control.TitledPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.util.logging.Level
import java.util.logging.Logger
import java.util.regex.Pattern
import javafx.scene.layout.Pane
import groovy.util.Eval.x






class controladorEspacios {
    @FXML
    private var eGrafico: Pane? = null

    @FXML
    var tGrafico : TitledPane?=null
    @FXML
    var fija : ImageView?=null


    var imagenBorrada:ImageView?=null
    var xx  = 0.0
    var yy=0.0
    @FXML
    fun mover(event: MouseEvent) {
        println("Mover:"+event.button)


    }
    @FXML
    fun coordenadas(event: MouseEvent) {
        event.button
println(event.sceneX)
        println("Raton:"+event.button)
        print( event.sceneY)
        if(event.button.toString().equals("PRIMARY")) {




            var imagen: ImageView = ImageView("file:\\J:\\Marzo\\src\\Imagenes\\icono.jpg")


            imagen.setOnDragDetected {

                    e ->

                println("Drag " +e.target)
                // Borran la imagen del Pane

                eGrafico!!.getChildren().remove(imagen)
                e.consume()
            }



            // Crear evento para la imagen , al hacer clic la tenemos que borrar
            registraEvento(imagen)


            imagen.layoutX = event.sceneX
            imagen.layoutY = event.sceneY



            imagen.fitWidth = 30.0
            imagen.fitHeight = 30.0
            imagen.setMouseTransparent(false)

            eGrafico!!.children.add(imagen)
        }
        else{
            xx=event.sceneX
            yy=event.sceneY
            //copiar(event)
        }
    }

    fun registraEvento(imagen:ImageView){
        imagenBorrada=imagen
       imagen.setOnMouseClicked({ e ->
           println("Click " +e.target)
            // Borran la imagen del Pane
           eGrafico!!.getChildren().remove(imagen)
           e.consume();

       })

    }

    @FXML
    fun copiar(event: MouseEvent) {
        try {
            imagenBorrada!!.layoutX = xx
            imagenBorrada!!.layoutY = yy
            eGrafico!!.children.add(imagenBorrada)
        }catch(e:Exception){}
    }
    fun initialize() {

    }




fun VisualizaRejilla(event: ActionEvent) {
    //var proyector: Stage = Stage()
    val proyector = (event.getSource() as Node).getScene().getWindow() as Stage
    val root: Parent =
        FXMLLoader.load(javaClass.getResource("rejillaRegistros.fxml"))
    // var root = FXMLLoader.load<Parent>(javaClass.getResource("clientes/pantallaClientes.fxml"))
    val escena: Scene = Scene(root)
    proyector.scene = escena

    proyector.title = "Gestión del Metal"

    val ico = Image("Imagenes/icono.jpg")
    proyector.icons.add(ico)
    proyector.show()

}
    fun registerListener(cajaTexto: TextField ,ncaracteres:Int,nc:Int) {

        cajaTexto.textProperty().addListener { obs, oldText, newText ->

            if ( newText.length > ncaracteres) {
                cajaTexto.text=  oldText
            }
        }

        cajaTexto.focusedProperty().addListener { obs, oldText, newText ->

            // val p = Pattern.compile("[0-9]*.[0-9]*")
            var p = Pattern.compile("")
            var b:Boolean=true
            if(nc==9) {
                p = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}\$")
                val m = p.matcher(cajaTexto.text )
                b = m.find()
            }
            if(nc==6||nc==7 || nc==8 ) {
                println("NUMERO "+nc)
                p = Pattern.compile("[0-9]*")
                val m = p.matcher(cajaTexto.text )
                b = m.matches()
            }

            if(nc==5  ) {
                println("NUMERO "+nc)
                p = Pattern.compile("[0-9]{8}[A-Z]{1}")
                val m = p.matcher(cajaTexto.text )
                b = m.matches()
            }
            //   val b = m.find()
            println("Foco " +b)

            if(!b){
                cajaTexto.requestFocus()
                cajaTexto.style="-fx-text-fill:red"
            }
            else{cajaTexto.style="-fx-text-fill:black"}

        }
    }


}