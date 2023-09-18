import java.io.File;
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

	private static byte[] encodeFileToBytes(String pathFile) {
		FileInputStream fileInputStream;
		ByteArrayOutputStream byteArrayOutputStream = null;
		byte[] data = null;
		String paths = pathFile;
		
		File file = new File(paths);
		byte[] fileBytes = null;
		try {
			fileInputStream = new FileInputStream(file);
			data = new byte[(int) file.length()];
			fileBytes = new byte[(int) file.length()];
		
			byteArrayOutputStream = new ByteArrayOutputStream();
			fileInputStream.read(data);
			byteArrayOutputStream.write(data);
			fileBytes = byteArrayOutputStream.toByteArray();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return data;

	}

	private static String encodeFileToBase64(String path) {
	       String base64File = "";
	       File file = new File(path);
	        try (FileInputStream imageInFile = new FileInputStream(file)) {
	            // Reading a file from file system
	            byte fileData[] = new byte[(int) file.length()];
	            imageInFile.read(fileData);
	            base64File = org.apache.axis.encoding.Base64.encode(fileData);
	        } catch (FileNotFoundException e) {
	            System.out.println("Archivo no encontrado" + e);
	        } catch (IOException ioe) {
	            System.out.println("Excepción al leer el archivo " + ioe);
	        }
	        return base64File;
	}
	



}
