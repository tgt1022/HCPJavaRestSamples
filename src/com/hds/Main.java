package com.hds;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;



public class Main {

    static private final  String HCPAuthHeaderKey = "Authorization";

    public static void main(String[] args) {
        try {
            String auth = "HCP " + Utils.getBase64Value("exampleuser") + ":" + Utils.getMD5Value("passw0rd");

            //Create an instance of an HTTPClient
            HttpClient client = HttpClientBuilder.create().build();
            put(client, auth);
//            putWithIndexAndShreddingEnabled(client, auth); //Set Index and Shredding to TRUE
//            post(client, auth); //Set Hold to true
//            get(client, auth);  //Download file
//            getDirList(client, auth); //List a directory
//            getStats(client, auth); //Get HCP stats
//            head(client, auth); //Execute a HEAD operation
//            addMetaData(client, auth); // Add my-annotation
//            getMetaData(client, auth); //Get my-annotation
//            delete(client, auth); //Delete object -> will not work if HOLD was set to true (forbidden)
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static void getStats(HttpClient client, String auth) throws IOException {
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url =  "http://example-namespace.example-tenant.cluster59h-vm3.lab.archias.com/vproc/statistics";

        //create a new HttpClient object and a GET request object
        HttpGet request = new HttpGet(url);

        //add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);


        //execute the request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());

        //get response content
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        //print response content to console
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }


    public static void getDirList(HttpClient client, String auth) throws IOException {
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt";

        HttpGet request = new HttpGet(url);

        //add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);


        //execute the request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());

        //get response content
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        //print response content to console
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }

    public static void head(HttpClient client, String auth) throws IOException{
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt";

        HttpHead request = new HttpHead(url);

        //add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);

        //execute the request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());

        //get response content
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        //print response content to console
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }

    public static void get(HttpClient client, String auth) throws IOException {
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt";

        HttpGet request = new HttpGet(url);

        //add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);
        request.addHeader("Range", "bytes=0-0");

        //execute the request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());

        //get response content
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        //print response content to console
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }
//
//        BufferedInputStream bis = new BufferedInputStream(entity.getContent());
//        String filePath = /tmp/outfile.txt""
//        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
//        int inByte;
//        while((inByte = bis.read()) != -1) bos.write(inByte);
//        bis.close();
//        bos.close();
//    }

    public static void putUsingChunks(HttpClient client, String auth) throws IOException{
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt";

        //specify path to file you want to upload(PUT)
        String localFilePath = "/Users/japark/Development/Java/HCPJavaRestSamples/world.txt";

        HttpPut request = new HttpPut(url);

        //add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);

        //setup byte array for file to upload(PUT)
        File input = new File(localFilePath);
        byte[] fileAsByteArr = Utils.fileToByteArray(input);

        ByteArrayEntity requestEntity = new ByteArrayEntity(fileAsByteArr);
        requestEntity.setChunked(true);

        //set the request to use the byte array
        request.setEntity(requestEntity);
        //execute PUT request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());

    }

    public static void put(HttpClient client, String auth) throws IOException{
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt";

        //specify path to file you want to upload(PUT)
        String localFilePath = "/Users/japark/Development/Java/HCPJavaRestSamples/world.txt";

        HttpPut request = new HttpPut(url);

        //add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);
//
//        UNCOMMENT TO USE CONDITIONAL PUT
//        request.addHeader("If-None-Match", "86d434bcb3a1af9b0b764fc7dd4dd31a");
//        request.addHeader("Expect", "100-CONTINUE");

        //setup byte array for file to upload(PUT)
        File input = new File(localFilePath);
        byte[] fileAsByteArr = Utils.fileToByteArray(input);

        ByteArrayEntity requestEntity = new ByteArrayEntity(fileAsByteArr);

        //set the request to use the byte array
        request.setEntity(requestEntity);
        //execute PUT request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());

    }


    public static void putWithIndexAndShreddingEnabled(HttpClient client, String auth) throws IOException{
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt" +
                              "?index=true&shred=true";

        //specify path to file you want to upload(PUT)
        String localFilePath = "/Users/japark/Development/Java/HCPJavaRestSamples/world.txt";

        HttpPut request = new HttpPut(url);

        //add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);

        //setup byte array for file to upload(PUT)
        File input = new File(localFilePath);
        byte[] fileAsByteArr = Utils.fileToByteArray(input);

        ByteArrayEntity requestEntity = new ByteArrayEntity(fileAsByteArr);

        //set the request to use the byte array
        request.setEntity(requestEntity);
        //execute PUT request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
    }




    public static void post(HttpClient client, String auth) throws IOException{
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt";

        HttpPost request = new HttpPost(url);

        // add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);

        // set field hold to true
        List <NameValuePair> nameValuePairs = new ArrayList <NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("hold", "true"));
        request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        //execute DELETE request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
    }


    public static void delete(HttpClient client, String auth) throws IOException{
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt?" +
                "privileged=true&reason=Because%20I%20Said%20So";

        HttpDelete request = new HttpDelete(url);

        // add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);

        //execute DELETE request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
    }

    public static void addMetaData(HttpClient client, String auth) throws IOException{
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt?" +
                        "type=custom-metadata&annotation=myannotation";

        //specify path to file you want to upload(PUT)
        String localFilePath = "/Users/japark/Development/Java/HCPJavaRestSamples/myannotation.xml";

        HttpPut request = new HttpPut(url);

        //add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);

        //setup byte array for file to upload(PUT)
        File input = new File(localFilePath);
        byte[] fileAsByteArr = Utils.fileToByteArray(input);

        ByteArrayEntity requestEntity = new ByteArrayEntity(fileAsByteArr);

        //set the request to use the byte array
        request.setEntity(requestEntity);
        //execute PUT request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
    }

    public static void getMetaData(HttpClient client, String auth) throws IOException{
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt?" +
                              "type=custom-metadata&annotation=myannotation";

        HttpGet request = new HttpGet(url);

        //add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);

        //execute the request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());

        //get response content
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        //print response content to console
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }

    public static void deleteMetaData(HttpClient client, String auth) throws IOException{
        //specify namespace URL - eg. namespace.tenant.HCP.DOMAIN.com/rest/path
        String url = "http://example-namespace.example-tenant.cluster59h-vm3.lab.archivas.com/rest/examples/world.txt?" +
                               "type=custom-metadata&annotation=myannotation";
        HttpDelete request = new HttpDelete(url);

        // add authorization header for user(base64) "exampleuser" with password(md5) "passw0rd"
        request.addHeader(HCPAuthHeaderKey, auth);

        //execute DELETE request
        HttpResponse response = client.execute(request);

        //print response status to console
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
    }
    

}
