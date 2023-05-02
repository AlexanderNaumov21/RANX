<?xml version = "1.0" encoding = "utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<xsl:apply-templates/>
</xsl:template>
<xsl:template match="tovars">
              <h1><xsl:value-of select="opis"</h1>
                                <table border="all">
                                <tr><th>Название</th><th>Цена</th></tr>
              <xsl:apply-templates select="tovar"/>
              </table>
</xsl:template>

<xsl:template  match="tovar">
 <tr>
  <td><xsl:value-of  select="naim"/></td>
  <td align="right"><xsl:value-of  select="price"/></td>
 </tr>
</xsl:template>


</xsl:stylesheet>

