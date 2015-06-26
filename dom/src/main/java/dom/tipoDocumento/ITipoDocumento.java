package dom.tipoDocumento;
/**
 * Interface para implementar los distintos tipos de Dni.
 *  
 * @author Adamantium
 * @since 01/06/2015
 * @version 1.0.0
 */
public interface ITipoDocumento {
		TipoDocumentoEnum getCategory();
	
		void setCategory(TipoDocumentoEnum tipoDocumento);
	
	}

