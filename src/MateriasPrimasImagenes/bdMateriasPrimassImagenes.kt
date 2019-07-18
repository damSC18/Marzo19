package MateriasPrimassImagenes

import MateriasPrimas.objetoMateriasPrimas
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.ObjectInputStream
import java.sql.*

class bdMateriasPrimassImagenes {
    var connection: Connection? = null
    var stmt: Statement? = null
    var rs: ResultSet?  = null
    var posicion:Int=0
    fun conexion()
    {
        var UrlficheroConexion = System.getProperty("user.dir") + "\\conexion.txt"
        val parametrosConexion = FileInputStream(UrlficheroConexion).bufferedReader().use { it.readText() }

        var datosconexion=parametrosConexion.split(",")

        // val connectionString = "jdbc:sqlserver://90d18277a66e\\sqlexpress;database=metal;user=sa;password=sa_sa_2018@2018sa";
        // val connectionString = "jdbc:sqlserver://localhost:1433;database=metal;user=sa;password=dam2";
        val connectionString = "jdbc:sqlserver://"+datosconexion[0]+":"+datosconexion[1]+";database=Metal;user="+datosconexion[2]+";password="+datosconexion[3];
        println(connectionString)
        try {

            //  Class.forName("org.sqlite.JDBC")

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
            connection = DriverManager.getConnection(connectionString)
            stmt = connection!!.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);


            val SQL = "SELECT * FROM  MateriaPrimass"

            rs = stmt!!.executeQuery(SQL)
///////////////////////////////////////////////////////////////////
            /* if(rs!!.isBeforeFirst){
                rs!!.first()
            System.out.println(rs!!.getString(2))
             } */



        } catch (e: Exception) {
            javax.swing.JOptionPane.showMessageDialog(null,"Error en conexión a Base de Datos -- "+e.message)
            System.exit(0)
        }
    }
    fun leerPrimerRegistro():ResultSet
    {
      rs!!.first()
      return rs!!
    }
    fun leerUtimoRegistro():ResultSet
    {
     rs!!.last()
     return rs!!
    }
 fun leerSiguienteRegistro():ResultSet
 {

     if(!rs!!.isLast)
     {
      rs!!.next()
     }
     else{rs!!.last()}
    return rs!!
 }
    fun leerAnteriorRegistro():ResultSet
    {
        if(!rs!!.isFirst){
            rs!!.previous()
        }
        else{rs!!.first()}
        return rs!!
    }
    fun trabajoRegistro(SQL:String)
    {
        stmt!!.execute(SQL)


    }

    //FUNCION PARA INSERTAR UNA NUEVA MATERIA PRIMA
    fun grabaRegistroMP(articulo: objetoMateriasPrimas) {
          try {
            //     stmt = connection!!.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
            //EJECUTAMOS LA QUERY Y EN ESTE CASO TENEMOS UN BOOLEANO CR QUE TOMARA EL VALOR TRUE O FALSE DEPENDIENDO DE SI LA CONSULTA HA SIDO EXITOSA
            //   var cr: Boolean = stmt!!.execute(SQL)

            //LLAMAMOS A UPDATEDATA() PARA REFRESCAR LOS CAMBIOS
            //    UpdateData()



            /// SENTENCIA PRQEPARADA para grabar imagen en lugar de INSERT
            val sql = "INSERT INTO MateriaPrimass (Descripcion, id_Familia, PrecioCoste, Imagen) VALUES (?,?,?,?)"
            //Creamos una cadena para después prepararla
            val stmt = connection!!.prepareStatement(sql)
          //  javax.swing.JOptionPane.showMessageDialog(null,"n:\\imagenes\\animal5.jpg")
           // javax.swing.JOptionPane.showMessageDialog(null,articulo.imagen)
            val image= File(articulo.imagen)
            //val image = File("n:\\imagenes\\animal5.jpg")
            //ruta puede ser: "/home/cmop/Desktop/1.jpg"
            val fis: InputStream = FileInputStream(image) as InputStream

            println(fis.toString()+"       "+ image.length().toInt())
            //Lo convertimos en un Stream
            stmt.setString(1,articulo.descripcion)
            stmt.setInt(2,articulo.familia)
            stmt.setFloat(3,articulo.precioCoste)
            stmt.setBinaryStream(4, fis, image.length().toInt())
            //Asignamos el Stream al Statement
            var grabado:Boolean=stmt.execute()
            println("Grabado :"+ grabado)
//////////////////////////////////////////



        } catch (e: Exception) {
            e.printStackTrace()
        }




    }

    //FUNCION PARA MODIFICAR UNA  MATERIA PRIMA
    fun modificaRegistroMP(articulo:  objetoMateriasPrimas,  ficheroimagenLeido:Boolean){
        try {
            //     stmt = connection!!.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
            //EJECUTAMOS LA QUERY Y EN ESTE CASO TENEMOS UN BOOLEANO CR QUE TOMARA EL VALOR TRUE O FALSE DEPENDIENDO DE SI LA CONSULTA HA SIDO EXITOSA
            //   var cr: Boolean = stmt!!.execute(SQL)

            //LLAMAMOS A UPDATEDATA() PARA REFRESCAR LOS CAMBIOS
            //    UpdateData()

            if(ficheroimagenLeido){

            /// SENTENCIA PRQEPARADA para grabar imagen en lugar de INSERT
            val sql = "update MateriaPrimass set Descripcion=?, id_Familia=?, PrecioCoste=?, Imagen=? where id_MateriaPrima ="+ articulo.id

            val stmt = connection!!.prepareStatement(sql)

            stmt.setString(1,articulo.descripcion)
            stmt.setInt(2,articulo.familia)
            stmt.setFloat(3,articulo.precioCoste)

            val image:File= File(articulo.imagen)


            val fis: InputStream = FileInputStream(image) as InputStream

            //Lo convertimos en un Stream

            stmt.setBinaryStream(4, fis, image.length().toInt())

           var grabado:Boolean=stmt.execute()
    }
   else{
                val sql = "update MateriaPrimass set Descripcion=?, id_Familia=?, PrecioCoste=?  where id_MateriaPrima ="+ articulo.id
                //Creamos una cadena para después prepararla
                val stmt = connection!!.prepareStatement(sql)
                // javax.swing.JOptionPane.showMessageDialog(null,"n:\\imagenes\\animal5.jpg")
                //  javax.swing.JOptionPane.showMessageDialog(null,articulo.imagen)
                stmt.setString(1,articulo.descripcion)
                stmt.setInt(2,articulo.familia)
                stmt.setFloat(3,articulo.precioCoste)


                var grabado:Boolean=stmt.execute()

      }



//////////////////////////////////////////


        } catch (e: Exception) {
            e.printStackTrace()
        }



    }

    fun leerCamposTabla(): ResultSet {

        return rs!!
    }
    fun fleerSiguienteBuuscar(datoAbuscar: String, snc: String):ResultSet {

        try {
            var en = false
             if (rs!!.isAfterLast()) {
                rs!!.last()
            }
            if (rs!!.isBeforeFirst()) {
                rs!!.first()
            }
            posicion = rs!!.getRow()
//javax.swing.JOptionPane.showMessageDialog(null,posicion)
            while (rs!!.next()) {
                if (rs!!.getObject(snc) != null) {

                 //    javax.swing.JOptionPane.showMessageDialog(null,rs!!.getObject(snc).toString())
                    var cadena: String = rs!!.getObject(snc).toString().trim() + " "

                    var subcadena = datoAbuscar.trim().toString();


                    val enc:Int = cadena.indexOf(subcadena)

                    if (enc != -1) {
                        en = true
                        break
                    }
                }
            }
            if (en == false) {

                rs!!.absolute(posicion)
                //   return articuloLeido
            }
        } catch (ex: Exception) {
        }
        return rs!!
    }
    fun fleerAnteriorBuuscar(datoAbuscar: String, snc: String): ResultSet {

        try {
            var en = false
            if (rs!!.isAfterLast()) {
                rs!!.last()
            }
            if (rs!!.isBeforeFirst()) {
                rs!!.first()
            }
            posicion = rs!!.getRow()

            while (rs!!.previous()) {
                if (rs!!.getObject(snc) != null) {
                    var cadena: String = rs!!.getObject(snc).toString().trim() + " "

                    var subcadena = datoAbuscar.trim().toString();


                    val enc = cadena.indexOf(subcadena)

                    if (enc != -1) {

                        en = true
                        break
                    }
                }
            }
            if (en == false) {

                rs!!.absolute(posicion)
                //   return articuloLeido
            }
        } catch (ex: Exception) {
        }
        return rs!!
    }
}