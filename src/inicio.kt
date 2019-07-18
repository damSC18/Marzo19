import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import java.sql.Connection

class inicio:Application(){

   override fun start(proyector: Stage){
       val root : Parent = FXMLLoader.load(javaClass.getResource("principal.fxml"))
       val escena: Scene =   Scene(root)

       proyector.scene = escena


       proyector.title="Gestión del Metal"

       val ico = Image("Imagenes/icono.jpg")
       proyector.icons.add(ico)

       proyector.show()


   }
   fun initialize()
   {

   }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(inicio::class.java)
        }
        var connection: Connection? = null
    }
}