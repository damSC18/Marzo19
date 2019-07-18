package espacios


import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
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
import javafx.scene.control.*


class cEspacios2 {
    @FXML
    private var eGrafico: Pane? = null

    @FXML
    var tGrafico : TitledPane?=null
    @FXML
    var fija : ImageView?=null
    @FXML
    var listaImagenes: ComboBox<String> = ComboBox()


    var imagenBorrada:Button= Button("")
    var xx  = 0.0
    var yy=0.0
    var elementoBorrado:Boolean=false
    var cImagenes=0
    var urlImagen:String=""

@FXML
fun selecionarImagenes(event: ActionEvent)
{   // Obtenemos la url de la imagen seleccionmada en el combobox
    urlImagen =listaImagenes.value.toString()
}


    @FXML
    fun coordenadas(event: MouseEvent) {

        println("Raton:"+event.button)
        print( event.sceneY)
        var imagen: Button = Button("Botón")
        // Craer Elemento
        if(event.button.toString().equals("PRIMARY")) {



            cImagenes++
            imagen.id="$cImagenes"
            // Mover el elemento
           imagen.setOnMouseDragged {
         //   imagen.setOnDragDetected {

                    e ->
                imagenBorrada=imagen
                println("Drag " +e.target + "Imagen "+urlImagen)
                // Borran la imagen del Pane

              //  eGrafico!!.getChildren().remove(imagen)
               // e.consume()
            }

            imagen.setOnMouseDragged(){   e ->

               var  xxx=e.sceneX
               var  yyy=e.sceneY


                imagen.layoutX = xxx
                imagen.layoutY = yyy




            }


            // Crear evento para la imagen , al hacer clic la tenemos que borrar

            imagen.style="-fx-background-color:#0E6B80"

            imagen.layoutX = event.sceneX
            imagen.layoutY = event.sceneY

            imagen.setText("")
            imagen.maxWidth=35.0
            imagen.maxHeight=35.0



          var imagenF: ImageView = ImageView(urlImagen)//"file:\\J:\\Marzo\\src\\Imagenes\\icono.jpg")



            imagenF.fitWidth = 30.0
            imagenF.fitWidth = 30.0
            imagenF.fitHeight = 30.0
            imagen.setGraphic(imagenF)
            imagen.setMouseTransparent(false)

            eGrafico!!.children.add(imagen)

        //    registraEvento(imagen)




        }
        else{
           /* xx=event.sceneX
            yy=event.sceneY
            elementoBorrado=true

            imagenBorrada.style="-fx-background-color:#0E6B80"

            imagenBorrada.layoutX = xx
            imagenBorrada.layoutY = yy

            imagenBorrada.setText("")
            imagenBorrada.maxWidth=35.0
            imagenBorrada.maxHeight=35.0

            eGrafico!!.children.add(imagenBorrada)*/
            //copiar(event)
            registraEvento(imagen)
        }
    }

    fun registraEvento(imagen:Button){

       imagen.setOnMouseClicked({ e ->
           println("Click ...:" +e.target)

            imagenBorrada=imagen
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
        listaImagenes!!.items.add("file:\\J:\\Marzo\\src\\Imagenes\\impresora.png")
        listaImagenes!!.items.add("file:\\J:\\Marzo\\src\\Imagenes\\lista.png")
        listaImagenes!!.items.add("file:\\J:\\Marzo\\src\\Imagenes\\Borrar.png")
        listaImagenes!!.selectionModel.select(0)
        urlImagen ="file:\\J:\\Marzo\\src\\Imagenes\\impresora.png"
    }







}