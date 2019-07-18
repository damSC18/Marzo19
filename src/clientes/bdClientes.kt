package clientes

import java.io.FileInputStream
import java.sql.*

class bdClientes {
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


            val SQL = "SELECT * FROM clientes"

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
    return rs!!
 }
    fun leerAnteriorRegistro():ResultSet
    {
        if(!rs!!.isFirst){
            rs!!.previous()
        }
        return rs!!
    }
    fun trabajoRegistro(SQL:String)
    {
        stmt!!.execute(SQL)


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