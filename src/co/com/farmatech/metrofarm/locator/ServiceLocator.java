/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.locator;

import co.janker.dto.objresponse.ObjectResponse;
import co.janker.dto.objsend.ObjectSend;
import co.jankins.psf.common.conversion.MarshallServiceImpl;
import co.jankins.psf.common.exception.JankinsMarshallException;
import co.jankins.psf.common.global.GlobalDto;
import co.jankins.psf.common.global.GlobalUtils;
import com.co.farmatech.metrofarm.dto.PageData;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;


/**
 *
 * @author Bryan
 */
public class ServiceLocator {

    private URLConnection con;
    private static ServiceLocator instance;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    /**
     * Realiza el llamado a un servlet que se encuentra en una url especifica
     *
     * @param host -- Host al que se realizara el llamado.
     * @param remoteObject -- Objeto a enviar dentro del ObjectSend.
     * @param service -- Servicio a consumir.
     * @param operation -- Operación a consumir.
     * @return ObjectResponse -- Objeto de retorno o respuesta.
     */
    public ObjectResponse execute(String host, String service, String operation, Object remoteObject) throws JankinsMarshallException {
        StringBuilder sbu = new StringBuilder();
        ObjectResponse objResponse = null;

        try {
            MarshallServiceImpl marshall = new MarshallServiceImpl();
            ObjectSend obj = new ObjectSend();
            obj.setOperation(operation);
            obj.setService(service);
            obj.setContexto(GlobalUtils.getContext());
            
            Class clazz[] = getClassObjectSend(obj, remoteObject);
            String xml = marshall.marshallObject(obj, clazz);
            Gson gson = new Gson();
            String json = gson.toJson(obj);
//            String xmlBase64 = Serializable.Encriptar(xml);
            gson.fromJson(json, ObjectSend.class);
//            String data = "XML="+xmlBase64;
            String data = xml;
            URL url = new URL(host+"/DoProcess");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/plain; charset=utf-8"); 
            con.setRequestProperty("charset", "UTF-8");
            con.setDoOutput(true); 
            con.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(data.getBytes("UTF-8"));
            wr.close();
            
            BufferedReader in = new BufferedReader( new InputStreamReader(IOUtils.toBufferedInputStream(con.getInputStream()),"UTF-8"));

            StringBuffer result = new StringBuffer();
             String  RespuestaBase64= "";
            while ((RespuestaBase64 = in.readLine()) != null) {
                result.append(RespuestaBase64);
            }
             
//            RespuestaBase64 = result.toString();
//            String Respuesta = Serializable.Desencriptar(RespuestaBase64);
            String Respuesta=result.toString().replace("&#209;","Ñ").replace("&#241;", "ñ");
            
            objResponse = (ObjectResponse) marshall.unmarshallXml(Respuesta, GlobalDto.dtos);


        } catch (MalformedURLException me) {
            throw new JankinsMarshallException(me.getMessage(), me);
        } catch(SocketException se){
            throw new JankinsMarshallException("Ocurrio un error contactando el servidor,\nVerifique su conexión a internet o coloquese en contacto con el administrador del sistema", se);
        } catch (Throwable te) {
            throw new JankinsMarshallException(te.getMessage(), te);
        }
        return objResponse;
    }
    
        public ObjectResponse execute(String host, String service, String operation, Object remoteObject, PageData pageData) throws JankinsMarshallException {
        StringBuilder sbu = new StringBuilder();
        ObjectResponse objResponse = null;

        try {
            MarshallServiceImpl marshall = new MarshallServiceImpl();
            ObjectSend obj = new ObjectSend();
            obj.setOperation(operation);
            obj.setService(service);
            obj.setContexto(GlobalUtils.getContext());
            obj.setPageData(pageData);
            Class clazz[] = getClassObjectSend(obj, remoteObject);
            String xml = marshall.marshallObject(obj, clazz);
//            String xmlBase64 = Serializable.Encriptar(xml);
            
//            String data = "XML="+xmlBase64;
            String data = xml;
            URL url = new URL(host+"/DoProcess");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/plain; charset=utf-8"); 
            con.setRequestProperty("charset", "UTF-8");
            con.setDoOutput(true); 
            con.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(data.getBytes("UTF-8"));
            wr.close();
            
            BufferedReader in = new BufferedReader( new InputStreamReader(IOUtils.toBufferedInputStream(con.getInputStream()),"UTF-8"));

            StringBuffer result = new StringBuffer();
             String  RespuestaBase64= "";
            while ((RespuestaBase64 = in.readLine()) != null) {
                result.append(RespuestaBase64);
            }
             
//            RespuestaBase64 = result.toString();
//            String Respuesta = Serializable.Desencriptar(RespuestaBase64);
            String Respuesta=result.toString().replace("&#209;","Ñ").replace("&#241;", "ñ");
            
            objResponse = (ObjectResponse) marshall.unmarshallXml(Respuesta, GlobalDto.dtos);


        } catch (MalformedURLException me) {
            throw new JankinsMarshallException(me.getMessage(), me);
        } catch(SocketException se){
            throw new JankinsMarshallException("Ocurrio un error contactando el servidor,\nVerifique su conexión a internet o coloquese en contacto con el administrador del sistema", se);
        } catch (Throwable te) {
            throw new JankinsMarshallException(te.getMessage(), te);
        }
        return objResponse;
    }

    /**
     * Obtiene la clase del objeto enviado, en caso tal de ser un ArrayList
     * devuelve la clase del objeto contenido.
     *
     * @param obj -- Objeto de respuesta.
     * @param remoteObject -- Objeto de envio.
     * @return
     */
    public Class[] getClassObjectSend(ObjectSend obj, Object remoteObject) {
        Class clazz[];
        if (remoteObject instanceof ArrayList) {
            ArrayList<Class> clases = new ArrayList<Class>();

            for (Object object : (ArrayList) remoteObject) {
                boolean exist = false;
                for (Class class1 : clases) {
                    if (object.getClass().equals(class1)) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    clases.add(object.getClass());
                }

            }

            clazz = new Class[clases.size() + 2];
            for (int i = 0; i < clazz.length - 2; i++) {
                clazz[i] = clases.get(i);
            }

            obj.setRecords((ArrayList) remoteObject);
        } else {
            obj.setObj(remoteObject);
            clazz=new Class[3];
            clazz[0] = remoteObject.getClass();
        }
        clazz[clazz.length-1]=ObjectSend.class;
        clazz[clazz.length-2]=PageData.class;
        
        return clazz;
    }
}