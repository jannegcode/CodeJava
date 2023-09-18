import java.io.File;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.DatatypeConverter;

import json.org.JSONArray;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.pdf.codec.Base64;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

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
	
	// Extraer archivos ZIP
	public static boolean unzip(String ZipFilePath, String DestFilePath) throws IOException {
		
		boolean extract = false;
        File Destination_Directory = new File(DestFilePath);
        if (!Destination_Directory.exists()) {
        	Destination_Directory.mkdir();
        }
        ZipInputStream Zip_Input_Stream = new ZipInputStream(new FileInputStream(ZipFilePath));
        ZipEntry Zip_Entry = Zip_Input_Stream.getNextEntry();

        while (Zip_Entry != null) {
            String File_Path = DestFilePath + File.separator + Zip_Entry.getName();
            if (!Zip_Entry.isDirectory()) {
            	extract = true;
                extractFile(Zip_Input_Stream, File_Path);
            } else {

                File directory = new File(File_Path);
                directory.mkdirs();
            }
            Zip_Input_Stream.closeEntry();
            Zip_Entry = Zip_Input_Stream.getNextEntry();
        }
        Zip_Input_Stream.close();
        
        return extract;
    }

    private static void extractFile(ZipInputStream Zip_Input_Stream, String File_Path) throws IOException {
        BufferedOutputStream Buffered_Output_Stream = new BufferedOutputStream(new FileOutputStream(File_Path));
        byte[] Bytes = new byte[BUFFER_SIZE];
        int Read_Byte = 0;
        while ((Read_Byte = Zip_Input_Stream.read(Bytes)) != -1) {
        	Buffered_Output_Stream.write(Bytes, 0, Read_Byte);
        }
        Buffered_Output_Stream.close();
    }

	
}
