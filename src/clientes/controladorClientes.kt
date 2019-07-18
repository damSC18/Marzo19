package clientes

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.util.logging.Level
import java.util.logging.Logger
import java.util.regex.Pattern


class controladorClientes {
    // Campos del interfaz
    @FXML
    private var codigoPostal: TextField = TextField()
    @FXML
    private var direccion: TextField = TextField()
    @FXML
    private var personaContacto: TextField = TextField()
    @FXML
    private var provincia: TextField = TextField()
    @FXML
    private var sectorComercial: TextField = TextField()
    @FXML
    private var nombre: TextField = TextField()
    @FXML
    private var cif_Nif: TextField = TextField()
    @FXML
    private var web: TextField = TextField()
    @FXML
    private var telefono1: TextField = TextField()
    @FXML
    private var telefono2: TextField = TextField()
    @FXML
    private var id: TextField = TextField()
    @FXML
    private var poblacion: TextField = TextField()
    @FXML
    private var email: TextField = TextField()
    // Variables del programa
    var nuevo: Boolean = false
    var posicion: Int = 1
    // Estabelces conexion con el Modelo para acceso
    // a las funciones de Base de Datos(bdClientes.kt)
    var bd: bdClientes = bdClientes()


    //
// Buscar en el cursos actula
    @FXML
    internal var listacamposBuscar: ComboBox<String> = ComboBox()

    @FXML
    internal var PalabraBuscador: TextField = TextField()
    // El ResultSetMetaData nos devuelve la estructura de la tabla en la base de datos
    // Nombre de campos, tipo, estado
    private var lc: ResultSetMetaData? = null


    // Funciones del Interfaz
    @FXML
    fun nuevoRegistro(event: ActionEvent) {

        nuevo = true

        codigoPostal.text = ""
        direccion.text = ""
        personaContacto.text = ""
        provincia.text = ""
        sectorComercial.text = ""
        nombre.text = ""
        cif_Nif.text = ""
        web.text = ""
        telefono1.text = ""
        telefono2.text = ""
        id.text = ""
        poblacion.text = ""
        email.text = ""
    }

    @FXML
    fun grabarregistro(event: ActionEvent) {
        var SQL: String = ""
        if (nuevo) {
            SQL = " insert into clientes (Nombre,Direccion,Poblacion,Provincia,CodigoPostal,CifNif,Telefono1,Telefono2,Email,Web,PresonaContacto,SectorComercial) values(" +
                    "'" + nombre.text + "','" + direccion.text + "','" + poblacion.text + "','" + provincia.text +
                    "','" + codigoPostal.text + "','" + cif_Nif.text + "','" + telefono1.text + "','" + telefono2.text +
                    "','" + email.text + "','" + web.text + "','" + personaContacto.text + "','" +
                    sectorComercial.text + "')"
        } else {
            SQL = " update   clientes set nombre='" + nombre.text + "',Direccion='" + direccion.text + "',Poblacion='" +
                    poblacion.text + "',Provincia='" + provincia.text + "',CodigoPostal='" + codigoPostal.text +
                    "',CifNif='" + cif_Nif.text + "',Telefono1='" + telefono1.text + "',Telefono2='" + telefono2.text +
                    "',Email='" + email.text + "',Web='" + web.text + "',PresonaContacto='" + personaContacto.text +
                    "',SectorComercial='" + sectorComercial.text + "' where id=" + id.text

        }
        bd.trabajoRegistro(SQL)

        bd.conexion()
        if (nuevo) {
            ultiimoRegistro()
        }
    }

    @FXML
    fun borrarRegistro(event: ActionEvent) {
        var SQL: String = ""

        SQL = " delete from  clientes where id=" + id.text
        bd.trabajoRegistro(SQL)

        bd.conexion()

        ultiimoRegistro()
    }

    @FXML
    fun primerRegistro() {
        var rs: ResultSet = bd.leerPrimerRegistro()
        VisualizaRegistro(rs)
    }

