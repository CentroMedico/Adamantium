/*
 Copyright 2015 Adamantium

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
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