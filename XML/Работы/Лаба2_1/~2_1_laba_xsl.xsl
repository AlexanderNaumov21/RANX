<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
 <xsl:apply-templates/>
</xsl:template>
<xsl:template match="INVENTORY">
 <h1>Телефоны, отсортированные по цене:</h1>
 <table border="all">
 <tr>
                                <th>Название</th>
                                <th>Цена</th>
                                <th>ОС</th>
                                <th>Бренд</th>
                                <th>Память</th>
                                <th>Диагональ</th>
                                <th>Передняя камера, мп</th>
                                <th>Фронтальная камера, мп</th>
 </tr>
 <xsl:apply-templates select="SMARTPHONE">
 <xsl:sort select="PRICE" data-type="number" order="descending"/>
 </xsl:apply-templates>
 </table>
</xsl:template>
<xsl:template  match="USER">
  <tr>
   <td><xsl:value-of select="TITLE"/></td>
    <td><xsl:value-of select="PRICE"/></td>
    <td><xsl:value-of select="OS"/></td>
    <td><xsl:value-of select="BRAND"/></td>
    <td><xsl:value-of select="MEMORY"/></td>
    <td><xsl:value-of select="DIAGONAL"/></td>
    <td><xsl:value-of select="FRONT_CAMERA"/></td>
    <td><xsl:value-of select="REAR_CAMERA"/></td>
  </tr>
 </xsl:template>
</xsl:stylesheet>



