

public class FuncionesGenerales {
  
	
	public static String cambiaFormatoYYYYMMDD(String fecha){
		String s= null;
		String [] arr = fecha.split("-");
		s= arr[0]+arr[1]+arr[2];
		return s;
	}
	
}
