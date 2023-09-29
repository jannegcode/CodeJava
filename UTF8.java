public class FuncionesGenerales {

		public static String encodeUTF8(String parametro){
			String item = parametro; 
			byte[] bytes = item.getBytes(StandardCharsets.ISO_8859_1);
			item = new String(bytes, StandardCharsets.UTF_8);
			System.out.println(item);
			return item;
		}
	
}
