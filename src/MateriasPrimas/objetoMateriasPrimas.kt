package MateriasPrimas

import java.io.InputStream
import java.sql.Blob


data class objetoMateriasPrimas(var id: Int, var descripcion: String, var familia: Int, var precioCoste:Float, var imagen: String)
