package Helper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.testng.Reporter;

public class Efficacies {
 public String properties;
 public InputStream systemPropertyInputStream;
 public String packageName = null;
 public static Properties properties1 = null;

 public Efficacies() {
 }

 public String getProperties() {
     return this.properties;
 }

 public void setProperties(String properties) {
     this.properties = properties;
 }

 public InputStream getSystemPropertyInputStream() {
     return this.systemPropertyInputStream;
 }

 public void setSystemPropertyInputStream(InputStream systemPropertyInputStream) {
     this.systemPropertyInputStream = systemPropertyInputStream;
 }

 public synchronized Properties loadPropertyFile(String fileName) throws IOException {
     StackTraceElement packagePath = null;
     StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();

     for(int i = 0; i < stacktrace.length; ++i) {
         if (stacktrace[i].toString().contains("allure-results")) {
             packagePath = stacktrace[i];
             break;
         }
     }

     String[] arrayPackageName = packagePath.toString().split("\\.");
     String packageName = arrayPackageName[3];
     fileName = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + packageName + File.separator + fileName;
     File file = new File(fileName);
     FileInputStream fileInput = null;
     fileInput = new FileInputStream(file);
     Properties properties = new Properties();
     properties.load(fileInput);
     properties1 = properties;
     return properties;
 }

 public Properties loadPropertiesFromResources(String fileName) throws IOException {
     fileName = System.getProperty("user.dir") + File.separator + "PropertyFiles" + File.separator + fileName;
     File file = new File(fileName);
     FileInputStream fileInput = null;
     fileInput = new FileInputStream(file);
     Properties properties = new Properties();
     properties.load(fileInput);
     return properties;
 }

 public long generateRandomNumber() {
     return Instant.now().toEpochMilli();
 }

 public String gsonToJson(Object object) {
     Gson gson = new Gson();
     return gson.toJson(object);
 }

 public Map<String, String> readJsonFile(String fileName) throws Exception {
     String methodName = null;
     String filePath = null;
     StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();

     for(int i = 0; i < stacktrace.length; ++i) {
         if (stacktrace[i].getMethodName().equals("readJsonFile")) {
             methodName = stacktrace[i + 1].getMethodName();
             break;
         }
     }

     filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "TestData" + File.separator + fileName;
     JsonElement root = (new JsonParser()).parse(new FileReader(filePath));
     JsonObject jsonObject = root.getAsJsonObject();
     JsonElement some = jsonObject.get(methodName);
     JsonObject testData = some.getAsJsonObject();
     Map<String, String> testDataMap = new HashMap();
     Iterator var14 = testData.entrySet().iterator();

     while(var14.hasNext()) {
         Entry<String, JsonElement> entry = (Entry)var14.next();
         testDataMap.put(((String)entry.getKey()).toString(), ((JsonElement)entry.getValue()).getAsString());
     }

     return testDataMap;
 }

 public Map<String, String> readJsonElement(String fileName, String elementName) throws Exception {
     String filePath = null;
     StackTraceElement packagePath = null;
     StackTraceElement[] packageStackTraceArray = Thread.currentThread().getStackTrace();

     filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "TestData" + File.separator + fileName;
     
     JsonElement root = (new JsonParser()).parse(new FileReader(filePath));
     JsonObject jsonObject = root.getAsJsonObject();
     JsonElement some = jsonObject.get(elementName);
     JsonObject testData = some.getAsJsonObject();
     Map<String, String> testDataMap = new HashMap();
     Iterator var13 = testData.entrySet().iterator();

     while(var13.hasNext()) {
         Entry<String, JsonElement> entry = (Entry)var13.next();
         testDataMap.put(((String)entry.getKey()).toString(), ((JsonElement)entry.getValue()).getAsString());
     }

     return testDataMap;
 }

 public String readPropFile(String filename, String Key) {
     Properties prop = new Properties();

     try {
         prop.load(new FileInputStream(filename));
         return prop.getProperty(Key).toString();
     } catch (IOException var5) {
         var5.printStackTrace();
         return null;
     }
 }

