package com.uc.training.remote.utils;

import com.kenai.jaffl.annotations.IgnoreError;
import com.ycc.base.framework.remote.client.annotations.RemoteMethod;
import org.apache.poi.ss.formula.functions.Match;
import org.apache.poi.ss.formula.functions.T;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自动根据service服务接口方法，生成client调用服务方的方法
 *
 * @author zhongling(ling.zhong @ ucarinc.com)
 * @since 2018年11月28日 14:46
 */
public class AutoGetInfo {

    public static void main(String[] args) {
        //自动生成client代码
//        AutoGetInfo.autoGenerateCode();
        //自动提取服务注册中心所需要的格式
        AutoGetInfo.autoGenerateInterface();
    }

    /**
     * 自动生成client代码
     */
    public static void autoGenerateCode(){
        List<FunctionInfo> functionInfoList = new LinkedList<>();
        String functionName;
        String returnType = null;
        String[] paramType = null;
        String serviceDesc;
        String serviceName;
        FunctionInfo functionInfo;
        try {
            Class clazz = Class.forName(JavaFileInfo.INTERFACE_PAKAGE);
            Method[] methods = clazz.getMethods();
            for(Method method: methods){

                functionName = method.getName();
                RemoteMethod remoteMethodAnnotation = method.getAnnotation(RemoteMethod.class);
                //获取方法上@ApiOperation注解的value值
                serviceName = remoteMethodAnnotation.serviceName();
                serviceDesc = remoteMethodAnnotation.serviceDesc();

                //提取返回值
                Type type = method.getGenericReturnType();
                ParameterizedType parameterizedType=(ParameterizedType)type;
                //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
                Type[] actualType= parameterizedType.getActualTypeArguments();
                if(actualType == null){
                    System.out.println("方法名："+functionName + "的返回值视个泛型，无具体类型！");
                }else{
//                    System.out.println("泛型类型:" + actualType[0]);
                    if(actualType[0] instanceof Class){
                        returnType = actualType[0].toString();
                        returnType=returnType.substring(returnType.lastIndexOf(".")+1);
                    }else{
                        returnType = actualType[0].toString();
                        Matcher matcher = FunctionInfo.GENERIC_PATTERN.matcher(returnType);

                        if(matcher.find() && matcher.groupCount() == 2){
                            returnType = matcher.group(1).substring(matcher.group(1).lastIndexOf(".")+1) + "<" + matcher.group(2).substring(matcher.group(2).lastIndexOf(".")+1) +">";
                        }else{
                            returnType = matcher.group(1);
                        }
                    }
                }

                Class[] parameterTypes = method.getParameterTypes();
                paramType = new String[parameterTypes.length];
                for(int i = 0; i < parameterTypes.length; i++) {
                    paramType[i] = parameterTypes[i].getName();
                    paramType[i]=paramType[i].substring(paramType[i].lastIndexOf(".")+1);
                }
                functionInfo = new FunctionInfo(serviceName, serviceDesc, functionName, returnType, paramType);
//                System.out.println("方法属性 : "+functionInfo.toString());
                functionInfoList.add(functionInfo);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //TODO:格式化属性和方法后，将内容插入到java中文件
        String content = generateCodeContent(functionInfoList);
        generateJavaFile(content);
    }

    /**
     * 自动提取服务注册中心所需要的格式
     */
    public static void autoGenerateInterface(){
        List<FunctionInfo> functionInfoList = new LinkedList<>();
        String functionName;
        String serviceDesc;
        String serviceName;
        FunctionInfo functionInfo;
        try {
            Class clazz = Class.forName(JavaFileInfo.INTERFACE_PAKAGE);
            Method[] methods = clazz.getMethods();
            for(Method method: methods){
                functionName = method.getName();
                RemoteMethod remoteMethodAnnotation = method.getAnnotation(RemoteMethod.class);
                //获取方法上@ApiOperation注解的value值
                serviceName = remoteMethodAnnotation.serviceName();
                serviceDesc = remoteMethodAnnotation.serviceDesc();
                functionInfo = new FunctionInfo(serviceName, serviceDesc, functionName);
                functionInfoList.add(functionInfo);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //TODO:格式化服务名称和注解
        Iterator<FunctionInfo> iterator = functionInfoList.iterator();
        //存储生成的json串
        StringBuilder json = new StringBuilder();
        json.append("[");
        while(iterator.hasNext()){
            functionInfo = iterator.next();
            String jsonStr = formatInterfaceJson(functionInfo);
            json.append(jsonStr);
            if(iterator.hasNext()){
                json.append(",");
            }
        }
        json.append("]");
        System.out.println(json.toString());
    }
    /**
     * 组建需要生成的代码内容
     * @param functionInfoList
     * @return
     */
    public static String generateCodeContent(List<FunctionInfo> functionInfoList){
        StringBuilder content = new StringBuilder();
        System.out.println("---------提取的方法信息----------");
        Iterator<FunctionInfo> iterator = functionInfoList.iterator();
        FunctionInfo functionInfo;

        //class文件的公共信息部分内容
        String classInfoContent = formatClassInfo();
        //属性
        String propertyContent;
        //方法内容
        String functionContent;

        content.append(classInfoContent);
        while(iterator.hasNext()){
            functionInfo = iterator.next();
            //TODO:生成代码内容
            propertyContent = formatServiceName(functionInfo);
            functionContent = formatFunctionStr(functionInfo);
            content.append(propertyContent).append(functionContent);
            System.out.println(functionInfo.toString());
        }
        content.append("}");
        return content.toString();
    }

    /**
     * 创建class文件的公共信息部分
     * @return
     */
    public static String formatClassInfo(){
        String classInfo = "package " + JavaFileInfo.PACKAGE_NAME + ";\n" + "\n"
        + "import com.uc.training.remote.utils.RemoteUtil;\n"
        + "import org.slf4j.Logger;\n" + "import org.slf4j.LoggerFactory;\n"
        + "import org.springframework.beans.BeanUtils;\n" + "\n"
        + "import java.util.List;\n" + "\n"
        + "public  final class "+ JavaFileInfo.CLASS_NAME +" {\n" + "\n"
        + "    private static final Logger logger = LoggerFactory.getLogger("+ JavaFileInfo.CLASS_NAME +".class.getName());";
        return classInfo;
    }
    /**
     * 格式化，将服务名称ServiceName、服务描述ServiceDESC，生成静态常量属性
     * @param functionInfo
     * @return
     */
    public static String formatServiceName(FunctionInfo functionInfo){

        String s="\n    /**\n" + "     * "+functionInfo.getServiceDesc()+"\n" + "     */\n" ;
        StringBuilder str=new StringBuilder(s);
        String serviceNameTemp = humpNamingTranslateIntoUpperCase(functionInfo.getFunctionName());
        s = "    private static final String "+ serviceNameTemp +" = \"" + functionInfo.getServiceName() + "\";";
        str.append(s);
        return str.toString();
    }

    /**
     * 将服务名称的驼峰命名改成final命名规范
     * @param functionName
     * @return
     */
    public static String humpNamingTranslateIntoUpperCase(String functionName){
        StringBuilder serviceNameTemp=new StringBuilder();
        String temp=null;
        int count =0;
        for(int i = 0; i < functionName.length(); i++) {
            if(i == functionName.length()-1){
                temp = functionName.substring(count,i+1);
                serviceNameTemp.append(temp.toUpperCase());
                break;
            }
            if(functionName.charAt(i)>='A' && functionName.charAt(i)<='Z'){
                temp = functionName.substring(count,i);
                serviceNameTemp.append(temp.toUpperCase()).append("_");
                count=i;
            }
        }
        return serviceNameTemp.toString();
    }
    /**
     * 格式化，生成对应方法
     * @param functionInfo
     * @return
     */
    public static String formatFunctionStr(FunctionInfo functionInfo){
        //返回类型
        String returnType=functionInfo.getReturnType();
        String serviceName=functionInfo.getFunctionName();
        //获取入参
        StringBuilder enterParam = new StringBuilder();
        StringBuilder paramStr = new StringBuilder();
        String[] paramTypes = functionInfo.getParamType();
        //入参
        String paramName;
        //传参
        String param;
        for(int i = 0; i < paramTypes.length; i++) {
            if(i == paramTypes.length-1){
                paramName = paramTypes[i] + " param" + i;
                param = " param" + i;
            }else{
                paramName = paramTypes[i] + " param" + i + ", ";
                param = " param" + i + ", ";
            }
            enterParam.append(paramName);
            paramStr.append(param);
        }

        String serviceNameTemp = humpNamingTranslateIntoUpperCase(serviceName);
        String str = "\n    /**\n" + "     * "+ functionInfo.getServiceDesc() +"\n" + "     */\n"
        + "    public static "+ returnType + " " + functionInfo.getFunctionName() + "("+ enterParam +") {\n"
        + "        try {\n";
        if(paramTypes == null || paramTypes.length == 0){
            str += "            return ("+ returnType +") RemoteUtil.exec("+serviceNameTemp+");\n";
        }else {
            str += "            return ("+ returnType +") RemoteUtil.exec("+serviceNameTemp+", "+ paramStr +");\n";
        }
        str += "        } catch (ClassCastException e) {\n"
        + "            logger.error(\"类型转换异常\");\n"
        + "            logger.error(e.getMessage());\n" + "        }\n"
        + "        return null;\n" + "    }\n";
        return str;
    }

    /**
     * 将java文件的类信息转成字符串
     * @return
     */
    public static String addClassNameStr(){
        String info="package "+ JavaFileInfo.PACKAGE_NAME +";\n" + "\n" + "public class "+ JavaFileInfo.CLASS_NAME +" {\n" + "\n"
        + "    private static final Logger logger = LoggerFactory.getLogger("+ JavaFileInfo.CLASS_NAME +".class.getName());";
        return info;
    }
    /**
     * 把属性和方法插入到java中文件
     * @param codeContent
     */
    public static void generateJavaFile(String codeContent){
        File file =new File(JavaFileInfo.TO_FILE_NAME);

        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(file,true);
            fileOutputStream.write(codeContent.getBytes("utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化，将服务名称ServiceName、服务描述ServiceDESC，添加到json格式串中
     * @param functionInfo
     * @return
     */
    public static String formatInterfaceJson(FunctionInfo functionInfo){
        String serviceName = functionInfo.getServiceName();
        String functionName = functionInfo.getFunctionName();
        String serviceDesc = functionInfo.getServiceDesc();
        String s="{\n" + "\t\"name\": \"" + serviceName + "\",\n"
        + "\t\"methodName\": \""+ functionName +"\",\n"
        + "\t\"descipt\": \""+ serviceDesc +"\",\n"
        + "\t\"beanId\": \""+ JavaFileInfo.BEAN_ID +"\",\n"
        + "\t\"connectionTimeOut\": -1,\n"
        + "\t\"readTimeOut\": -1,\n"
        + "\t\"needResult\": 1\n" + "}" ;
        return s;
    }
}
class FunctionInfo{
    /**
     * 正则式匹配，提取泛型的对象类型
     */
    public static final String REGEX_PATTERN_GENERIC = "(.*?)<(.*?)>$";
    public static final Pattern GENERIC_PATTERN = Pattern.compile(REGEX_PATTERN_GENERIC);

    public FunctionInfo() {
    }
    public FunctionInfo(String serviceName, String serviceDesc, String functionName, String returnType, String[] paramType) {
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        this.functionName = functionName;
        this.returnType = returnType;
        this.paramType = paramType;
    }

    public FunctionInfo(String serviceName, String serviceDesc, String functionName) {
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        this.functionName = functionName;
    }

    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 服务描述
     */
    private String serviceDesc;
    /**
     * 方法名
     */
    private String functionName;
    /**
     * 返回类型
     */
    private String returnType;
    /**
     * 入参
     */
    private String[] paramType;


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String[] getParamType() {
        return paramType;
    }

    public void setParamType(String[] paramType) {
        this.paramType = paramType;
    }

    @Override
    public String toString() {
        return "FunctionInfo{" + "serviceName='" + serviceName + '\'' + ", serviceDesc='" + serviceDesc + '\'' + ", functionName='" + functionName + '\'' + ", returnType='" + returnType + '\'' + ", paramType=" + Arrays.toString(paramType) + '}';
    }
}
class JavaFileInfo{
    /**
     * class名称
     */
    public static final String CLASS_NAME = "GdsClientTest";
    /**
     * 信息填充java文件
     */
    public static final String TO_FILE_NAME = "E:\\companyTrains\\smadmin\\src\\main\\java\\com\\uc\\training\\remote\\client\\"+ CLASS_NAME +".java";
    /**
     * client的包名称
     */
    public static final String PACKAGE_NAME = "com.uc.training.remote.client";
    /**
     * interfacePakage名称
     */
    public static final String INTERFACE_PAKAGE = "com.uc.training.gds.service.GoodsRemoteService";

    /**
     * 服务id
     */
    public static final String BEAN_ID = "goodsRemoteService";
}