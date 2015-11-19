package dom.paciente.graficotorta;

import org.apache.isis.applib.adapters.DefaultsProvider;
import org.apache.isis.applib.adapters.EncoderDecoder;
import org.apache.isis.applib.adapters.Parser;
import org.apache.isis.applib.adapters.ValueSemanticsProvider;

public class GraficoPacienteSemantica implements
		ValueSemanticsProvider<GraficoPaciente> {

	@Override
	public Parser<GraficoPaciente> getParser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EncoderDecoder<GraficoPaciente> getEncoderDecoder() {
		return new EncoderDecoder<GraficoPaciente>() {

			public String toEncodedString(GraficoPaciente toEncode) {
				return Base64Serializer.toString(toEncode);
			}

			public GraficoPaciente fromEncodedString(String encodedString) {
				return (GraficoPaciente) Base64Serializer
						.fromString(encodedString);
			}
		};

	}

	@Override
	public DefaultsProvider<GraficoPaciente> getDefaultsProvider() {
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