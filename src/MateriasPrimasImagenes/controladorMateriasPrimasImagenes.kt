package MateriasPrimasImagenes

import MateriasPrimas.objetoMateriasPrimas
import MateriasPrimassImagenes.bdMateriasPrimassImagenes
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger
import java.util.regex.Pattern


class controladorMateriasPrimasImagenes {

    // Campos del interfaz
    @FXML
    private var PanelBusqueda : Pane = Pane()
    @FXML
    var panelImagen:Pane=Pane()
    @FXML
    var idProducto: TextField = TextField()
    @FXML
    var descripcion: TextField = TextField()
    @FXML
    var idFamilia: TextField = TextField()
    @FXML
    var precioCoste: TextField = TextField()
    @FXML
    var ficheroImagen: TextField = TextField()

    // Variables del programa
    var nuevo: Boolean = false
    var posicion: Int = 1
    // Estabelces conexion con el Modelo para acceso
    // a las funciones de Base de Datos(bdClientes.kt)
    var bd: bdMateriasPrimassImagenes = bdMateriasPrimassImagenes()



    // variable utilizada para saber si leemos fichero imagen de disco. La necesitamos en el momento de sobreescribir un registto

    var ficheroimagenLeido:Boolean=false
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
    fun NuevoRegistro() {



        nuevo = true

        idProducto.text = posicion.toString()// En la base de datops en un campo autoincrement. Por lo qu el numero de id no sirve
        descripcion.text = ""
        idFamilia.text = "0"
        precioCoste.text = "0.0"
        descripcion.requestFocus()
    }

    @FXML
    fun GrabarRegistro() {
        try {
        val dato_idMateriaPrima= idProducto.text.toInt()
        val dato_descripcion = descripcion.text
        val dato_familia = idFamilia.text.toInt()
        val dato_precioCoste = precioCoste.text.toFloat()
        // URL del fichero con la imagen a grabar en la BD
        var ficheroAGrabar:String = ficheroImagen.text

        // Creamos un objeto  articulo a partir de un DataClass en el que copiadmos todos los datos necesarios para grabar un nuevo
        // registro utilizando PrepareStatement . En el campo    ficheroAGrabar pasamos la url del fichero con la imagen a grabar
        var articulo=
            objetoMateriasPrimas(dato_idMateriaPrima,dato_descripcion,dato_familia,dato_precioCoste,ficheroAGrabar)

            //SI NUEVO ES TRUE LLAMAMOS A LA FUNCION PARA GRABAR UN NUEVO REGISTRO
            if(nuevo){
                bd.grabaRegistroMP(articulo)

            }
            //SI NUEVO ES false LLAMAMOS A LA FUNCION PARA MODIFICAR UN REGISTRO
            else{
                bd.modificaRegistroMP(articulo,ficheroimagenLeido)

            }
            // AL realizar una consulta de accion Insert o delete conta la base de datos, el cursor se cierra
            // port lo tanto ejecutamos la conexión y nos posicionamos ne el ultimo registro
            bd.conexion()

            UltimoRegistro()
        } catch (ex: Exception) {
            ex.printStackTrace()
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Gestión Metal"
            alert.headerText = "Crud Materias Fabricadas"
            alert.contentText = "Error al grabar artículo"
            alert.showAndWait()
        }
        ////////////////////////////////////////////////////////////
        // Si no tenemos que utiliar sentencia PrepareStatement como en el caso de grabar un campo Image
        // es mas fácil crear las sentencia SQL en el controlador y pasalas al mo delo por medio de
        // bd.trabajoRegistro(SQL)
        /////////////////////////////////////////////////////
       /*
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
            UltimoRegistro()
        }
    */
    }

    @FXML
    fun BorraRegistro() {
        var SQL: String = ""

        SQL = " delete from  MateriaPrimass where id_MateriaPrima=" + idProducto.text
        bd.trabajoRegistro(SQL)

        bd.conexion()

        UltimoRegistro()
    }

    @FXML
    fun PrimerRegistro() {
        var rs: ResultSet = bd.leerPrimerRegistro()
        VisualizaRegistro(rs)
    }

    @FXML
    fun anteriorRegistro() {
        var rs: ResultSet = bd.leerAnteriorRegistro()
        VisualizaRegistro(rs)
    }

    @FXML
    fun SiguienteRegistro() {

        var rs: ResultSet = bd.leerSiguienteRegistro()

        VisualizaRegistro(rs)
    }

