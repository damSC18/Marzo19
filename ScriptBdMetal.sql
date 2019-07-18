USE [Metal]
GO
/****** Object:  Table [dbo].[ArticulosFabricados]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ArticulosFabricados](
	[id_ArticuloFabricado] [int] IDENTITY(1,1) NOT NULL,
	[Descripcion] [varchar](50) NULL,
	[id_Familia] [int] NULL,
	[PrecioCoste] [float] NULL,
	[PrecioVenta] [float] NULL,
	[Stock] [int] NULL,
	[StockMinimo] [int] NULL,
	[Imagen] [varchar](max) NULL,
 CONSTRAINT [PK_ArticulosFabricados] PRIMARY KEY CLUSTERED 
(
	[id_ArticuloFabricado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[clientes]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[clientes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](50) NULL,
	[Direccion] [varchar](60) NULL,
	[Poblacion] [varchar](50) NULL,
	[Provincia] [varchar](30) NULL,
	[CodigoPostal] [varchar](5) NULL,
	[CifNif] [varchar](10) NULL,
	[Telefono1] [varchar](10) NULL,
	[Telefono2] [varchar](10) NULL,
	[Email] [varchar](50) NULL,
	[Web] [varchar](50) NULL,
	[PresonaContacto] [varchar](60) NULL,
	[SectorComercial] [varchar](80) NULL,
 CONSTRAINT [PK_clientes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[copiaArticulosFabricados]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[copiaArticulosFabricados](
	[id_ArticuloFabricado] [varchar](50) NULL,
	[Descripcion] [varchar](50) NULL,
	[id_Familia] [varchar](50) NULL,
	[PrecioCoste] [varchar](50) NULL,
	[PrecioVenta] [varchar](50) NULL,
	[Stock] [varchar](50) NULL,
	[StockMinimo] [varchar](50) NULL,
	[Imagen] [varchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DatosPorIdioma]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DatosPorIdioma](
	[id_Idioma] [int] NOT NULL,
	[id_ArticuloFabricado] [int] NOT NULL,
	[DatosTecnicos] [text] NULL,
	[id_Multimedia_DTecnicos] [int] NULL,
	[DatosMantenimiento] [text] NULL,
	[id_Multimedia_DMantenimiento] [int] NULL,
	[DatosMontaje] [ntext] NULL,
	[id_Multimedia_DMontaje] [int] NULL,
 CONSTRAINT [PK_DatosPorIdioma] PRIMARY KEY CLUSTERED 
(
	[id_Idioma] ASC,
	[id_ArticuloFabricado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EscandallosArticulosFabricados]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EscandallosArticulosFabricados](
	[id_Escandallo] [int] IDENTITY(1,1) NOT NULL,
	[id_ArticuloFabricado] [int] NOT NULL,
	[id_MateriaPrima] [int] NOT NULL,
	[cantidad] [int] NOT NULL,
 CONSTRAINT [PK_EscandallosArticulosFabricados] PRIMARY KEY CLUSTERED 
(
	[id_Escandallo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Idiomas]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Idiomas](
	[id_Idioma] [int] IDENTITY(1,1) NOT NULL,
	[Idioma] [varchar](50) NULL,
 CONSTRAINT [PK_Idiomas] PRIMARY KEY CLUSTERED 
(
	[id_Idioma] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LineasPresupuestos]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LineasPresupuestos](
	[id_LineaPresupuesto] [int] IDENTITY(1,1) NOT NULL,
	[id_Presupuesto] [int] NULL,
	[DescripcionLinea] [varchar](50) NULL,
	[id_Articulo] [int] NULL,
	[Precio] [float] NULL,
	[Cantidad] [int] NULL,
	[DatosTecnicos] [text] NULL,
	[DatosMantenimiento] [text] NULL,
	[DatosMontaje] [text] NULL,
	[Estado] [varchar](10) NULL,
 CONSTRAINT [PK_LineasPresupuestos] PRIMARY KEY CLUSTERED 
(
	[id_LineaPresupuesto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MateriaPrimas]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MateriaPrimas](
	[id_MateriaPrima] [int] IDENTITY(1,1) NOT NULL,
	[Descripcion] [nchar](50) NULL,
	[id_Familia] [int] NULL,
	[PrecioCoste] [float] NULL,
	[Imagen] [nvarchar](max) NULL,
 CONSTRAINT [PK_MateriaPrima] PRIMARY KEY CLUSTERED 
(
	[id_MateriaPrima] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MateriaPrimass]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MateriaPrimass](
	[id_MateriaPrima] [int] IDENTITY(1,1) NOT NULL,
	[Descripcion] [varchar](100) NULL,
	[id_Familia] [int] NULL,
	[PrecioCoste] [float] NULL,
	[Imagen] [image] NULL,
 CONSTRAINT [PK_MateriaPrimass] PRIMARY KEY CLUSTERED 
(
	[id_MateriaPrima] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MateriasPrimas]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MateriasPrimas](
	[id_MateriaPrima] [int] NOT NULL,
	[Descripcion] [nvarchar](50) NOT NULL,
	[id_Familia] [int] NULL,
	[PrecioCoste] [float] NULL,
	[Imagen] [nvarchar](max) NULL,
 CONSTRAINT [PK_MateriaPrimas] PRIMARY KEY CLUSTERED 
(
	[id_MateriaPrima] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[multimedia]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[multimedia](
	[id_multimedia] [int] IDENTITY(1,1) NOT NULL,
	[descripcion] [varchar](60) NOT NULL,
	[tipo] [int] NOT NULL,
	[url] [varchar](120) NOT NULL,
 CONSTRAINT [PK_multimedia] PRIMARY KEY CLUSTERED 
(
	[id_multimedia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Presupuestos]    Script Date: 01/05/2019 20:40:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Presupuestos](
	[id_Presupuesto] [int] IDENTITY(1,1) NOT NULL,
	[id_Cliente] [int] NOT NULL,
	[Fecha] [date] NOT NULL,
	[Descripcion] [varchar](max) NULL,
	[Estado] [varchar](10) NULL,
 CONSTRAINT [PK_Presupuestos] PRIMARY KEY CLUSTERED 
(
	[id_Presupuesto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ArticulosFabricados] ON 
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (1, N'aaaaaaaaaaa', 1, 1, 2, 3, 4, N'J:\GestionMetal\Imagenes\animal2.jpg')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (2, N'Escavadora', 5, 247.34, 247.34, 0, 0, N'J:\GestionMetal\Imagenes\animal2.jpg')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (1002, N'Tornillo', 1, 2, 2, 2, 2, N'J:\GestionMetal\Imagenes\tornillo.jpg')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (1003, N'Tornillo2', 3, 3, 3, 3, 3, N'J:\GestionMetal\Imagenes\tornillo2.jpg')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (1004, N'Tornillo2', 3, 3, 3, 3, 3, N'J:\GestionMetal\Imagenes\tornillo2.jpg')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (1005, N'Taladro', 4, 4, 4, 4, 4, N'J:\GestionMetal\Imagenes\taladro.jpg')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (1006, N'Martillo', 5, 5, 5, 5, 5, N'j:\GestionMetal\Imagenes\martillo.JPG')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (1007, N'Rodamiento', 7, 7, 7, 7, 7, N'J:\GestionMetal\Imagenes\rodamiento.jpg')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (2002, N'Pepinillo', 6, 243, 243, 0, 0, N'Imagenes\icono.jpg')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (2003, N'Arandela', 8, 330, 330, 0, 0, N'Imagenes\icono.jpg')
GO
INSERT [dbo].[ArticulosFabricados] ([id_ArticuloFabricado], [Descripcion], [id_Familia], [PrecioCoste], [PrecioVenta], [Stock], [StockMinimo], [Imagen]) VALUES (3002, N'Escalera', 4, 56517.188, 56517.188, 0, 0, N'J:\GestionMetal\Imagenes\LijadoraIndustrial.jpg')
GO
SET IDENTITY_INSERT [dbo].[ArticulosFabricados] OFF
GO
SET IDENTITY_INSERT [dbo].[clientes] ON 
GO
INSERT [dbo].[clientes] ([id], [Nombre], [Direccion], [Poblacion], [Provincia], [CodigoPostal], [CifNif], [Telefono1], [Telefono2], [Email], [Web], [PresonaContacto], [SectorComercial]) VALUES (10, N'PericoPalotes', N'C/ Las amapolas', N'Aranda de Duero', N'Burgos', N'09400', N'12123456G', N'343', N'650', N'direccion@empres.com', N'www', N'Otro wew fffffffffffffff', N'Industrial')
GO
INSERT [dbo].[clientes] ([id], [Nombre], [Direccion], [Poblacion], [Provincia], [CodigoPostal], [CifNif], [Telefono1], [Telefono2], [Email], [Web], [PresonaContacto], [SectorComercial]) VALUES (11, N'Epifanio Peludo', N'C/ de Allá', N'Aranda de Duero', N'Burgos', N'09400', N'33 33', N'2334', N'4353', N'345', N'www', N'yo', N'Madera')
GO
INSERT [dbo].[clientes] ([id], [Nombre], [Direccion], [Poblacion], [Provincia], [CodigoPostal], [CifNif], [Telefono1], [Telefono2], [Email], [Web], [PresonaContacto], [SectorComercial]) VALUES (12, N'Pepe lopez', N'Amapolas 2', N'Aranda de Duero', N'Burgos', N'09400', N'4', N'3243245', N'345', N'345', N'www', N'tu', N'Construcción')
GO
INSERT [dbo].[clientes] ([id], [Nombre], [Direccion], [Poblacion], [Provincia], [CodigoPostal], [CifNif], [Telefono1], [Telefono2], [Email], [Web], [PresonaContacto], [SectorComercial]) VALUES (13, N'Andres Estebanez', N'Corelacasa 13', N'Aranda de Duero', N'Burgos', N'09400', N'5', N'23423', N'345', N'345', N'www', N'el', N'Naval')
GO
INSERT [dbo].[clientes] ([id], [Nombre], [Direccion], [Poblacion], [Provincia], [CodigoPostal], [CifNif], [Telefono1], [Telefono2], [Email], [Web], [PresonaContacto], [SectorComercial]) VALUES (1002, N'El otro', N'esquinita 11', N'Aranda de Duero', N'Burgos', N'09400', N'6', N'123', N'345', N'34535', N'www', N'nos', N'Limpieza')
GO
SET IDENTITY_INSERT [dbo].[clientes] OFF
GO
INSERT [dbo].[DatosPorIdioma] ([id_Idioma], [id_ArticuloFabricado], [DatosTecnicos], [id_Multimedia_DTecnicos], [DatosMantenimiento], [id_Multimedia_DMantenimiento], [DatosMontaje], [id_Multimedia_DMontaje]) VALUES (0, 2, N'ssssssssssss', 1, N'ssssssssssss', 1, N'fffffffffffffffffffffffff', 3)
GO
INSERT [dbo].[DatosPorIdioma] ([id_Idioma], [id_ArticuloFabricado], [DatosTecnicos], [id_Multimedia_DTecnicos], [DatosMantenimiento], [id_Multimedia_DMantenimiento], [DatosMontaje], [id_Multimedia_DMontaje]) VALUES (1, 2, N'em frances', 1, N'em frances', 1, N'em frances', 3)
GO
INSERT [dbo].[DatosPorIdioma] ([id_Idioma], [id_ArticuloFabricado], [DatosTecnicos], [id_Multimedia_DTecnicos], [DatosMantenimiento], [id_Multimedia_DMantenimiento], [DatosMontaje], [id_Multimedia_DMontaje]) VALUES (2, 1, N'ddddddddddddd', 1, N'fffffffffffffff', 2, N'rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr', 3)
GO
INSERT [dbo].[DatosPorIdioma] ([id_Idioma], [id_ArticuloFabricado], [DatosTecnicos], [id_Multimedia_DTecnicos], [DatosMantenimiento], [id_Multimedia_DMantenimiento], [DatosMontaje], [id_Multimedia_DMontaje]) VALUES (2, 2, N'ddddddddddddddddddd', 1, N'fffffffffffffff', 2, N'nnnnnnnnnnnnnnnnnnnnnn', 3)
GO
INSERT [dbo].[DatosPorIdioma] ([id_Idioma], [id_ArticuloFabricado], [DatosTecnicos], [id_Multimedia_DTecnicos], [DatosMantenimiento], [id_Multimedia_DMantenimiento], [DatosMontaje], [id_Multimedia_DMontaje]) VALUES (3, 2, N'Taladro', 1, N'Taladro', 2, N'Taladro', 3)
GO
INSERT [dbo].[DatosPorIdioma] ([id_Idioma], [id_ArticuloFabricado], [DatosTecnicos], [id_Multimedia_DTecnicos], [DatosMantenimiento], [id_Multimedia_DMantenimiento], [DatosMontaje], [id_Multimedia_DMontaje]) VALUES (3, 2002, N'PortuguesnTecnocps', 1, N'Portuguesn maaaaaaaaaaaaaaaaaatenim', 1, N'Portuguesry moooooooooooooooooooooootaje', 3)
GO
SET IDENTITY_INSERT [dbo].[EscandallosArticulosFabricados] ON 
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (1, 2, 1, 1)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (2, 2, 2, 2)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (1002, 2002, 1, 1)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (1003, 2002, 2, 2)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (1004, 2002, 4, 3)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (1005, 2003, 2, 33)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (1006, 2003, 3, 33)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (2002, 3002, 82, 2)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (2003, 3002, 83, 4)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (2004, 3002, 1, 100)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (2005, 3002, 21, 200)
GO
INSERT [dbo].[EscandallosArticulosFabricados] ([id_Escandallo], [id_ArticuloFabricado], [id_MateriaPrima], [cantidad]) VALUES (2006, 3002, 12, 457)
GO
SET IDENTITY_INSERT [dbo].[EscandallosArticulosFabricados] OFF
GO
SET IDENTITY_INSERT [dbo].[Idiomas] ON 
GO
INSERT [dbo].[Idiomas] ([id_Idioma], [Idioma]) VALUES (1, N'Español')
GO
INSERT [dbo].[Idiomas] ([id_Idioma], [Idioma]) VALUES (2, N'Frances')
GO
INSERT [dbo].[Idiomas] ([id_Idioma], [Idioma]) VALUES (3, N'Aleman')
GO
INSERT [dbo].[Idiomas] ([id_Idioma], [Idioma]) VALUES (4, N'Portugues')
GO
SET IDENTITY_INSERT [dbo].[Idiomas] OFF
GO
SET IDENTITY_INSERT [dbo].[LineasPresupuestos] ON 
GO
INSERT [dbo].[LineasPresupuestos] ([id_LineaPresupuesto], [id_Presupuesto], [DescripcionLinea], [id_Articulo], [Precio], [Cantidad], [DatosTecnicos], [DatosMantenimiento], [DatosMontaje], [Estado]) VALUES (2084, 1022, N'Enganche', 1005, 4, 1, N'', N'', N'', N'Taller')
GO
INSERT [dbo].[LineasPresupuestos] ([id_LineaPresupuesto], [id_Presupuesto], [DescripcionLinea], [id_Articulo], [Precio], [Cantidad], [DatosTecnicos], [DatosMantenimiento], [DatosMontaje], [Estado]) VALUES (2085, 1022, N'Cajon', 2, 247.34, 1, N'fffffffffffffffffffffffff', N'ssssssssssss', N'ssssssssssss', N'Fabricado')
GO
INSERT [dbo].[LineasPresupuestos] ([id_LineaPresupuesto], [id_Presupuesto], [DescripcionLinea], [id_Articulo], [Precio], [Cantidad], [DatosTecnicos], [DatosMantenimiento], [DatosMontaje], [Estado]) VALUES (2086, 1022, N'Cajon', 1002, 2, 1, N'', N'', N'', N'Diseño')
GO
INSERT [dbo].[LineasPresupuestos] ([id_LineaPresupuesto], [id_Presupuesto], [DescripcionLinea], [id_Articulo], [Precio], [Cantidad], [DatosTecnicos], [DatosMantenimiento], [DatosMontaje], [Estado]) VALUES (3011, 2011, N'Primera', 1002, 2, 1, N'', N'', N'', N'Fabricado')
GO
SET IDENTITY_INSERT [dbo].[LineasPresupuestos] OFF
GO
SET IDENTITY_INSERT [dbo].[MateriaPrimas] ON 
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (1, N'Tornillo 10 X25                                   ', 1, 12323, N'j:\GestionMetal\Imagenes\tornillo.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (2, N'Tornillo cabeza 10 Y25                            ', 1, 2323, N'j:\GestionMetal\Imagenes\tornillo2.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (3, N'Arandela 12                                       ', 1, 0.45, N'j:\GestionMetal\Imagenes\arandela.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (4, N'Rodamiento Trasero EjeDereco                      ', 2, 0, N'j:\GestionMetal\Imagenes\rodamiento.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (5, N'Tornillo del 10                                   ', 1, 34, N'j:\GestionMetal\Imagenes\TornilloSimple.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (6, N'Rodamiento Doble V                                ', 2, 34, N'j:\GestionMetal\Imagenes\RDobleV.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (7, N'Rodamiento XL_U_L                                 ', 2, 34, N'j:\GestionMetal\Imagenes\rX_Y_L.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (8, N'Rosca Fina x12_24                                 ', 1, 34, N'j:\GestionMetal\Imagenes\tornilloFino.png')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (9, N'Electrodos soldadura Portatil                     ', 3, 343, N'j:\GestionMetal\Imagenes\electrodos.png')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (10, N'Electrodos soldadura Cordón Gruesol               ', 3, 0, N'j:\GestionMetal\Imagenes\electrodoGrueso.png')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (11, N'Sellador Silicona                                 ', 3, 0, N'j:\GestionMetal\Imagenes\SelladorSilicona.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (12, N'Electrodos Silicona de pistola                    ', 3, 0, N'j:\GestionMetal\Imagenes\siliconaPistola.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (13, N'Taladro                                           ', 4, 0, N'j:\GestionMetal\Imagenes\taladro.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (14, N'Martillo                                          ', 4, 0, N'j:\GestionMetal\Imagenes\martillo.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (15, N'xxxxxxxxxxxxxxxx                                  ', 1, 1, N'C:\Users\izquierda\Pictures\aviso.png')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (16, N'Maquina Soldadura Precision                       ', 5, 4556, N'j:\GestionMetal\Imagenes\soldaduraPrecision.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (17, N'Lijadora Industrial                               ', 5, 34545, N'j:\GestionMetal\Imagenes\LijadoraIndustrial.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (18, N'Chapa de acero modelo Abrahan                     ', 8, 345, N'j:\GestionMetal\Imagenes\chapaAceroAbrahan.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (19, N'Chapa Estandar Pulida                             ', 8, 4556, N'j:\GestionMetal\Imagenes\chapaLisa.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (20, N'Angulo Acero X1                                   ', 8, 34, N'j:\GestionMetal\Imagenes\AnguloAceroX1.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (21, N'Neumaticos Comb                                   ', 7, 545, N'j:\GestionMetal\Imagenes\ComboNeumaticos.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (22, N'Neumaticos Moto                                   ', 7, 0, N'j:\GestionMetal\Imagenes\neumaticosMoto.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (23, N'Neumatico Tractor Agricola                        ', 7, 4545, N'j:\GestionMetal\Imagenes\tractor.jpg')
GO
INSERT [dbo].[MateriaPrimas] ([id_MateriaPrima], [Descripcion], [id_Familia], [PrecioCoste], [Imagen]) VALUES (1001, N'dddddddddddddd                                    ', 1, 1.1000000238418579, N'񏳿က䙊䙉Āā䠀䠀