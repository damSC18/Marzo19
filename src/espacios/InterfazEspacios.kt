package espacios

import javafx.application.Application
import javafx.event.Event
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage




public class InterfazEspacios: Application() {


    override fun start(stage: Stage) {

        val root : Parent = FXMLLoader.load(javaClass.getResource("espacio.fxml"))
        val scene: Scene =   Scene(root)

        stage.scene = scene
        stage.title="Gestión del Metal"

       // val ico = Image("Imagenes/icono.jpg")
       // stage.icons.add(ico)

        stage.show()

        // Desactiva cerrar ventana desde la X
        stage.setOnCloseRequest { e: Event ->
            e.consume()
         }

    }

    fun initialize() {
        println("Initialize")


    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(InterfazEspacios::class.java)
        }
    }
}