    @FXML
    fun anteriorRegisto(event: ActionEvent) {
        var rs: ResultSet = bd.leerAnteriorRegistro()
        VisualizaRegistro(rs)
    }

    @FXML
    fun siguienteRegistro(event: ActionEvent) {
        var rs: ResultSet = bd.leerSiguienteRegistro()
        VisualizaRegistro(rs)
    }

    @FXML
    fun ultiimoRegistro() {
        var rs: ResultSet = bd.leerUtimoRegistro()
        VisualizaRegistro(rs)
    }






    @FXML
    fun salirClientes(event: ActionEvent) {
        try {

            val root = FXMLLoader.load<Parent>(javaClass.getResource("principal.fxml"))

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

    //  @FXML
    // var webb:WebView=WebView()
    fun initialize() {

        bd.conexion()
        primerRegistro()
        // Obtener lista Campos y Visualizarlos en el comboBox de Panel Buscar
        rellenarListaCamposBuscar()
        registerListener(nombre,50,1)
        registerListener(direccion,60,2)
        registerListener(poblacion,50,3)
        registerListener(provincia,30,4)
        registerListener(cif_Nif,10,5)
        registerListener(codigoPostal,5,6)
        registerListener(telefono1,10,7)
        registerListener(telefono2,10,8)
        registerListener(email,50,9)
        registerListener(web,50,10)
        registerListener(personaContacto,60,11)
        registerListener(sectorComercial,80,12)


    }

    fun VisualizaRegistro(rs: ResultSet) {
        nuevo = false
        posicion = rs.row

        id.text = rs.getInt(1).toString()
        nombre.text = rs.getString("nombre")
        direccion.text = rs.getString(3)
        poblacion.text = rs.getString(4)
        provincia.text = rs.getString(5)
        codigoPostal.text = rs.getString(6)
        cif_Nif.text = rs.getString(7)

        telefono1.text = rs.getString(8)
        telefono2.text = rs.getString(9)
        email.text = rs.getString(10)
        web.text = rs.getString(11)
        personaContacto.text = rs.getString(12)
        sectorComercial.text = rs.getString(13)
    }

    @FXML
    fun SigBuscar(mouseEvent: MouseEvent) {
        val snc = listacamposBuscar.value.toString()
        var rs: ResultSet = bd.fleerSiguienteBuuscar(PalabraBuscador.text, snc)
        VisualizaRegistro(rs)
    }

    @FXML
    fun AntBuscar(mouseEvent: MouseEvent) {
        val snc = listacamposBuscar.value.toString()
        var rs: ResultSet = bd.fleerAnteriorBuuscar(PalabraBuscador.text, snc)
        VisualizaRegistro(rs)
    }

    @FXML
    fun FinalizarBuscar(mouseEvent: MouseEvent) {

    }

    private fun rellenarListaCamposBuscar() {

        lc = (bd.leerCamposTabla()).getMetaData()

        try {
            //   listacamposBuscar.items.clear()
            //  listacamposBuscar.removeAllItems();

            for (i in 1..lc!!.getColumnCount()) {
                //        println("TIpo de campo en la table Producto " + lc!!.getColumnType(i) + lc!!.getColumnTypeName(i))
                if (lc!!.getColumnType(i) == 12 || lc!!.getColumnType(i) == 4) {

                    listacamposBuscar.items.add(lc!!.getColumnName(i).toString())

                }
            }
        } catch (ex: SQLException) {
            Logger.getLogger(controladorClientes::class.java!!.getName()).log(Level.SEVERE, null, ex)
        }


    }
@FXML
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

@FXML
fun ImprimirPdf()
{
    var condicion:String=""
    var listadoJr: impresor.imprimeInforme  = impresor.imprimeInforme()

    listadoJr.imprimefichero(condicion,"clientes.jasper")
}

}