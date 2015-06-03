package dom.Regex;
public final class RegexValidation {
	public static final class ValidaNombres {
		private ValidaNombres() {
		}

		public static final String REFERENCIA = "[a-z,A-Z,0-9,ñ,Ñ, ]+";
		public static final String INICIALES = "[a-z,A-Z,ñ,Ñ]{2}$+";
	}

	public static final class ValidaTel {
		private ValidaTel() {

		}

		public static final String NUMEROTEL = "[+]?[0-9 -]*";

	}

	public static final class ValidaMail {
		private ValidaMail() {

		}

		public static final String EMAIL = "[^@ ]*@{1}[^@ ]*[.]+[^@ ]*";
	}

	public static final class ValidaDoc {
		private ValidaDoc() {

		}

		public static final String DOCUMENTO = "[0-9]+";
	}

	public static final class ValidaMatricula {
		private ValidaMatricula() {

		}

		public static final String MATRICULA = "[a-z,A-Z,0-9,ñ,Ñ, ]+";
	}
}