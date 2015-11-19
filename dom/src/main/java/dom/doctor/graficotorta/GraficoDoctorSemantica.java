package dom.doctor.graficotorta;

import org.apache.isis.applib.adapters.DefaultsProvider;
import org.apache.isis.applib.adapters.EncoderDecoder;
import org.apache.isis.applib.adapters.Parser;
import org.apache.isis.applib.adapters.ValueSemanticsProvider;

public class GraficoDoctorSemantica implements
		ValueSemanticsProvider<GraficoDoctor> {

	@Override
	public Parser<GraficoDoctor> getParser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EncoderDecoder<GraficoDoctor> getEncoderDecoder() {
		return new EncoderDecoder<GraficoDoctor>() {

			public String toEncodedString(GraficoDoctor toEncode) {
				return Base64Serializer.toString(toEncode);
			}

			public GraficoDoctor fromEncodedString(String encodedString) {
				return (GraficoDoctor) Base64Serializer
						.fromString(encodedString);
			}
		};

	}

	@Override
	public DefaultsProvider<GraficoDoctor> getDefaultsProvider() {
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