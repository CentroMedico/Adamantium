package dom.turnopaciente.grafico;

import org.apache.isis.applib.adapters.DefaultsProvider;
import org.apache.isis.applib.adapters.EncoderDecoder;
import org.apache.isis.applib.adapters.Parser;
import org.apache.isis.applib.adapters.ValueSemanticsProvider;

public class GraficoTurnoPacienteSemantica implements
		ValueSemanticsProvider<GraficoTurno> {

	@Override
	public Parser<GraficoTurno> getParser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EncoderDecoder<GraficoTurno> getEncoderDecoder() {
		return new EncoderDecoder<GraficoTurno>() {

			public String toEncodedString(GraficoTurno toEncode) {
				return Base64Serializer.toString(toEncode);
			}

			public GraficoTurno fromEncodedString(String encodedString) {
				return (GraficoTurno) Base64Serializer
						.fromString(encodedString);
			}
		};

	}

	@Override
	public DefaultsProvider<GraficoTurno> getDefaultsProvider() {
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