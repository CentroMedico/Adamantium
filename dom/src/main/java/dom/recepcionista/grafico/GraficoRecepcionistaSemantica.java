package dom.recepcionista.grafico;

import org.apache.isis.applib.adapters.DefaultsProvider;
import org.apache.isis.applib.adapters.EncoderDecoder;
import org.apache.isis.applib.adapters.Parser;
import org.apache.isis.applib.adapters.ValueSemanticsProvider;

public class GraficoRecepcionistaSemantica implements
		ValueSemanticsProvider<GraficoRecepcionista> {

	@Override
	public Parser<GraficoRecepcionista> getParser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EncoderDecoder<GraficoRecepcionista> getEncoderDecoder() {
		return new EncoderDecoder<GraficoRecepcionista>() {

			public String toEncodedString(GraficoRecepcionista toEncode) {
				return Base64Serializer.toString(toEncode);
			}

			public GraficoRecepcionista fromEncodedString(String encodedString) {
				return (GraficoRecepcionista) Base64Serializer
						.fromString(encodedString);
			}
		};

	}

	@Override
	public DefaultsProvider<GraficoRecepcionista> getDefaultsProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isImmutable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEqualByContent() {
		// TODO Auto-generated method stub
		return true;
	}

}