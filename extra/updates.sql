/* actualiza el stock de los lotes en el almacén */
UPDATE ubicacion
SET cantidad =
(
	SELECT 
		IF(
			IFNULL ((SELECT SUM(gea1.elaborado) FROM gp_envasado_agrupacion gea1 WHERE gea.ordenEnvasado = gea1.ordenEnvasado), 0) -
			IFNULL ((SELECT SUM(bl.cantidad) FROM bulto_lotes bl WHERE bl.lote = ge.lote), 0) +
			IFNULL ((SELECT SUM(re.cantidad) FROM registroentrada re WHERE re.lote = ge.lote AND (idTipoRegistro = 'P')), 0) +
			IFNULL ((SELECT SUM(re.cantidad) FROM registroentrada re WHERE re.lote = ge.lote AND (re.idTipoRegistro = 'D')), 0) >= 0
			,
			IFNULL ((SELECT SUM(gea1.elaborado) FROM gp_envasado_agrupacion gea1 WHERE gea.ordenEnvasado = gea1.ordenEnvasado), 0) -
			IFNULL ((SELECT SUM(bl.cantidad) FROM bulto_lotes bl WHERE bl.lote = ge.lote), 0) +
			IFNULL ((SELECT SUM(re.cantidad) FROM registroentrada re WHERE re.lote = ge.lote AND (idTipoRegistro = 'P')), 0) +
			IFNULL ((SELECT SUM(re.cantidad) FROM registroentrada re WHERE re.lote = ge.lote AND (re.idTipoRegistro = 'D')), 0)
			,
			0
		) AS TOTAL_LOTE
	FROM gp_envasado ge
		LEFT JOIN gp_envasado_agrupacion gea ON ge.orden = gea.ordenEnvasado
		LEFT JOIN producto_compuesto pc ON pc.idCompuestoDe = (SELECT ge2.idProducto FROM gp_envasado ge2 WHERE ge2.lote = ge.lote)
	WHERE ge.lote = registro
	LIMIT 1
)
WHERE registro = SUBSTRING(registro, 1, 1) = '0';

/* eans13 fabricados, sin estar en agrupaciones */
UPDATE producto p, (
	SELECT ge.idProducto, SUM(ge.elaborado) AS elaborado
	FROM gp_envasado ge
	INNER JOIN gp_envasado_agrupacion gea ON gea.ordenEnvasado = ge.orden
	WHERE gea.idAgrupacion = -1
	GROUP BY ge.idProducto, idAgrupacion
) envasado
SET p.stock = envasado.elaborado
WHERE p.idProducto = envasado.idProducto;

/* eans13 agrupados utilizados para envasar eans14 */
/*UPDATE producto p, (
	SELECT ge.idProducto, SUM(ge.elaborado) As elaborado
	FROM gp_envasado ge
	INNER JOIN gp_envasado_agrupacion gea ON gea.ordenEnvasado = ge.orden
	WHERE gea.idAgrupacion >= 0
	GROUP BY ge.idProducto, idAgrupacion
) envasado
SET p.stockAgrupado = envasado.elaborado
WHERE p.idProducto = envasado.idProducto;*/
UPDATE producto p, (
	SELECT /*p.idProducto,*/ pc.idCompuestoDe, /*p.stock, pc.cantidad,*/ SUM(p.stock * pc.cantidad) AS cantidad
	FROM producto p
	INNER JOIN producto_compuesto pc ON pc.idProducto = p.idProducto
	GROUP BY pc.idCompuestoDe
	ORDER BY pc.idCompuestoDe
) agrupados
SET p.stockAgrupado = agrupados.cantidad
WHERE p.idProducto = agrupados.idCompuestoDe;

/* eans14 fabricados */
UPDATE producto p, (
	SELECT gea.idAgrupacion, SUM(gea.elaborado) As elaborado
	FROM gp_envasado ge
	INNER JOIN gp_envasado_agrupacion gea ON gea.ordenEnvasado = ge.orden
	WHERE gea.idAgrupacion >= 0
	GROUP BY ge.idProducto, idAgrupacion
) envasado
SET p.stock = envasado.elaborado
WHERE p.idProducto = envasado.idAgrupacion;

/* mercancia vendida que hay que restar */
UPDATE producto p, (
	SELECT ad.idProducto, SUM(ad.cantidadUnitaria) AS cantidad
	FROM albaran_detalle ad
	INNER JOIN albaran a ON a.codigoAlbaran = ad.codigoAlbaran
	WHERE a.habilitado = 'S'
		And a.estado != 'X'
	GROUP BY ad.idProducto
) ventas
SET p.stock = (p.stock - ventas.cantidad)
WHERE p.idProducto = ventas.idProducto;

/* mercancia devuelta que hay que sumar */
UPDATE producto p, (
	SELECT if (gea.idAgrupacion >= 0, gea.idAgrupacion, ge.idProducto) AS idProducto, re.cantidad
	FROM registroentrada re
	INNER JOIN gp_envasado ge ON re.lote = ge.lote
	INNER JOIN gp_envasado_agrupacion gea ON gea.ordenEnvasado = ge.orden
	WHERE re.idTipoRegistro = 'D'
) devoluciones
SET p.stock = (p.stock + devoluciones.cantidad)
WHERE p.idProducto = devoluciones.idProducto;

/* producto venta a sumar */
UPDATE producto p, (
	SELECT re.idProducto, re.cantidad
	FROM registroentrada re
	WHERE re.idTipoRegistro = 'P'
) entradas
SET p.stock = (p.stock + entradas.cantidad)
WHERE p.idProducto = entradas.idProducto;