    @FXML
    fun UltimoRegistro() {
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
        PrimerRegistro()
        // Obtener lista Campos y Visualizarlos en el comboBox de Panel Buscar
        rellenarListaCamposBuscar()

        // Validación de datos
        // Pasamos al evento el nombre del campo, la longitud máxima permitiva y el numero de campo
        // por medio del tercer parametros decimimos que caja de texto ha desencadenado el evento
          registerListener(descripcion,50,1)
          registerListener(idFamilia,1,2)
          registerListener(precioCoste,8,3)



    }
    @FXML
    //FUNCION PARA SELECCIONAR IMAGENES
    fun SeleccionarImagen()
    {
        // variable utilizada para saber si leemos fichero imagen de disco. La necesitamos en el momento de sobreescribir un registto
        ficheroimagenLeido =true

        //CREAMOS UN FILECHOOSER
        val fileChooser: FileChooser = FileChooser()
        //AÑADIMOS FILTROS A NUESTRO FILECHOOSER PARA INDICAR QUE TIPO DE EXTENSIONES QUEREMOAS ADMITIR
        fileChooser.getExtensionFilters().addAll(
            FileChooser.ExtensionFilter("All Files", "*.*"),
            FileChooser.ExtensionFilter("Text Files", "*.txt"),
            FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
            FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"))

        val selectedFile = fileChooser.showOpenDialog(null)
        //SI LA SELECCION NO ES NULA TOMAMOS LA RUTA ABSOLUTA DEL ELEMENTO ESCOGIDO
        if (selectedFile != null) {
            try {
                ficheroImagen.text = selectedFile.absolutePath
                //////////////////////////////////////////////////////////////////////////
                // Esn  ficheroImagen.text guardamoe la ruta del fichero con la imagen a grabar
                // luego se la pasaremos a operacionesMP para grabar en el campo Imagen de la base de datos
                //  en la fun GrabarRegistro()
                // Creamos un objeto de tipo objetoMateriasPrimas en el cuan tenemos un campo de tipo
                // string para la URL del ficehro

                //////  ///////////////////////////////////////////////  ///////////////////////////////////////////////

                // El Resto del codigo Visualiza la imagen leida en un ImageView y trambien genera un fichero en disco
                //////////// Pero realmente no sirve para el objetivo de grabar imagen en SqlServer

                var urlFichero: String = "file:///" + ficheroImagen.text.replace("\\", "/")


              //  javax.swing.JOptionPane.showMessageDialog(null,urlFichero)

                var imagenMultimedia: ImageView =    ImageView(urlFichero)

                imagenMultimedia.setFitWidth(panelImagen.getWidth())
                imagenMultimedia.setFitHeight(panelImagen.getHeight())
                // imagenMultimedia.setScaleX(0.9)
                //imagenMultimedia.setScaleY(0.9)
                panelImagen.children.add(  imagenMultimedia)

                /////////////////////////////////////
                // PAsar a Base64   --- > https://grokonez.com/kotlin/kotlin-encode-decode-fileimage-base64
                ///////////////////////////////////////////////////////////
             /*   val bytes = File(ficheroImagen.text ).readBytes()
                println(ficheroImagen.text )
                val base64 = Base64.getEncoder().encodeToString(bytes)
                println(base64)

                // A partir de base64 crear un fichero rn disco con la imagen
                var base64Str:String=base64
                val imageByteArray = Base64.getDecoder().decode(base64Str)
                File("C:\\imagenes\\rueda22.png").writeBytes(imageByteArray)*/




            } catch (ex: FileNotFoundException) {
                println(ex.message)
            }
        }
    }


    fun VisualizaRegistro(rs: ResultSet) {
// Campo9s de la tabala MateriaPreimass
 /*
        [id_MateriaPrima]   int
        ,[Descripcion]        String
        ,[id_Familia]         id
        ,[PrecioCoste]        float
        ,[Imagen]             imagen
        */
        ficheroimagenLeido=false
        nuevo = false
        // Obtenemos los datos leidos y los visualizamos sobre als cajas de texto
        idProducto.text= rs!!.getString("id_MateriaPrima")
        descripcion.text = rs!!.getString("Descripcion")
        idFamilia.text = rs!!.getInt("id_Familia").toString()
        precioCoste.text = rs!!.getFloat("PrecioCoste").toString()


        // Obtenemos la imagen leida de la base de datos
        // creamos un ImageView a partir de la imagen(image) obtenida
        // y la añadimos al contenedor panelImagen



        var issss: InputStream   = rs!!.getBinaryStream(5)
        //Creamos  un objeto Image a partir del InputStream

        var foto:Image =Image(issss!!)

        var imagenMultimedia:ImageView=    ImageView(foto)

        imagenMultimedia.setFitWidth(panelImagen.getWidth())
        imagenMultimedia.setFitHeight(panelImagen.getHeight())
        // imagenMultimedia.setScaleX(0.9)
        //imagenMultimedia.setScaleY(0.9)

        panelImagen.children.removeAll(panelImagen.children)
        panelImagen.children.add(  imagenMultimedia)



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
    //FUNCION PARA FINALIZAR  BUSQUEDA
    fun FinalizarBuscar() {
        PanelBusqueda.setVisible(false)
    }


    @FXML
    //FUNCION PARA BUSCAR UN REGISTRO
    fun BuscarRegistro() {
        PanelBusqueda.setVisible(true)

    }
    //FUNCION PARA BUSCAR SIGUIENTE REGISTRO





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
            Logger.getLogger(controladorMateriasPrimasImagenes::class.java!!.getName()).log(Level.SEVERE, null, ex)
        }


    }
@FXML
fun VisualizaRejilla(event: ActionEvent) {

    //Visualiza rejilla tabla clientes
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
            if(nc==2) {
                println(2)
                //p = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}\$")
                p = Pattern.compile("[0-9]?") // Pemite un solo numero de 0 a 9
                val m = p.matcher(cajaTexto.text )
               // b = m.find()
                b = m.matches()
            }
            if( nc==3) {
                 p = Pattern.compile("[0-9]+.[0-9]{0,2}") // permite Todos los digitos que quieras a la izquieda(por lo menos 1)  de .
                                                        // yde cero a dos dígitos a la dereca
              //  p = Pattern.compile("[0-9]*")
                val m = p.matcher(cajaTexto.text )
                b = m.matches()
            }



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
    // Imprime diseño informe tabla clienets
    var condicion:String=""
    var listadoJr: impresor.imprimeInforme  = impresor.imprimeInforme()

    listadoJr.imprimefichero(condicion,"clientes.jasper")
}

    //FUNCION PARA FINALIZAR QUE NOS CARGA DE NUEVO EL MENU INICIAL DE METAL.FXML
@FXML
fun Finalizar(event: ActionEvent) {

        System.exit(0)
    }
}