 public void downloadFile(String downloadLink, String downloadDestn) throws Exception {
     File dir = new File(downloadDestn.substring(0, downloadDestn.lastIndexOf(File.separator)));
     if (!dir.exists()) {
         dir.mkdirs();
     }

     URL website = new URL(downloadLink);
     ReadableByteChannel readableByteChannel = Channels.newChannel(website.openStream());
     FileOutputStream fileOutputStream = new FileOutputStream(downloadDestn);
     fileOutputStream.getChannel().transferFrom(readableByteChannel, 0L, 9223372036854775807L);
     fileOutputStream.close();
     readableByteChannel.close();
     Reporter.log("Downloaded chrome driver to " + downloadDestn);
 }

 public void unzip(String zipFilePath, String destDir) throws Exception {
     File dir = new File(destDir);
     if (!dir.exists()) {
         dir.mkdirs();
     }

     byte[] buffer = new byte[1024];
     FileInputStream fileInputStream = new FileInputStream(zipFilePath);
     ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);

     for(ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
         String fileName = zipEntry.getName();
         File newFile = new File(destDir + File.separator + fileName);
         Reporter.log("Unzipping to " + newFile.getAbsolutePath());
         (new File(newFile.getParent())).mkdirs();
         FileOutputStream fos = new FileOutputStream(newFile);

         int len;
         while((len = zipInputStream.read(buffer)) > 0) {
             fos.write(buffer, 0, len);
         }

         fos.close();
         zipInputStream.closeEntry();
     }

     zipInputStream.closeEntry();
     zipInputStream.close();
     fileInputStream.close();
 }

 public ArrayList<String> readValues(ArrayList<String> namesList) {
     ArrayList<String> readValueList = new ArrayList();
     Iterator var3 = namesList.iterator();

     while(var3.hasNext()) {
         String read = (String)var3.next();
         readValueList.add(new String(Base64.getDecoder().decode(read)));
     }

     return readValueList;
 }

 public Map<String, String> readJsonElementInOrder(String fileName, String elementName) throws Exception {
     String filePath = null;
     StackTraceElement packagePath = null;
     StackTraceElement[] packageStackTraceArray = Thread.currentThread().getStackTrace();

     for(int i = 0; i < packageStackTraceArray.length; ++i) {
         if (packageStackTraceArray[i].toString().contains("")) {
             packagePath = packageStackTraceArray[i];
             break;
         }
     }

     String[] arrayPackageName = packagePath.toString().split("\\.");
     String packageName = arrayPackageName[3];
     filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + packageName + File.separator + fileName;
     JsonElement root = (new JsonParser()).parse(new FileReader(filePath));
     JsonObject jsonObject = root.getAsJsonObject();
     JsonElement some = jsonObject.get(elementName);
     JsonObject testData = some.getAsJsonObject();
     Map<String, String> testDataMap = new LinkedHashMap();
     Iterator var13 = testData.entrySet().iterator();

     while(var13.hasNext()) {
         Entry<String, JsonElement> entry = (Entry)var13.next();
         testDataMap.put(((String)entry.getKey()).toString(), ((JsonElement)entry.getValue()).getAsString());
     }

     return testDataMap;
 }

 public Map<String, String> readJsonFileInOrder(String fileName) throws Exception {
     String methodName = null;
     String filePath = null;
     StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();

     for(int i = 0; i < stacktrace.length; ++i) {
         if (stacktrace[i].getMethodName().equals("readJsonFileInOrder")) {
             methodName = stacktrace[i + 1].getMethodName();
             break;
         }
     }

     StackTraceElement packagePath = null;
     StackTraceElement[] packageStackTraceArray = Thread.currentThread().getStackTrace();

     for(int i = 0; i < packageStackTraceArray.length; ++i) {
         if (packageStackTraceArray[i].toString().contains("")) {
             packagePath = packageStackTraceArray[i];
             break;
         }
     }

     String[] arrayPackageName = packagePath.toString().split("\\.");
     String packageName = arrayPackageName[3];
     filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + packageName + File.separator + fileName;
     JsonElement root = (new JsonParser()).parse(new FileReader(filePath));
     JsonObject jsonObject = root.getAsJsonObject();
     JsonElement some = jsonObject.get(methodName);
     JsonObject testData = some.getAsJsonObject();
     Map<String, String> testDataMap = new LinkedHashMap();
     Iterator var14 = testData.entrySet().iterator();

     while(var14.hasNext()) {
         Entry<String, JsonElement> entry = (Entry)var14.next();
         testDataMap.put(((String)entry.getKey()).toString(), ((JsonElement)entry.getValue()).getAsString());
     }

     return testDataMap;
 }
}

