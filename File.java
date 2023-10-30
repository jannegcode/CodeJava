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

public class File {

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

    // Archivo convertirlo a ZIP
    public static void zipFiles(String sourceFile, String dest) throws IOException {

        // ZIP de destino, ruta de origen y nombre del zip
        FileOutputStream fos = new FileOutputStream(dest);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        // Buscamos el archivo fisico
        File fileToZip = new File(sourceFile);
        //Convertimos el archivo a un InputStream y lo agregamos a una entrada del ZIP
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());

        // Agregamos la entrada del zip con el archivo al archivo de salida.
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        
        // Cerramos los recursos.
        zipOut.closeEntry();
        zipOut.close();
        fis.close();
        fos.close();
    }

// Este método es para poder converir un archivo que traes de JSP a bytes y poderlo adjuntar en un correo. 
	public static void FiletoByteCorreo() throws IOException {
		FileItem itemPago;
//		if (request.getAttribute("filePago") != null) {
//			itemPago = (FileItem) request.getAttribute("filePago");
//			InputStream is = itemPago.getInputStream();
//			String [] ext = itemPago.getContentType().split("/");
//			byte[] comprobantePago = IOUtils.toByteArray(is);
//			if (comprobantePago!=null){
//				MimeBodyPart attachment = new MimeBodyPart();
//				javax.activation.DataSource source = new BufferedDataSource(comprobantePago,"attachment");
//				attachment.setDataHandler(new DataHandler(source));
//				attachment.setFileName("Pago." + ext[1]);
//				mp.addBodyPart(attachment);
//		 	}
//		}
	}

	public static String obtenerRuta(){
	   String directorio = new File ("WebContent/WEB-INF/lib").getAbsolutePath();
	   return directorio;
	}


}
