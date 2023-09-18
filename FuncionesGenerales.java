public class FuncionesGenerales {
  
    public static void deleteDirectory(File directory) {
        for (File file: Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                if(file.delete()){
                	System.out.println("Archivo borrado: " + file.getName());
                }
            }
        }
    }
    
	public static void cleanDirectory(String path){
        File directory = new File(path);
		deleteDirectory(directory);
	}





}
