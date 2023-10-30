import java.util.UUID;

public class UUID {
  
  public static void main(String[] args) {
  
  }
  
  public static String obtener(){
      UUID uuid = UUID.randomUUID();
      String strUUID = uuid.toString().toUpperCase();
      return strUUID;
  }


}
